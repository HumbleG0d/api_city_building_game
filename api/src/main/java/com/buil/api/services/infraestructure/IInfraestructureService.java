package com.buil.api.services.infraestructure;

import java.util.List;

import com.buil.api.dto.InfraestructureDTO;
import com.buil.api.model.infraestructure.Infraestructure;
import com.buil.api.model.infraestructure.TypeInfraestructure;
import com.buil.api.model.resource.Resource;
import com.buil.api.request.AddInfreaestructureRequest;

/**
 * Service interface for managing infrastructure-related operations.
 * Defines methods for adding, retrieving, and deleting infrastructure entities.
 */
public interface IInfraestructureService {

    /**
     * Adds a new infrastructure entity to a specific city.
     *
     * @param addInfreaestructureRequest the request object containing infrastructure details
     * @param cityId the ID of the city to which the infrastructure belongs
     * @return an {@link InfraestructureDTO} representing the newly added infrastructure
     */
    InfraestructureDTO addInfraestructure(AddInfreaestructureRequest addInfreaestructureRequest, Long cityId);

    /**
     * Retrieves infrastructure by its ID.
     *
     * @param id the ID of the infrastructure to retrieve
     * @return an {@link InfraestructureDTO} representing the retrieved infrastructure
     */
    InfraestructureDTO getInfraestructureById(Long id);

    /**
     * Retrieves a list of all infrastructures.
     *
     * @return a list of {@link InfraestructureDTO} representing all infrastructures
     */
    List<InfraestructureDTO> getAllInfraestructures();

    /**
     * Retrieves infrastructure entities by their name.
     *
     * @param name the name of the infrastructure to retrieve
     * @return a list of {@link InfraestructureDTO} representing the found infrastructures
     */
    List<InfraestructureDTO> getInfraestructureByName(String name);

    /**
     * Retrieves infrastructure entities by their type.
     *
     * @param typeInfraestructure the type of infrastructure to retrieve
     * @return a list of {@link Infraestructure} representing the found infrastructures
     */
    List<Infraestructure> getInfrestrucutureByType(TypeInfraestructure typeInfraestructure);

    /**
     * Retrieves the resources associated with a given infrastructure type and name.
     *
     * @param typeInfraestructure the type of the infrastructure
     * @param name the name of the resource
     * @return a list of {@link Resource} representing the found resources
     */
    List<Resource> getResourceByInfraestructureAndName(TypeInfraestructure typeInfraestructure, String name);

    /**
     * Deletes an infrastructure entity by its ID.
     *
     * @param id the ID of the infrastructure to delete
     */
    void deleteInfraestructure(Long id);
}
