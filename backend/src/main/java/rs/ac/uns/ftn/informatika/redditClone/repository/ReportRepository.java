package rs.ac.uns.ftn.informatika.redditClone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Comment;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Post;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Reaction;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Report;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report,Integer> {
    public List<Report> findAllByComment(Comment comment);
    public List<Report> findAllByCommentAndAccepted(Comment comment, Boolean accepted);
    public List<Report> findAllByPost(Post post);
    public List<Report> findAllByPostAndAccepted(Post post,Boolean accepted);
//    SELECT * FROM report inner join community_posts on report.post_id = community_posts.post_id where community_id = 2;
    @Query(value = "select r.id,r.accepted,r.reason,r.comment_id,r.date,r.post_id,r.user_id from Report r inner join community_posts cp on r.post_id = cp.post_id where accepted is null and community_id =?1",nativeQuery = true)
    public List<Report> findCommunityReportedPosts(Integer communityId);
//select * from report where comment_id is not null and accepted is null;
    @Query(value = "select r.id,r.accepted,r.reason,r.date,r.comment_id,r.post_id,r.user_id from Report r where r.comment_id is not null and accepted is null",nativeQuery = true)
    public List<Report> findCommunityReportedComments();
}
