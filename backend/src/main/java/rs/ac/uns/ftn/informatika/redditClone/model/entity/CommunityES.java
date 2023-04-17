package rs.ac.uns.ftn.informatika.redditClone.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.CommunityPostESDTO;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Setter
@Getter
@Builder
@AllArgsConstructor
@Document(indexName = "communities")
//@Setting(settingPath = "/analyzers/serbianAnalyzer.json")
public class CommunityES {
    @Id
    private Integer id;
    @Field(type = FieldType.Text)
    private String name;
    @Field(type = FieldType.Text)
    private String description;
    @Field(type = FieldType.Text)
    private String descriptionFile;
    @JsonFormat(pattern = "MM/dd/yyyy")
    @Field(type = FieldType.Date)
    private LocalDate creationDate;
    @ElementCollection
    @Field(type = FieldType.Text)
    private Set<String> rules = new HashSet<>();
    @Field(type = FieldType.Boolean)
    private Boolean isSuspended;
    @Field(type = FieldType.Object)
    private List<Flair> flairs = new ArrayList<>();
    @Field(type = FieldType.Object)
    private Set<CommunityPostESDTO> posts = new HashSet<>();
    @Field(type = FieldType.Keyword)
    private String keywords;
    private String filename;

    public CommunityES(){}

    public CommunityES(Community community){
        this.id = community.getId();
        this.name = community.getName();
        this.description = community.getDescription();
        this.creationDate = community.getCreationDate();
        this.rules = community.getRules();
        this.isSuspended = community.getIsSuspended();
        this.flairs = community.getFlairs();
        this.posts = getCommunityPosts(community.getPosts());

    }

    private Set<CommunityPostESDTO> getCommunityPosts(Set<Post> posts){
        Set<CommunityPostESDTO> retVal = new HashSet<>();
        for (Post post:posts) {
            retVal.add(new CommunityPostESDTO(post));
        }
        return retVal;
    }
}
