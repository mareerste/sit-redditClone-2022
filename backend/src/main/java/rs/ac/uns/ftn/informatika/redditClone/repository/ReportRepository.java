package rs.ac.uns.ftn.informatika.redditClone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.*;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report,Integer> {
    public List<Report> findAllByComment(Comment comment);
    public List<Report> findAllByCommentAndAccepted(Comment comment, Boolean accepted);
    public List<Report> findAllByCommentAndAcceptedAndUser(Comment comment, Boolean accepted,User user);
    public List<Report> findAllByPost(Post post);
    @Transactional
    public void deleteAllByComment(Comment comment);
    @Transactional
    public void deleteAllByPost(Post post);
    public List<Report> findAllByPostAndAccepted(Post post,Boolean accepted);
    public List<Report> findAllByPostAndAcceptedAndUser(Post post, Boolean accepted, User user);
//    SELECT * FROM report inner join community_posts on report.post_id = community_posts.post_id where community_id = 2;
    @Query(value = "select r.id,r.accepted,r.reason,r.comment_id,r.date,r.post_id,r.user_id from Report r inner join community_posts cp on r.post_id = cp.post_id where accepted is null and community_id =?1",nativeQuery = true)
    public List<Report> findCommunityReportedPosts(Integer communityId);
//select * from report where comment_id is not null and accepted is null;
    @Query(value = "select r.id,r.accepted,r.reason,r.date,r.comment_id,r.post_id,r.user_id from Report r where r.comment_id is not null and accepted is null",nativeQuery = true)
    public List<Report> findCommunityReportedComments();
}
