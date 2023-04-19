package rs.ac.uns.ftn.informatika.redditClone.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.PostES;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class PostSearchDTO {
    private Integer id;
    private String title;
    private String text;

    public PostSearchDTO (PostES post){this(post.getId(), post.getTitle(), post.getText());}
    public PostSearchDTO(){}
}
