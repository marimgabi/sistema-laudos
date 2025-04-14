package com.example.controllers;

import com.example.dto.TemplateDto;
import com.example.services.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/template")
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @PostMapping
    public ResponseEntity<TemplateDto> create(@RequestBody TemplateDto templateDto){
        TemplateDto created = templateService.createTemplate(templateDto);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/medico")
    public ResponseEntity<List<TemplateDto>> findAllByMedico(){
        List<TemplateDto> templates = templateService.findByMedicoLogado();
        return ResponseEntity.ok(templates);
    }
}
