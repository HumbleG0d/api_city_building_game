# Proyecto 3: Juego de construcción de ciudades

Estos ejercicios están diseñados para reforzar los conocimientos clave relacionados con el desarrollo de software y el proyecto.

## 1. Gestión de recursos y construcción de edificios (3 puntos)

- **Tarea**: Implementar la lógica para que los jugadores puedan gestionar sus recursos (por ejemplo, madera, piedra, oro) y construir diferentes tipos de edificios en su ciudad a través de la API REST.
- **Objetivo**: Comprender cómo manejar el almacenamiento y la actualización de recursos dentro de una API REST.
- **Enfoque del ejercicio**: Usar Flask o FastAPI (Python), Spring Boot (Java) o Ruby on Rails para gestionar la lógica de recursos y construcción, con almacenamiento en bases de datos como PostgreSQL o MySQL.


```java
/**
     * Adds a new infrastructure entity to a specific city.
     * If the infrastructure type is RANCH, generates resources associated with farms.
     *
     * @param addInfreaestructureRequest the request object containing infrastructure details
     * @param cityId the ID of the city to which the infrastructure belongs
     * @return an {@link InfraestructureDTO} representing the newly added infrastructure
     */
    @Override
    public InfraestructureDTO addInfraestructure(AddInfreaestructureRequest addInfreaestructureRequest, Long cityId) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new CityNotFoundException("City not found"));

        Infraestructure infraestructure = createInfraestructure(addInfreaestructureRequest);
        infraestructure.setCity(city);
        infraestructure = infraestructureRepository.save(infraestructure);

        city.getInfraestructure().add(infraestructure);
        cityRepository.save(city);

        if (infraestructure.getTypeInfraestructure().equals(TypeInfraestructure.RANCH)) {
            this.generateFarmResources(city);
        }

        return convertToDTO(infraestructure);
    }

    /**
     * Creates a new infrastructure entity based on the request data.
     *
     * @param addInfreaestructureRequest the request object containing infrastructure details
     * @return the created {@link Infraestructure} entity
     */
    private Infraestructure createInfraestructure(AddInfreaestructureRequest addInfreaestructureRequest) {
        return new Infraestructure(
                addInfreaestructureRequest.getName(),
                addInfreaestructureRequest.getTypeInfraestructure(),
                addInfreaestructureRequest.getCost(),
                addInfreaestructureRequest.getTimeConstruction(),
                addInfreaestructureRequest.getAbility(),
                addInfreaestructureRequest.getDescription()
        );
    }

    /**
     * Converts an {@link Infraestructure} entity to a {@link InfraestructureDTO}.
     *
     * @param infraestructure the infrastructure entity to convert
     * @return an {@link InfraestructureDTO} representing the infrastructure
     */
    private InfraestructureDTO convertToDTO(Infraestructure infraestructure) {
        return new InfraestructureDTO(
                infraestructure.getName(),
                infraestructure.getTypeInfraestructure(),
                infraestructure.getCost(),
                infraestructure.getTimeConstruction(),
                infraestructure.getAbility()
        );
    }

    /**
     * Generates farm resources for a city based on its infrastructures of type RANCH.
     *
     * @param city the city for which to generate resources
     */
    private void generateFarmResources(City city) {
        List<Infraestructure> infraestructures = this.getInfrestrucutureByType(TypeInfraestructure.RANCH);

        infraestructures.forEach(infraestructure -> {
            int productionAmount = infraestructure.getAbility() + 5;
            infraestructure.setAbility(productionAmount);

            Resource resource = resourceRepository.findByCityAndTypeResource(infraestructure.getCity(), Type_resource.AGRICULTURAL);

            if (resource == null) {
                resource = new Resource(infraestructure.getName(), Type_resource.AGRICULTURAL, productionAmount, infraestructure.getCity());
            }

            resource.setTotal(resource.getTotal() + productionAmount);
            resourceRepository.save(resource);

            // Adding additional resources to the city
            resourceService.addResourceByFarm(city, "Wood", Type_resource.NATURAL, 5);
            resourceService.addResourceByFarm(city, "Stone", Type_resource.NATURAL, 5);
            resourceService.addResourceByFarm(city, "Earth", Type_resource.NATURAL, 5);
            resourceService.addResourceByFarm(city, "Energy", Type_resource.ENERGY, 10);
        });
    }
```

Tabla de la ciudades creadas

