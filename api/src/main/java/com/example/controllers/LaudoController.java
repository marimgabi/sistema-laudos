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
                                           @PathVariable Integer id){
        LaudoDto laudoDtoUpdated = laudoService.update(laudoDto, id);
        return ResponseEntity.ok(laudoDtoUpdated);
    }

    @GetMapping
    public ResponseEntity<List<LaudoDto>> findAll(){
        List<LaudoDto> laudos = laudoService.findAllByUser();
        return ResponseEntity.ok(laudos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaudoDto> findById(@PathVariable Integer id){
        LaudoDto laudo = laudoService.findById(id);
        return ResponseEntity.ok(laudo);
    }

    @GetMapping("/{id}/inativate")
    public ResponseEntity<LaudoDto> inativateLaudo(@PathVariable Integer id){
        LaudoDto laudo = laudoService.inativate(id);
        return ResponseEntity.ok(laudo);
    }

}
