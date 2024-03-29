package rs.ac.uns.ftn.informatika.redditClone.model.dto;

import rs.ac.uns.ftn.informatika.redditClone.model.entity.Flair;

import javax.persistence.*;
import java.io.Serializable;

public class FlairDTO implements Serializable {

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public FlairDTO(){
    }

    public FlairDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public FlairDTO(Flair flair){this(flair.getId(), flair.getName());}
    @Override
    public String toString() {
        return "Flair{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
