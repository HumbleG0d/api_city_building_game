# City-Building Game API

Este es el backend de un juego de construcción de ciudades, desarrollado en Java utilizando Spring Boot. La API permite la gestión de recursos, infraestructuras, ciudades y poblaciones dentro del juego.

## Tabla de Contenidos

- [Requisitos](#requisitos)
- [Instalación](#instalación)
  - [Clonar el Repositorio](#clonar-el-repositorio)
  - [Ejecución Local](#ejecución-local)
  - [Ejecución con Docker](#ejecución-con-docker)
- [Uso](#uso)
- [Capturas de Pantalla y Ejemplos de Requests](#capturas-de-pantalla-y-ejemplos-de-requests)
- [Monitoreo con Prometheus y Grafana](#monitoreo-con-prometheus-y-grafana)

## Requisitos

- Java 17+
- Maven 3.6+
- Docker (opcional)

## Instalación

### Clonar el Repositorio

Primero, clona el repositorio a tu máquina local:

```bash
git clone git@github.com:HumbleG0d/api_city_building_game.git
cd api_city_building_game
```

### Ejecución Local

* Asegúrate de tener Java 17 y Maven instalados en tu sistema.

* Navega al directorio del proyecto y construye el proyecto con Maven:

    ```bash
    mvn clean install
    ```
* Ejecuta la aplicación:
    
    ```bash
    mvn clean install
    ```
La API estará disponible en http://localhost:8081

### Ejecución con Docker

También puedes ejecutar la API utilizando Docker. Para esto, necesitas tener Docker instalado en tu máquina.

* Asegúrese  de tener Docker corriendo.

* En el directorio , donde se encuentra el archivo `Dockerfile` , ejecute el siguiente comando: 
    
    ```bash
    docker build -t <name> .
    ```
*  En el directorio raíz del proyecto, donde se encuentra el archivo `docker-compose.yml`, ejecuta el siguiente comando para iniciar todos los servicios (API, Prometheus y Grafana):

    ```bash
    docker compose up 
    ```
La API estará disponible en http://localhost:8081.


## Uso

Para empezar a utilizar la API, aquí hay algunas rutas disponibles:

* Crear una ciudad:  `POST /api/v1/cities/add`

* Obtener infraestructuras por tipo: `GET /api/v1/infrastructures/infraestructure/by-type`

## Capturas de Pantalla y Ejemplos de Requests

### 1. Crear una Ciudad

**Endpoint**: `POST /api/v1/cities/add`

**Descripción**: Este request crea una nueva ciudad en el juego con un nombre especificado.


**Cuerpo del request (JSON)**:

```json
{
  "name": "Lima"
}
```

 ![](https://github.com/HumbleG0d/api_city_building_game/blob/main/api/assets/post_city.png)


### 2. Mostrar Ciudad por id

**Endpoint**: `GET /api/v1/cities/{id_city}/city`

**Descripción**: Este request nos muestra la  ciudad por id..


**Cuerpo del request (JSON)**:

```json
{
    "message": "Get city success",
    "data": {
        "id": 1,
        "name": "Lima",
        "resource": [
            {
                "name": "Madera",
                "total": 79
            },
            {
                "name": "Piedra",
                "total": 93
            },
            {
                "name": "Tierra",
                "total": 69
            },
            {
                "name": "Agua",
                "total": 89
            },
            {
                "name": "Arena",
                "total": 83
            },
            {
                "name": "Energia",
                "total": 88
            }
        ],
        "totalPopulation": 2,
        "coins": 1000.00,
        "infraestructures": []
    }
}
```

 ![](https://github.com/HumbleG0d/api_city_building_game/blob/main/api/assets/get_city.png)


### 2. Crear una Infraestrucutura

### 1. Crear una Infraestructura para un Ciudad

**Endpoint**: `POST /api/v1/infraestructures/add/{id_city}`

**Descripción**: Este request crea una infraestructura  en una ciudad determinada en el juego.


**Cuerpo del request (JSON)**:

```json
{
    "name": "Manzanas",
    "typeInfraestructure": "RANCH",
    "cost": {
        "madera": 5,
        "piedra": 5
    },
    "timeConstruction": 5,
    "ability": 10,
    "description": "Chacra para la producción de manzanas."
}
```

 ![](https://github.com/HumbleG0d/api_city_building_game/blob/main/api/assets/post_infra.png)



### 2. Mostrar Infraestructura por id

**Endpoint**: `GET /api/v1/infraestructures/{id_infraestructure}/infraestructure`

**Descripción**: Este request crea una infraestructura  en una ciudad determinada en el juego.


**Cuerpo del request (JSON)**:

```json
{
    "name": "Manzanas",
    "typeInfraestructure": "RANCH",
    "cost": {
        "madera": 5,
        "piedra": 5
    },
    "timeConstruction": 5,
    "ability": 10,
    "description": "Chacra para la producción de manzanas."
}
```

 ![](https://github.com/HumbleG0d/api_city_building_game/blob/main/api/assets/post_infra.png)


## Monitoreo con Prometheus y Grafana

### Accediendo a Prometheus

1. Una vez que todos los contenedores estén corriendo, puedes acceder a la interfaz web de Prometheus en:

    ```
    http://localhost:9090
    ```

    ![](https://github.com/HumbleG0d/api_city_building_game/blob/main/api/assets/prometheus.png)


2. En la interfaz de Prometheus, puedes hacer consultas sobre las métricas recolectadas. Por ejemplo:
   - Consulta todas las métricas recolectadas: `up`
   - Métricas sobre el uso de CPU: `process_cpu_seconds_total`
   - Métricas sobre el estado de tus endpoints: `http_requests_total`

    ![](https://github.com/HumbleG0d/api_city_building_game/blob/main/api/assets/prometheus_up.png)

3. También puedes ver el estado de las series de tiempo, targets y alertas.

### Accediendo a Grafana

1. Grafana estará disponible en el puerto `3000` por defecto. Accede a Grafana desde tu navegador:

    ```
    http://localhost:3000
    ```

2. Las credenciales por defecto son:
    - **Usuario**: `admin`
    - **Contraseña**: `admin`

   Se te pedirá cambiar la contraseña la primera vez que inicies sesión.

   ![](https://github.com/HumbleG0d/api_city_building_game/blob/main/api/assets/grafana.png)

3. Una vez dentro de Grafana:
    - Ve a la sección de **Configuration** y selecciona **Data Sources**.
    - Agrega **Prometheus** como una fuente de datos usando la siguiente URL para conectarlo:

      ```
      http://prometheus:9090
      ```


    ![](https://github.com/HumbleG0d/api_city_building_game/blob/main/api/assets/grafana_add_p.png)


4. Crea un dashboard para visualizar las métricas:
    - Ve a **Create** > **Dashboard**.
    - Selecciona **Add a new panel** y en la sección de **Data source**, selecciona **Prometheus**.
    - Escribe una consulta para mostrar una métrica (por ejemplo, `http_requests_total`).
    - Guarda el panel y agrégalo a un dashboard.

    ![](https://github.com/HumbleG0d/api_city_building_game/blob/main/api/assets/grafana_request.png)


    ![](https://github.com/HumbleG0d/api_city_building_game/blob/main/api/assets/grafana_statics.png)