package rs.ac.uns.ftn.informatika.redditClone.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Community;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.CommunityES;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class CommunitySearchDTO {
    private Integer id;
    private String name;
    private Integer countPost;

    public CommunitySearchDTO(CommunityES communityES){this(communityES.getId(), communityES.getName(), communityES.getPosts().size());}
    public CommunitySearchDTO(){}
}
