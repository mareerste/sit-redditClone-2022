package rs.ac.uns.ftn.informatika.redditClone.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;

@Setter
@Getter
@Builder
@AllArgsConstructor
@Document(indexName = "posts")
public class PostES {

    @Id
    private Integer id;
    @Field(type = FieldType.Text)
    private String title;
    @Field(type = FieldType.Text)
    private String text;
    @Field(type = FieldType.Text)
    private String textFile;
    @Field(type = FieldType.Keyword)
    private String keywords;
    private String filename;
    @Field(type = FieldType.Integer)
    private Integer karma = 0;
    @Field(type = FieldType.Integer)
    private Integer comments = 0;
    @Field(type = FieldType.Text)
    private String flair;

    public void karmaUp() {
        this.karma = ++this.karma;
    }

    public void karmaDown() {this.karma = --this.karma;}

    public void addComm() {this.comments = ++this.comments;}
    public void removeComm() {this.comments = --this.comments;}

    public PostES(){}

    public PostES(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.text = post.getText();
    }
}
