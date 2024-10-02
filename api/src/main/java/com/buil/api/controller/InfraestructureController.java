package com.buil.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buil.api.dto.InfraestructureDTO;
import com.buil.api.exceptions.InfraestructureNotFoundException;
import com.buil.api.model.infraestructure.Infraestructure;
import com.buil.api.model.infraestructure.TypeInfraestructure;
import com.buil.api.request.AddInfreaestructureRequest;
import com.buil.api.response.ApiResponse;
import com.buil.api.services.infraestructure.InfraestructureService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RequestMapping("${api.prefix}/infraestructures")
@RestController
@RequiredArgsConstructor
public class InfraestructureController {
    private final InfraestructureService infraestructureService;

    @PostMapping("/add/{id_city}")
    public ResponseEntity<ApiResponse<InfraestructureDTO>> addInfraestructure(
            @RequestBody AddInfreaestructureRequest addInfreaestructureRequest, @PathVariable Long id_city) {
        try {
            InfraestructureDTO infraestructureDTO = infraestructureService
                    .addInfraestructure(addInfreaestructureRequest, id_city);
            return ResponseEntity.ok(new ApiResponse<>("Infraestructure success", infraestructureDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(e.getMessage(), null));
        }
    }
    
    @GetMapping("/{id}/infraestructure")
    public ResponseEntity<ApiResponse<InfraestructureDTO>> getInfraestructure(@PathVariable Long id) {
        try {
            InfraestructureDTO infraestructureDTO = infraestructureService.getInfraestructureById(id);
            return ResponseEntity.ok(new ApiResponse<>("Infraestructure success", infraestructureDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(e.getMessage(), null));
        }
    }
    
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<InfraestructureDTO>>> getAllInfraestructures() {
        try {
            List<InfraestructureDTO> infraestructureDTOs = infraestructureService.getAllInfraestructures();
            return ResponseEntity.ok(new ApiResponse<>("Success", infraestructureDTOs));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(e.getMessage(), null));
        }
    }
    
    @GetMapping("/infraestructure/by-name")
    public ResponseEntity<ApiResponse<List<InfraestructureDTO>>> getInfraestructureByName(@RequestParam String name) {
        try {
            var infraestructure = infraestructureService.getInfraestructureByName(name);
            return ResponseEntity.ok(new ApiResponse<>("Success", infraestructure));
        } catch (InfraestructureNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(e.getMessage(), null));
        }
    }

    @GetMapping("/infraestructure/by-type")
    public ResponseEntity<ApiResponse<List<Infraestructure>>> getInfraestrucureByType(@RequestParam String type) {
        try {
            TypeInfraestructure typeInfraestructure = TypeInfraestructure.fromString(type);
            var infraestructure = infraestructureService.getInfrestrucutureByType(typeInfraestructure);
            return ResponseEntity.ok(new ApiResponse<>("Infrastructures found", infraestructure));
        } catch (InfraestructureNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("Infrastructures not found", null));
        }
    }
    
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<ApiResponse<InfraestructureDTO>> deleteInfraestructure(@PathVariable Long id) {
        try {
            infraestructureService.deleteInfraestructure(id);            
            return ResponseEntity.ok(new ApiResponse<>("Delete infraestrucutere success", infraestructureService.getInfraestructureById(id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(e.getMessage(), null));
        }
    }
}
