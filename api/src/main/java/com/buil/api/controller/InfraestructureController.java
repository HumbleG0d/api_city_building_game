package com.buil.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buil.api.dto.InfraestructureDTO;
import com.buil.api.request.AddInfreaestructureRequest;
import com.buil.api.response.ApiResponse;
import com.buil.api.services.infraestructure.InfraestructureService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RequestMapping("${api.prefix}/infraestructure")
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
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<InfraestructureDTO>> getInfraestructure(@PathVariable Long id) {
        try {
            InfraestructureDTO infraestructureDTO = infraestructureService.getInfraestructureById(id);
            return ResponseEntity.ok(new ApiResponse<>("Infraestructure success", infraestructureDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(e.getMessage(), null));
        }
    }
    
    
}
