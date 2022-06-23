package rs.ac.uns.ftn.informatika.redditClone.controller;


import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import rs.ac.uns.ftn.informatika.redditClone.repository.ImageRepository;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/image")
public class ImageController {

    @Autowired
    ImageRepository imageRepository;

    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
    @PostMapping
    public ResponseEntity<Map<String,String>> uplaodImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
        Map<String,String> response = new HashMap<>();
        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        String path = imageRepository.save(file.getBytes(),file.getOriginalFilename());
        response.put("path",path);
        return new ResponseEntity(response, HttpStatus.OK);
    }
//    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
//    @GetMapping
//    public ResponseEntity<Map<String, ServletOutputStream>> getImage(@RequestParam("path") String imageName, HttpServletResponse response) throws IOException {
//        Map<String,ServletOutputStream> responseSend = new HashMap<>();
//        File file = new File(imageName);
//        response.setHeader("Content-Length", String.valueOf(file.length()));
//        response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
//        Files.copy(file.toPath(), response.getOutputStream());
//        responseSend.put("image",response.getOutputStream());
//        return new ResponseEntity(response.getOutputStream(), HttpStatus.OK);
//    }

    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
    @GetMapping
    public @ResponseBody byte[] getImage(@RequestParam("path") String imageName, HttpServletResponse response) throws IOException {
//        Map<String,ServletOutputStream> responseSend = new HashMap<>();
//        File file = new File(imageName);
//        response.setHeader("Content-Length", String.valueOf(file.length()));
//        response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
//        Files.copy(file.toPath(), response.getOutputStream());
//        responseSend.put("image",response.getOutputStream());

//        InputStream in = getClass()
//                .getResourceAsStream("/com/baeldung/produceimage/image.jpg");

        InputStream in = getClass()
                .getResourceAsStream(imageName);

        System.out.println(in);

        return IOUtils.toByteArray(in);
    }

//    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
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

    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
    @GetMapping(value = "/get")
    public ResponseEntity<Map<String,String>> doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String urlImage = request.getParameter("path");
        response.setContentType("image/jpeg");
        ServletOutputStream out;
        out = response.getOutputStream();
        FileInputStream fin = new FileInputStream(urlImage);
        Map<String, BufferedOutputStream> responseValue = new HashMap<>();

        BufferedInputStream bin = new BufferedInputStream(fin);
        BufferedOutputStream bout = new BufferedOutputStream(out);
        int ch = 0;
        while ((ch = bin.read()) != -1) {
            bout.write(ch);
        }
        responseValue.put("image",bout);

        bin.close();
        fin.close();
        bout.close();
        out.close();
        return new ResponseEntity(responseValue, HttpStatus.OK);
    }

}
