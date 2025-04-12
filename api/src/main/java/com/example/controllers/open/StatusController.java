package com.example.controllers.open;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
public class StatusController {

    @GetMapping
    public String pong(){
        return "Pong";
    }

}
