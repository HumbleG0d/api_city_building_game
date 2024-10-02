package com.buil.api.services.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.buil.api.exceptions.ResourceNotFoundException;
import com.buil.api.model.City;
import com.buil.api.model.resource.Resource;
import com.buil.api.model.resource.Type_resource;
import com.buil.api.repository.ResourceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
/**
 * Service implementation for managing resources in a city.
 * Handles operations such as adding resources, initializing resources, and retrieving them.
 */
public class ResourceService implements IResourceService {
    
    // List to temporarily store resources for operations
    private final List<Resource> resources = new ArrayList<>();

    // Repository to interact with the Resource database
    private final ResourceRepository resourceRepository;

    /**
     * Adds a specified amount of resource to a city's farm.
     * Currently, this method only works for certain types of resources (e.g., "Madera", "Tierra", etc.).
     * 
     * @param city the city to which the resources belong
     * @param name the name of the resource being added (e.g., "Madera")
     * @param type_resource the type of the resource (NATURAL, ENERGY, etc.)
     * @param hability the amount of the resource being added
     */
    @Override
    public void addResourceByFarm(City city, String name, Type_resource type_resource, int hability) {
        city.getResources().stream().forEach(r -> {
            // Only add resources to specific types for now
            if (r.getName().equals("Madera") || r.getName().equals("Tierra") || r.getName().equals("Piedra") || r.getName().equals("Energia")) {
                r.setTotal(r.getTotal() + hability); // Update the total of the resource
            }
        });
    }

    /**
     * Initializes a list of resources for a new city with random starting values.
     * Saves the generated resources to the repository.
     * 
     * @param city the city to which the resources will be added
     * @return a list of the initialized resources
     */
    @Override
    public List<Resource> initialResources(City city) {
        // Create resources with random starting values and add to the city
        resources.add(new Resource("Madera", Type_resource.NATURAL, generetyQuantityResources(), city));
        resources.add(new Resource("Piedra", Type_resource.NATURAL, generetyQuantityResources(), city));
        resources.add(new Resource("Tierra", Type_resource.NATURAL, generetyQuantityResources(), city));
        resources.add(new Resource("Agua", Type_resource.NATURAL, generetyQuantityResources(), city));
        resources.add(new Resource("Arena", Type_resource.NATURAL, generetyQuantityResources(), city));
        resources.add(new Resource("Energia", Type_resource.ENERGY, generetyQuantityResources(), city));
        
        // Save all initialized resources to the database
        resourceRepository.saveAll(resources);
        return resources;
    }
    
    /**
     * Generates a random quantity for a resource between 50 and 100.
     * 
     * @return a randomly generated quantity for the resource
     */
    private int generetyQuantityResources() {
        return (int) (Math.random() * 50) + 50; // Generates a random number between 50 and 100
    }

    /**
     * Retrieves a list of resources belonging to a specific city by the city's ID.
     * 
     * @param cityId the ID of the city whose resources are being retrieved
     * @return a list of resources for the given city
     */
    @Override
    public List<Resource> getResourcesByCity(Long cityId) {
        return resourceRepository.findByCityId(cityId); // Fetch resources by city ID
    }

    /**
     * Calculates the total number of habilities/resources a city possesses by its ID.
     * 
     * @param cityId the ID of the city
     * @return the total number of resources for the city
     */
    @Override
    public int habilityResourcesByCity(Long cityId) {
        return resourceRepository.getTotalResourceByCity(cityId); // Sum of resources for the city
    }

    /**
     * Retrieves a specific resource by its ID.
     * Throws an exception if the resource is not found.
     * 
     * @param id the ID of the resource
     * @return the resource object
     * @throws ResourceNotFoundException if the resource is not found
     */
    @Override
    public Resource getResourceById(Long id) {
        return resourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found")); // Handle resource not found
    }

    /**
     * Updates a resource by its ID. Currently, this method is unimplemented.
     * 
     * @param id the ID of the resource to be updated
     * @param resource the updated resource data
     * @throws UnsupportedOperationException since the method is not yet implemented
     */
    @Override
    public Resource updateResource(Long id, Resource resource) {
        throw new UnsupportedOperationException("Unimplemented method 'updateResource'");
    }

    /**
     * Deletes a resource by its ID.
     * Throws an exception if the resource is not found.
     * 
     * @param id the ID of the resource to be deleted
     * @throws ResourceNotFoundException if the resource is not found
     */
    @Override
    public void deleteResource(Long id) {
        resourceRepository.findById(id).ifPresentOrElse(
            resourceRepository::delete,
            () -> { throw new ResourceNotFoundException("Resource not found"); }
        );
    }
}
