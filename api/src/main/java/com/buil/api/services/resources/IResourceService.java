package com.buil.api.services.resources;

import java.util.List;

import com.buil.api.model.City;
import com.buil.api.model.resource.Resource;
import com.buil.api.model.resource.Type_resource;

public interface IResourceService {
    void addResourceByFarm(Resource resource , String name , Type_resource type_resource , int hability);
    List<Resource> initialResources(City city);
    List<Resource> getResourcesByCity(Long cityId);
    int habilityResourcesByCity(Long cityId);
    Resource getResourceById(Long id);
    Resource updateResource(Long id, Resource resource);
    void deleteResource(Long id);
}
