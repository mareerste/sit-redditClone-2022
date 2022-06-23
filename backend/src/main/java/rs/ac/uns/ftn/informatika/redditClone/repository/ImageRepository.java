package rs.ac.uns.ftn.informatika.redditClone.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Repository
public class ImageRepository {
    @Value("C:\\Users\\HP\\Documents\\Predavanja\\Semestar 4\\Project_RedditClone_2022\\image\\")
    private String path ;

    public String save(byte[] bytes, String name){
        Path newPath = Path.of(path + name);
        try{
            Files.write(newPath,bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("newPath" + newPath);
        return newPath.toString();
    }
}
