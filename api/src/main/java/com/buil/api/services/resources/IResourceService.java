package com.buil.api.services.resources;

import java.util.List;

import com.buil.api.model.City;
import com.buil.api.model.resource.Resource;
import com.buil.api.model.resource.Type_resource;

/**
 * Service interface for managing resources within a city.
 * Defines operations for adding, retrieving, updating, and deleting resources, as well as specific functionalities such as calculating resource abilities.
 */
public interface IResourceService {

    /**
     * Adds a new resource to a city through farming.
     *
     * @param city the city to which the resource will be added
     * @param name the name of the resource
     * @param type_resource the type of resource (e.g., NATURAL, ENERGY, AGRICULTURAL)
     * @param hability the ability level associated with the resource
     */
    void addResourceByFarm(City city, String name, Type_resource type_resource, int hability);

    /**
     * Initializes default resources for a city.
     *
     * @param city the city for which the resources will be initialized
     * @return a list of initialized {@link Resource} entities
     */
    List<Resource> initialResources(City city);

    /**
     * Retrieves a list of resources associated with a specific city by its ID.
     *
     * @param cityId the ID of the city whose resources are to be retrieved
     * @return a list of {@link Resource} entities belonging to the specified city
     */
    List<Resource> getResourcesByCity(Long cityId);

    /**
     * Calculates the total ability score of all resources in a specific city by its ID.
     *
     * @param cityId the ID of the city whose resource abilities are to be calculated
     * @return the total ability score of the city's resources
     */
    int habilityResourcesByCity(Long cityId);

    /**
     * Retrieves a resource by its ID.
     *
     * @param id the ID of the resource to be retrieved
     * @return the found {@link Resource} entity
     */
    Resource getResourceById(Long id);

    /**
     * Updates a resource based on its ID and new resource details.
     *
     * @param id the ID of the resource to be updated
     * @param resource the updated {@link Resource} entity
     * @return the updated {@link Resource} entity
     */
    Resource updateResource(Long id, Resource resource);

    /**
     * Deletes a resource by its ID.
     *
     * @param id the ID of the resource to be deleted
     */
    void deleteResource(Long id);
}
