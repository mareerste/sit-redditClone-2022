package rs.ac.uns.ftn.informatika.redditClone.service;

import rs.ac.uns.ftn.informatika.redditClone.model.entity.Comment;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Post;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Report;

import java.util.List;

public interface ReportService {
    Report findOne(Integer id);
    List<Report> findAll();
    List<Report> findAllByComment(Comment comment);
    List<Report> findAllByCommentToAnswer(Comment comment);
    List<Report> findAllByPost(Post post);

    List<Report> findAllByPostToAnswer(Post post);
    Report save(Report report);
    void delete(Report report);
    Report acceptReport(Report report);
    Report declineReport(Report report);
}
