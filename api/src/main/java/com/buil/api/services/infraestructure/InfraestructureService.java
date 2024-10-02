package com.buil.api.services.infraestructure;

import java.util.List;

import org.springframework.stereotype.Service;

import com.buil.api.dto.InfraestructureDTO;
import com.buil.api.exceptions.CityNotFoundException;
import com.buil.api.exceptions.InfraestructureNotFoundException;
import com.buil.api.model.City;
import com.buil.api.model.infraestructure.Infraestructure;
import com.buil.api.model.infraestructure.TypeInfraestructure;
import com.buil.api.model.resource.Resource;
import com.buil.api.model.resource.Type_resource;
import com.buil.api.repository.CityRepository;
import com.buil.api.repository.InfraestructureRepository;
import com.buil.api.repository.ResourceRepository;
import com.buil.api.request.AddInfreaestructureRequest;
import com.buil.api.services.resources.ResourceService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

/**
 * Service implementation of {@link IInfraestructureService} for managing infrastructure-related operations.
 * Handles logic for adding, retrieving, and deleting infrastructure entities, as well as managing associated resources.
 */
public class InfraestructureService implements IInfraestructureService {

    private final CityRepository cityRepository;
    private final InfraestructureRepository infraestructureRepository;
    private final ResourceRepository resourceRepository;
    private final ResourceService resourceService;

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

    /**
     * Retrieves an infrastructure by its ID.
     *
     * @param id the ID of the infrastructure to retrieve
     * @return an {@link InfraestructureDTO} representing the found infrastructure
     */
    @Override
    public InfraestructureDTO getInfraestructureById(Long id) {
        var infraestructure = infraestructureRepository.findById(id)
                .orElseThrow(() -> new InfraestructureNotFoundException("Infraestructure not found"));

        return convertToDTO(infraestructure);
    }

    /**
     * Retrieves a list of all infrastructures.
     *
     * @return a list of {@link InfraestructureDTO} representing all infrastructures
     */
    @Override
    public List<InfraestructureDTO> getAllInfraestructures() {
        return infraestructureRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    /**
     * Retrieves infrastructures by their name.
     *
     * @param name the name of the infrastructure to retrieve
     * @return a list of {@link InfraestructureDTO} representing the found infrastructures
     */
    @Override
    public List<InfraestructureDTO> getInfraestructureByName(String name) {
        return infraestructureRepository.findByName(name).stream()
                .map(this::convertToDTO)
                .toList();
    }

    /**
     * Retrieves infrastructures by their type.
     *
     * @param typeInfraestructure the type of infrastructure to retrieve
     * @return a list of {@link Infraestructure} entities matching the specified type
     */
    @Override
    public List<Infraestructure> getInfrestrucutureByType(TypeInfraestructure typeInfraestructure) {
        return infraestructureRepository.findInfraestructureByTypeInfraestructure(typeInfraestructure);
    }

    /**
     * Retrieves the resources associated with a given infrastructure type and name.
     *
     * @param typeInfraestructure the type of the infrastructure
     * @param name the name of the resource
     * @return a list of {@link Resource} representing the found resources
     */
    @Override
    public List<Resource> getResourceByInfraestructureAndName(TypeInfraestructure typeInfraestructure, String name) {
        // TODO: Implement this method
        throw new UnsupportedOperationException("Unimplemented method 'getResourceByInfraestructureAndName'");
    }

    /**
     * Deletes an infrastructure entity by its ID.
     *
     * @param id the ID of the infrastructure to delete
     */
    @Override
    public void deleteInfraestructure(Long id) {
        infraestructureRepository.findById(id).ifPresentOrElse(
                infraestructureRepository::delete,
                () -> {
                    throw new InfraestructureNotFoundException("Infraestructure not found");
                });
    }
}
