package com.example.controllers;

import com.example.dto.LaudoDto;
import com.example.services.LaudoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/laudo")
public class LaudoController {

    @Autowired
    private LaudoService laudoService;

    @PostMapping
    public ResponseEntity<LaudoDto> create(@RequestBody LaudoDto laudoDto){
        LaudoDto laudoDtoNew = laudoService.create(laudoDto);
        return ResponseEntity.ok(laudoDtoNew);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LaudoDto> update(@RequestBody LaudoDto laudoDto,
                                           @RequestParam Integer id){
        LaudoDto laudoDtoUpdated = laudoService.update(laudoDto, id);
        return ResponseEntity.ok(laudoDtoUpdated);
    }

    @GetMapping
    public ResponseEntity<List<LaudoDto>> findAll(){
        List<LaudoDto> laudos = laudoService.findAll();
        return ResponseEntity.ok(laudos);
    }

}
