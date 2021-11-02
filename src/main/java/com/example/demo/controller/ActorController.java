package com.example.demo.controller;


import com.example.demo.domain.model.Actor;
import com.example.demo.repository.ActorRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/actor")
public class ActorController {

    private final ActorRepository actorRepository;
    ActorController(ActorRepository actorRepository){
        this.actorRepository = actorRepository;
    }

    @GetMapping("/")
    public List<Actor> talycual(){
        return actorRepository.findAll();
    }


}
