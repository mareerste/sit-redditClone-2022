package rs.ac.uns.ftn.informatika.redditClone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.informatika.redditClone.model.Comment;
import rs.ac.uns.ftn.informatika.redditClone.model.Post;
import rs.ac.uns.ftn.informatika.redditClone.model.Report;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report,Integer> {
    public List<Report> findAllByComment(Comment comment);
    public List<Report> findAllByCommentAndAccepted(Comment comment, Boolean accepted);
    public List<Report> findAllByPost(Post post);
    public List<Report> findAllByPostAndAccepted(Post post,Boolean accepted);
}
