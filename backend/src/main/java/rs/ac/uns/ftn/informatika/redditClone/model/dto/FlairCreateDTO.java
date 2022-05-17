package rs.ac.uns.ftn.informatika.redditClone.model.dto;

import rs.ac.uns.ftn.informatika.redditClone.model.entity.Flair;

import java.io.Serializable;

public class FlairCreateDTO  implements Serializable {
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

    public FlairCreateDTO(){
    }

    public FlairCreateDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public FlairCreateDTO(Flair flair){this(flair.getId(), flair.getName());}
    @Override
    public String toString() {
        return "Flair{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
