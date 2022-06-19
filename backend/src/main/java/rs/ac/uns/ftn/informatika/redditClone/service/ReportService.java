package rs.ac.uns.ftn.informatika.redditClone.service;

import rs.ac.uns.ftn.informatika.redditClone.model.entity.*;

import java.util.List;

public interface ReportService {
    Report findOne(Integer id);
    List<Report> findAll();
    List<Report> findAllByComment(Comment comment);
    List<Report> findAllByCommentToAnswer(Comment comment, User user);
    List<Report> findAllByPost(Post post);
    List<Report> findReportedCommunityPosts(Community community);
    List<Report> findReportedCommunityComments();
    List<Report> findAllByPostToAnswer(Post post, User user);
    void deleteAllByPost(Post post);
    void deleteAllByComment(Comment comment);
    Report save(Report report);
    void delete(Report report);
    Report acceptReport(Report report);
    Report declineReport(Report report);
}
