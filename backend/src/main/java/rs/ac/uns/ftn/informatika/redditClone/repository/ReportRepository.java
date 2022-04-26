package rs.ac.uns.ftn.informatika.redditClone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Comment;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Post;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Reaction;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Report;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report,Integer> {
    public List<Report> findAllByComment(Comment comment);
    public List<Report> findAllByCommentAndAccepted(Comment comment, Boolean accepted);
    public List<Report> findAllByCommentAndRespond(Comment comment, Boolean respond);
    public List<Report> findAllByPost(Post post);
    public List<Report> findAllByPostAndAccepted(Post post,Boolean accepted);
    public List<Report> findAllByPostAndRespond(Post post,Boolean respond);
    public List<Report> findAllByRespond(Boolean respond);
}
