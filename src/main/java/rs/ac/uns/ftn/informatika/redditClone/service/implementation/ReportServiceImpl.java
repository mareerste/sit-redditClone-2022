package rs.ac.uns.ftn.informatika.redditClone.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Comment;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Post;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Report;
import rs.ac.uns.ftn.informatika.redditClone.repository.ReportRepository;
import rs.ac.uns.ftn.informatika.redditClone.service.ReportService;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportRepository reportRepository;
    @Override
    public Report findOne(Integer id){return reportRepository.findById(id).orElseGet(null);}
    @Override
    public List<Report> findAll(){return reportRepository.findAll();}
    @Override
    public List<Report> findAllByComment(Comment comment){return reportRepository.findAllByComment(comment);}
    @Override
    public List<Report> findAllByCommentToAnswer(Comment comment){return reportRepository.findAllByCommentAndAccepted(comment, null);}
    @Override
    public List<Report> findAllByPost(Post post){return reportRepository.findAllByPost(post);}
    @Override
    public List<Report> findAllByPostToAnswer(Post post){return reportRepository.findAllByPostAndAccepted(post, null);}
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
