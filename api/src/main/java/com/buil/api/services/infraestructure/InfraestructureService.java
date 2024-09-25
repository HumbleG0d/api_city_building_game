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
public class InfraestructureService implements IInfraestructureService {

    private final CityRepository cityRepository;
    private final InfraestructureRepository infraestructureRepository;
    private final ResourceRepository resourceRepository;
    private final ResourceService resourceService;

    @Override
    public InfraestructureDTO addInfraestructure(AddInfreaestructureRequest addInfreaestructureRequest, Long cityId) {
        /*TODO: Actualizar los recursos.*/
        City ciudad = cityRepository.findById(cityId)
                .orElseThrow(() -> new CityNotFoundException("Ciudad no encontrada"));

        Infraestructure infraestructure = createInfraestructure(addInfreaestructureRequest);

        infraestructure.setCity(ciudad);

        infraestructure = infraestructureRepository.save(infraestructure);

        ciudad.getInfraestructure().add(infraestructure);

        cityRepository.save(ciudad);

        if (infraestructure.getTypeInfraestructure().equals(TypeInfraestructure.RANCH)) {
            this.generateFarmResources();
        }

        return convertToDTO(infraestructure);
    }

    private Infraestructure createInfraestructure(AddInfreaestructureRequest addInfreaestructureRequest) {
        return new Infraestructure(addInfreaestructureRequest.getName() , addInfreaestructureRequest.getTypeInfraestructure() , addInfreaestructureRequest.getCost() , addInfreaestructureRequest.getTimeConstruction() , addInfreaestructureRequest.getAbility() , addInfreaestructureRequest.getDescription());
      }
    
      private InfraestructureDTO convertToDTO(Infraestructure infraestructure) {
        return new InfraestructureDTO(
                infraestructure.getName(),
                infraestructure.getTypeInfraestructure(),
                infraestructure.getCost(),
                infraestructure.getTimeConstruction(),
                infraestructure.getAbility()
        );
      }
    
      private void generateFarmResources(){
        List<Infraestructure> infraestructures = this.getInfrestrucutureByType(TypeInfraestructure.RANCH);
        infraestructures.forEach(infraestructure -> {
          int catidadProduccion = infraestructure.getAbility() + 5;
          infraestructure.setAbility(catidadProduccion);
          Resource resource = resourceRepository.findByCityAndTypeResource(infraestructure.getCity() , Type_resource.AGRICULTURAL);
            if(resource == null){
              resource = new Resource(infraestructure.getName() , Type_resource.AGRICULTURAL , catidadProduccion, infraestructure.getCity());
            }
          resource.setTotal(resource.getTotal() + catidadProduccion);
            resourceRepository.save(resource);
        resourceService.addResourceByFarm(resource , "Madera" , Type_resource.NATURAL , 5);
          resourceService.addResourceByFarm(resource , "Piedra" , Type_resource.NATURAL , 5);
          resourceService.addResourceByFarm(resource , "Tierra" , Type_resource.NATURAL , 5);
          resourceService.addResourceByFarm(resource , "Energia" , Type_resource.ENERGY, 10);
        });
      }

    @Override
    public InfraestructureDTO getInfraestructureById(Long id) {
        var infraestrucuture = infraestructureRepository.findById(id)
                .orElseThrow(() -> new InfraestructureNotFoundException("Infraestructure not found"));
        return convertToDTO(infraestrucuture);
    }

    @Override
    public List<Infraestructure> getAllInfraestructures() {
        return infraestructureRepository.findAll();
    }

    @Override
    public Infraestructure getInfraestructureByName(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInfraestructurePorNombre'");
    }

    @Override
    public List<Infraestructure> getInfrestrucutureByType(TypeInfraestructure typeInfraestructure) {
        return infraestructureRepository.findInfraestructureByTypeInfraestructure(typeInfraestructure);
    }

    @Override
    public List<Resource> getResourceByInfraestructureAndName(TypeInfraestructure typeInfraestructure, String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRecursoPorInfraestructureYnombre'");
    }

    @Override
    public void deleteInfraestructure(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteInfraestructure'");
    }
    
}
