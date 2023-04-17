package rs.ac.uns.ftn.informatika.redditClone.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Post;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class CommunityPostESDTO {
    private Integer id;
    private Integer karma = 0;

    public CommunityPostESDTO() {
    }
    public CommunityPostESDTO(Post post) {
        this.id = post.getId();
        this.karma = 0;
    }
    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                '}';
    }
}
