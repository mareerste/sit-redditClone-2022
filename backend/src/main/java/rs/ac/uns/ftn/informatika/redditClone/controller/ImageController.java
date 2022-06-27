package rs.ac.uns.ftn.informatika.redditClone.controller;


import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import rs.ac.uns.ftn.informatika.redditClone.repository.ImageRepository;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/image")
public class ImageController {

    @Autowired
    ImageRepository imageRepository;

    @PostMapping
    public ResponseEntity<Map<String,String>> uplaodImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
        Map<String,String> response = new HashMap<>();
        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        String path = imageRepository.save(file.getBytes(),file.getOriginalFilename());
        response.put("path",path);
        return new ResponseEntity(response, HttpStatus.OK);
    }


//    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
    @GetMapping(value = "/get")
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String urlImage = request.getParameter("path");
        response.setContentType("image/jpeg");
        ServletOutputStream out;
        out = response.getOutputStream();
        FileInputStream fin = new FileInputStream(urlImage);

        BufferedInputStream bin = new BufferedInputStream(fin);
        BufferedOutputStream bout = new BufferedOutputStream(out);
        int ch = 0;
        while ((ch = bin.read()) != -1) {
            bout.write(ch);
        }

        bin.close();
        fin.close();
        bout.close();
        out.close();
    }


//    @GetMapping(value = "/get")
//    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String urlImage = request.getParameter("path");
//        response.setContentType("image/jpeg");
//        ServletOutputStream out;
//        out = response.getOutputStream();
//        FileInputStream fin = new FileInputStream(urlImage);
//
//        BufferedInputStream bin = new BufferedInputStream(fin);
//        BufferedOutputStream bout = new BufferedOutputStream(out);
//        int ch = 0;
//        while ((ch = bin.read()) != -1) {
//            bout.write(ch);
//        }
//
//        bin.close();
//        fin.close();
//        bout.close();
//        out.close();
//    }

}
