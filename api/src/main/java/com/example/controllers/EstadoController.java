package com.example.controllers;

import com.example.dto.EstadoDto;
import com.example.entities.Estado;
import com.example.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estado")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @GetMapping
    public ResponseEntity<List<EstadoDto>> findAll(){
        List<EstadoDto> estadoList = estadoService.findAll();
        return ResponseEntity.ok(estadoList);
    }

}
