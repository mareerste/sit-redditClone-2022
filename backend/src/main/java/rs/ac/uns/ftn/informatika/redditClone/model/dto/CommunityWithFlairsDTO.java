package rs.ac.uns.ftn.informatika.redditClone.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Community;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Flair;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Moderator;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.User;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class CommunityWithFlairsDTO implements Serializable {
    private Integer id;
    private String name;
    private String description;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate creationDate;
    private Set<String> rules = new HashSet<>();
    private Boolean isSuspended;
    private String suspendedReason;
    private List<Flair> flairs = new ArrayList<>();
    private Set<User> moderators = new HashSet<>();
    private MultipartFile[] files;
    private Integer karma = 0;

    public CommunityWithFlairsDTO() {
    }

    public CommunityWithFlairsDTO(Integer id, String name, String description, LocalDate creationDate, Set<String> rules, Boolean isSuspended, String suspendedReason, Set<User> moderators, List<Flair> flairs) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.rules = rules;
        this.isSuspended = isSuspended;
        this.suspendedReason = suspendedReason;
        this.moderators = moderators;
        this.flairs = flairs;
    }
    public CommunityWithFlairsDTO(Community community){this(community.getId(), community.getName(), community.getDescription(), community.getCreationDate(), community.getRules(),community.getSuspended(), community.getSuspendedReason(), community.getModerators(), community.getFlairs());}
    @Override
    public String toString() {
        return "Community{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", rules=" + rules +
                ", isSuspended=" + isSuspended +
                ", suspendedReason='" + suspendedReason + '\'' +
                '}';
    }
}
