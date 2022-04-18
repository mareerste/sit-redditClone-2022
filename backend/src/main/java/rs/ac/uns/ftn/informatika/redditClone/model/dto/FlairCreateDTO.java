package rs.ac.uns.ftn.informatika.redditClone.model.dto;

import rs.ac.uns.ftn.informatika.redditClone.model.entity.Flair;

import java.io.Serializable;

public class FlairCreateDTO  implements Serializable {
    private Integer id;
    private String name;
    private CommunityDTO community;

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
    public CommunityDTO getCommunity() {
        return community;
    }
    public void setCommunity(CommunityDTO community) {
        this.community = community;
    }

    public FlairCreateDTO(){
    }

    public FlairCreateDTO(Integer id, String name, CommunityDTO community) {
        this.id = id;
        this.name = name;
        this.community = community;
    }

    public FlairCreateDTO(Flair flair){this(flair.getId(), flair.getName(), new CommunityDTO(flair.getCommunity()));}
    @Override
    public String toString() {
        return "Flair{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
