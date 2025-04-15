package com.example.controllers;

import com.example.Enums.EnumTipoMedico;
import com.example.dto.MedicoDto;
import com.example.services.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public ResponseEntity<List<MedicoDto>> getAll(){
        List<MedicoDto> medicoDtoList = medicoService.findAll();
        return ResponseEntity.ok(medicoDtoList);
    }

    @GetMapping("/solicitante")
    public ResponseEntity<List<MedicoDto>> getAllSolicitante(){
        List<MedicoDto> medicos = medicoService.findAllMedicosByTipo(EnumTipoMedico.SOLICITANTE);
        return ResponseEntity.ok(medicos);
    }
}
