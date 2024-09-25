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
public class ResourceService implements IResourceService {
    
    private final List<Resource> resources = new ArrayList<>();
    private final ResourceRepository resourceRepository;

    @Override
    public void addResourceByFarm(Resource resource, String name, Type_resource type_resource, int hability) {
        /*TODO: Este resource se actualiza a partir del farmeo , el farme nos dara TIERRA , AGUA , ENERGIA , PIEDRA(5 de cada uno). Implementar la logica de si se crea un chacra entonces se genera un nuevo resource*/
        resource = new Resource(name , type_resource , hability , resource.getCity());
        resourceRepository.save(resource);
    }

    @Override
    public List<Resource> initialResources(City city) {
        resources.add(new Resource("Madera", Type_resource.NATURAL, generetyQuantityResources(), city));
        resources.add(new Resource("Piedra", Type_resource.NATURAL, generetyQuantityResources(), city));
        resources.add(new Resource("Tierra", Type_resource.NATURAL, generetyQuantityResources(), city));
        resources.add(new Resource("Agua", Type_resource.NATURAL, generetyQuantityResources(), city));
        resources.add(new Resource("Arena", Type_resource.NATURAL, generetyQuantityResources(), city));
        resources.add(new Resource("Energia", Type_resource.ENERGY, generetyQuantityResources(), city));
        resourceRepository.saveAll(resources);
        return resources;
    }
    
    private int generetyQuantityResources(){
        return (int) (Math.random() * 50) + 50;
      }

    @Override
    public List<Resource> getResourcesByCity(Long cityId) {
        return resourceRepository.findByCityId(cityId);
    }

    @Override
    public int habilityResourcesByCity(Long cityId) {
        return resourceRepository.getTotalResourceByCity(cityId);
    }

    @Override
    public Resource getResourceById(Long id) {
        return resourceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
    }

    @Override
    public Resource updateResource(Long id, Resource resource) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateResource'");
    }

    @Override
    public void deleteResource(Long id) {
        resourceRepository.findById(id).ifPresentOrElse(resourceRepository::delete, () -> {
            throw new ResourceNotFoundException("Resource not found");
       });
    }
    
}
