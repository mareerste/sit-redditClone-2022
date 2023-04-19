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
    @Field(type = FieldType.Double)
    private Double karma = 0.0;

    public PostES(){}

    public PostES(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.text = post.getText();
    }
}
