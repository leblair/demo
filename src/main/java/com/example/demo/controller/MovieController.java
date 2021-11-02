package com.example.demo.controller;

import com.example.demo.domain.model.Movie;
import com.example.demo.repository.MovieRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//los controladoress son los que escuchan las petiicones y escuchan en un endpoint
//los repositorios son los que acceden a la base de datos
//se puede usar el firefox y el comando "curl" en la terminal para acceder a los datos json
//Pero mejor postman
@RestController
@RequestMapping("/movies") //con que intencion lo vamos a mapear
//cuando pidan /hello accederan a este controlador
public class MovieController {
    //este es el endpoint, el que recibe las peticiones http://algo/movies hace la peticion y devuelve el json
    //el endpoint es lo que va despues del servidor

    private final MovieRepository movieRepository;
    MovieController(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    @GetMapping("/")
    public List<Movie> talycual(){
        return movieRepository.findAll();
    }

    @PostMapping("/")
    public Movie createMovie(@RequestBody Movie movie){
        return movieRepository.save(movie);
    }

}
