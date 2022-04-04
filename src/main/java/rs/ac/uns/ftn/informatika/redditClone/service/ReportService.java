package rs.ac.uns.ftn.informatika.redditClone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.redditClone.model.Comment;
import rs.ac.uns.ftn.informatika.redditClone.model.Post;
import rs.ac.uns.ftn.informatika.redditClone.model.Report;
import rs.ac.uns.ftn.informatika.redditClone.repository.ReportRepository;

import java.util.List;

@Service
public class ReportService {
    @Autowired
    private ReportRepository reportRepository;

    public Report findOne(Integer id){return reportRepository.findById(id).orElseGet(null);}
    public List<Report> findAll(){return reportRepository.findAll();}
    public List<Report> findAllByComment(Comment comment){return reportRepository.findAllByComment(comment);}
    public List<Report> findAllByCommentToAnswer(Comment comment){return reportRepository.findAllByCommentAndAccepted(comment, null);}
    public List<Report> findAllByPost(Post post){return reportRepository.findAllByPost(post);}
    public List<Report> findAllByPostToAnswer(Post post){return reportRepository.findAllByPostAndAccepted(post, null);}
    public Report save(Report report){return reportRepository.save(report);}
    public void delete(Report report){reportRepository.delete(report);}
    public Report acceptReport(Report report){
        report.setAccepted(true);
        return report;
    }
    public Report declineReport(Report report){
        report.setAccepted(false);
        return report;
    }
}
