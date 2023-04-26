package rs.ac.uns.ftn.informatika.redditClone.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.CommunityES;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class CommunitySearchDTO {
    private Integer id;
    private String name;
    private Integer countPost;
    private Integer karma;

    public CommunitySearchDTO(CommunityES communityES){this(communityES.getId(), communityES.getName(), communityES.getPosts().size(), communityES.getKarma());}
    public CommunitySearchDTO(){}
}
