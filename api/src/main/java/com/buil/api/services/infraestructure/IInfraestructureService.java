package com.buil.api.services.infraestructure;

import java.util.List;

import com.buil.api.dto.InfraestructureDTO;
import com.buil.api.model.infraestructure.Infraestructure;
import com.buil.api.model.infraestructure.TypeInfraestructure;
import com.buil.api.model.resource.Resource;
import com.buil.api.request.AddInfreaestructureRequest;

public interface IInfraestructureService {
    InfraestructureDTO addInfraestructure(AddInfreaestructureRequest addInfreaestructureRequest, Long cityId);
    InfraestructureDTO getInfraestructureById(Long id);
    List<Infraestructure> getAllInfraestructures();
    Infraestructure getInfraestructureByName(String name);
    List<Infraestructure> getInfrestrucutureByType(TypeInfraestructure typeInfraestructure);
    List<Resource> getResourceByInfraestructureAndName(TypeInfraestructure typeInfraestructure, String name);
    void deleteInfraestructure(Long id);
}
