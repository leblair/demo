package com.example.demo.controller;

import com.example.demo.domain.model.FileTable;
import com.example.demo.domain.model.Movie;
import com.example.demo.repository.FileRepository;
import com.example.demo.repository.MovieRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//autenticacion identificarse, con password
//autorizacion dar permisos para hacer comentarios, dar like etc
//se hace con servidores de autenticacion
//usuario y contraselna ira a springboot y de ahi a la base de datos YA NO SE HACE POR TEMAS DE SEGURIDAD HACKING
//no tiene que quedarse en la aplicación
//instalamos un servidor de autorizacion, un boton de logearse en el servidor, el servidor devuelve una url, el movil te lleva a la pagina del servidor
//esa web al logearse va al servidor, este envia un jwt un hash de numeros tokens para que la aplicacion valide si son los tokens correctos
//mirar Keykloack
//auth0.com dan 7000 cuentas de usuaario para probar gratis
//usar el de google


import java.util.List;
import java.util.UUID;

//los controladoress son los que escuchan las petiicones y escuchan en un endpoint
//los repositorios son los que acceden a la base de datos
//se puede usar el firefox y el comando "curl" en la terminal para acceder a los datos json
//Pero mejor postman
@RestController
@RequestMapping("/files") //con que intencion lo vamos a mapear
//cuando pidan /hello accederan a este controlador
public class FileController {
    //este es el endpoint, el que recibe las peticiones http://algo/movies hace la peticion y devuelve el json
    //el endpoint es lo que va despues del servidor

    private final FileRepository fileRepository;

    FileController(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }
//peticion por get y "/algo"
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getId(@PathVariable UUID id) {
        FileTable file = fileRepository.findById(id).orElse(null);

        if (file == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(file.contenttype))
                .contentLength(file.data.length)
                .body(file.data);
    }

    @PostMapping("/")
    public String upload(@RequestParam("file") MultipartFile uploadedFile) {
        try {
            System.out.println(uploadedFile.getOriginalFilename() + ", " + uploadedFile.getContentType());
            FileTable file = new FileTable();
            file.contenttype = uploadedFile.getContentType();
            file.data = uploadedFile.getBytes();

            return fileRepository.save(file).fileid.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping
    public String hack() {
        List<String> files = fileRepository.getFileIds();

        String filesStr = "";
        for (String file : files) {
            filesStr += "<img src='/files/"+file+"' style='width:15em'>";
        }

        return "<form method=\"POST\" enctype=\"multipart/form-data\" style=\"display:flex;\">\n" +
                "    <input id=\"file\" type=\"file\" name=\"file\" style=\"display:none\" onchange=\"document.getElementById('preview').src=window.URL.createObjectURL(event.target.files[0])\">\n" +
                "    <label for=\"file\" style=\"border: 1px dashed #999\">\n" +
                "        <img id=\"preview\" src=\"\" style=\"width:64px;\">\n" +
                "    </label>\n" +
                "    <input type=\"submit\" style=\"background:#0096f7;color: white;border: 0;border-radius: 3px;padding: 8px;\" value=\"Upload\">\n" +
                "</form>\n <div style='display:flex;flex-wrap:wrap;gap:1em;'>" + filesStr + "</div>";
    }

}
