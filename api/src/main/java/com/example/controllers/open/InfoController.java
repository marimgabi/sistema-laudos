package com.example.controllers.open;

import com.example.Enums.EnumSexo;
import com.example.Enums.EnumStatus;
import com.example.Enums.EnumTipoMedico;
import com.example.Enums.EnumTipoTemplate;
import com.example.dto.RoleDto;
import com.example.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/info")
public class InfoController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/roles")
    public ResponseEntity<List<RoleDto>> getAllRoles(){
        List<RoleDto> roles = roleService.findAll();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/enum/status")
    public List<String> getStatusValues() {
        return Arrays.stream(EnumStatus.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @GetMapping("/enum/tipo-medico")
    public List<String> getTipoMedicoValues() {
        return Arrays.stream(EnumTipoMedico.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @GetMapping("/enum/tipo-template")
    public List<String> getTipoTemplateValues() {
        return Arrays.stream(EnumTipoTemplate.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @GetMapping("/enum/sexo")
    public List<String> getSexoValues() {
        return Arrays.stream(EnumSexo.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

}