![](https://github.com/HumbleG0d/api_city_building_game/blob/main/api/assets/city_bd.png)


Tabla de las infraestructuras construidas
![](https://github.com/HumbleG0d/api_city_building_game/blob/main/api/assets/tipo_infra.png)


## 2. Monitorización de la gestión de recursos (2 puntos)

- **Tarea**: Configurar Prometheus para monitorear el uso de recursos, el número de edificios construidos y el progreso de las ciudades de los jugadores. Crear un tablero de control básico en Grafana para visualizar las estadísticas de cada ciudad.
- **Objetivo**: Entender la integración de Prometheus y Grafana para el monitoreo de aplicaciones en tiempo real.
- **Enfoque del ejercicio**: Configurar exportadores de métricas para Prometheus y crear gráficos personalizados en Grafana.

    ![](https://github.com/HumbleG0d/api_city_building_game/blob/main/api/assets/grafana_request.png)



    ![](https://github.com/HumbleG0d/api_city_building_game/blob/main/api/assets/grafana_statics.png)


## 3. Dockerización del juego (3 puntos)

- **Tarea**: Dockerizar la aplicación del juego, incluyendo la API y la base de datos. Usar Docker Compose para coordinar los servicios de manera eficiente.
- **Objetivo**: Aprender a contenerizar una aplicación completa y gestionar múltiples servicios.
- **Enfoque del ejercicio**: Escribir un Dockerfile para la API, configurar `docker-compose.yml` para coordinar la API y la base de datos, y probar la solución.

```docker
services:
  postgres:
    container_name: postgres-sql
    image: postgres
    env_file:
      - api/.env  # Environment variables for Postgres configuration
    ports:
      - "1234:5432"  # Expose Postgres on port 1234
    volumes:
      - postgres:/var/lib/postgres/data  # Persist Postgres data
    networks:
      - spring-boot-net  # Connect to the specified network

  citybuildinggame:
    container_name: city_game
    image: city_game  # Your application image
    ports:
      - "8081:8081"  # Expose application on port 8081
    networks:
      - spring-boot-net  # Connect to the specified network
    depends_on:
      - postgres  # Ensure Postgres starts before your application

  # Prometheus service
  prometheus:
    image: "prom/prometheus"
    restart: unless-stopped  # Restart policy
    networks:
      - spring-boot-net  # Connect to the specified network
    ports:
      - "9090:9090"  # Expose Prometheus on port 9090
    volumes:
      - ./prometheus.yml:/etc/prometheus/prometheus.yml  # Configuration file for Prometheus
    depends_on:
      - citybuildinggame  # Ensure your application starts before Prometheus

  # Grafana service
  grafana:
    image: "grafana/grafana-enterprise"
    restart: unless-stopped  # Restart policy
    networks:
      - spring-boot-net  # Connect to the specified network
    ports:
      - "3000:3000"  # Expose Grafana on port 3000
    links:
      - prometheus:prometheus  # Link to Prometheus

volumes:
  postgres:  # Define a named volume for Postgres data persistence

networks:
  spring-boot-net:
    driver: bridge  # Use the bridge driver for networking
```



## 4. Mejoras automáticas de edificios (3 puntos)

- **Tarea**: Implementar una función que permita a los jugadores mejorar automáticamente los edificios en su ciudad, basándose en la cantidad de recursos disponibles. Si el jugador tiene suficientes recursos, el edificio mejora automáticamente, de lo contrario, la solicitud falla.
- **Objetivo**: Trabajar con la lógica de actualización de estado de edificios y la validación de recursos.
- **Enfoque del ejercicio**: Usar Python, Java o Ruby para crear esta lógica en la API y asegurar una validación adecuada de los recursos.


    ```java
    public boolean upgradeBuilding(Long buildingId, Long cityId) {
    Building building = buildingRepository.findById(buildingId)
            .orElseThrow(() -> new RuntimeException("Building not found"));

    List<Resource> resources = resourceRepository.findByCityId(cityId);

    // Check if there are enough resources
    int upgradeCost = building.getUpgradeCost();
    Resource requiredResource = resources.stream()
            .filter(resource -> resource.getType().equals("coins"))  
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Resource not found"));

    if (requiredResource.getAmount() >= upgradeCost) {
        requiredResource.setAmount(requiredResource.getAmount() - upgradeCost);
        building.setLevel(building.getLevel() + 1);  

        resourceRepository.save(requiredResource);
        buildingRepository.save(building);

        return true;
    } else {
        return false;
    }
}
    ```

## 5. Uso de Git Squash para condensar commits (2 puntos)

- **Tarea**: Crear un historial de commits con varios cambios pequeños en el proyecto. Usar `git squash` para condensar los commits en uno solo y documentar el proceso.
- **Objetivo**: Aprender cómo limpiar el historial de commits en Git y la importancia de mantener un flujo de trabajo limpio.
- **Enfoque del ejercicio**: Simular un flujo de trabajo de desarrollo donde se crean múltiples commits pequeños, y luego se condensan en un solo commit mediante `git squash`.
