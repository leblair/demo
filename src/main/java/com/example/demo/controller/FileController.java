package com.example.demo.controller;

import com.example.demo.domain.model.FileTable;
import com.example.demo.domain.model.Movie;
import com.example.demo.repository.FileRepository;
import com.example.demo.repository.MovieRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public byte[] getId(@PathVariable UUID id) {
        FileTable fileTable = fileRepository.getById(id);

        return fileTable.data; //para solo devolver los bytes de la imagen
    }

    @PostMapping
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

    public List<FileTable> talycual() {
        return fileRepository.findAll();
    }
//request tipo post
    //http get o post, si es por get i "/" usa este
    @PostMapping("/")
    public FileTable createMovie(@RequestBody FileTable fileTable) {
        return fileRepository.save(fileTable);
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
