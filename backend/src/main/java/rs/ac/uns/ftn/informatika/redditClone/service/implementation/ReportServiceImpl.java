package rs.ac.uns.ftn.informatika.redditClone.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.*;
import rs.ac.uns.ftn.informatika.redditClone.repository.ReportRepository;
import rs.ac.uns.ftn.informatika.redditClone.service.ReportService;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportRepository reportRepository;
    @Override
    public Report findOne(Integer id){return reportRepository.findById(id).orElse(null);}
    @Override
    public List<Report> findAll(){return reportRepository.findAll();}
    @Override
    public List<Report> findAllByComment(Comment comment){return reportRepository.findAllByComment(comment);}
    @Override
    public List<Report> findAllByCommentToAnswer(Comment comment, User user){return reportRepository.findAllByCommentAndAcceptedAndUser(comment, null, user);}
    @Override
    public List<Report> findAllByPost(Post post){return reportRepository.findAllByPost(post);}

    @Override
    public List<Report> findReportedCommunityPosts(Community community) {
        return reportRepository.findCommunityReportedPosts(community.getId());
    }
    @Override
    public List<Report> findReportedCommunityComments() {
        return reportRepository.findCommunityReportedComments();
    }

    @Override
    public List<Report> findAllByPostToAnswer(Post post, User user){return reportRepository.findAllByPostAndAcceptedAndUser(post, null,user);}

    @Override
    public void deleteAllByPost(Post post) {
        reportRepository.deleteAllByPost(post);
    }

    @Override
    public void deleteAllByComment(Comment comment) {
        reportRepository.deleteAllByComment(comment);
    }

    @Override
    public Report save(Report report){return reportRepository.save(report);}
    @Override
    public void delete(Report report){reportRepository.delete(report);}
    @Override
    public Report acceptReport(Report report){
        report.setAccepted(true);
        return report;
    }
    @Override
    public Report declineReport(Report report){
        report.setAccepted(false);
        return report;
    }
}
