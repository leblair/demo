package com.example.demo.domain.model;

import javax.persistence.*;
import java.util.UUID;

//para que sea automatico
@Entity
//corresponde a:
@Table(name="movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID movieid;

    public String title;
    public String imageurl;
}
