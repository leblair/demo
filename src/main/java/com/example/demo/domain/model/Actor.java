package com.example.demo.domain.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name="actor")
public class Actor {
    @Id
    public UUID actorid;

    public String name;
    public String imageurl;
}
