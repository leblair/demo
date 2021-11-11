package com.example.demo.repository;

import com.example.demo.domain.model.FileTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.File;
import java.util.List;
import java.util.UUID;

public interface FileRepository extends JpaRepository<FileTable, UUID> {
//aqui estan las consultas que queramos hacer
    //esta en blanco porque spring hace las consultas automaticamente
    //genera la clase por cada consulta con interface
    @Query("select fileid from File")
    List<String> getFileIds();


}
