package com.example.demo.domain.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;
//para que sea automatico
@Entity
//corresponde a:
@Table(name="file")
public class FileTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID fileid;

    public String contenttype;

    @Lob//para saber que es un array
    @Type(type="org.hibernate.type.BinaryType")
    public byte[] data;
}
