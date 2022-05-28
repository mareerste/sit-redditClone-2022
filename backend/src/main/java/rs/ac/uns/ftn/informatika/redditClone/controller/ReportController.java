package rs.ac.uns.ftn.informatika.redditClone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.ReportCommentDTO;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.ReportDTO;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.ReportPostDTO;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Community;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Report;
import rs.ac.uns.ftn.informatika.redditClone.service.CommentService;
import rs.ac.uns.ftn.informatika.redditClone.service.PostService;
import rs.ac.uns.ftn.informatika.redditClone.service.ReportService;
import rs.ac.uns.ftn.informatika.redditClone.service.UserService;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/report")
public class ReportController {
    @Autowired
    private ReportService reportService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private PostService postService;
    @GetMapping
    public ResponseEntity<List<ReportDTO>>getReports(){
        List<Report> reports = reportService.findAll();
        List<ReportDTO> reportDTOList = new ArrayList<>();
        for (Report r:reports){
            reportDTOList.add(new ReportDTO(r));
        }
        return new ResponseEntity<>(reportDTOList, HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('MODERATOR', 'ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ReportDTO>getReport(@PathVariable Integer id){
        Report report = reportService.findOne(id);
        if(report == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new ReportDTO(report), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
    @PostMapping(consumes = "application/json", value = "/post")
    public ResponseEntity<ReportPostDTO> saveReportPost(@RequestBody ReportPostDTO reportDTO){
        if (reportDTO.getReason().equals("") || reportDTO.getReason() == null || reportDTO.getPost() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Report report = new Report();
        report.setTimestamp(reportDTO.getTimestamp());
        report.setReason(reportDTO.getReason());
        report.setUser(userService.findOne(reportDTO.getUser().getUsername()));
        report.setAccepted(reportDTO.getAccepted());
        report.setPost(postService.findOne(reportDTO.getPost().getId()));
        report = reportService.save(report);
        return new ResponseEntity<>(new ReportPostDTO(report),HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
    @PostMapping(consumes = "application/json", value = "/comment")
    public ResponseEntity<ReportCommentDTO> saveReportComment(@RequestBody ReportCommentDTO reportDTO){
        if (reportDTO.getReason().equals("") || reportDTO.getReason() == null || reportDTO.getComment() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Report report = new Report();
        report.setTimestamp(reportDTO.getTimestamp());
        report.setReason(reportDTO.getReason());
        report.setUser(userService.findOne(reportDTO.getUser().getUsername()));
        report.setAccepted(reportDTO.getAccepted());
        report.setComment(commentService.findOne(reportDTO.getComment().getId()));
        report = reportService.save(report);
        return new ResponseEntity<>(new ReportCommentDTO(report),HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
    @PutMapping(consumes = "application/json", value = "/post")
    public ResponseEntity<ReportPostDTO> updateReportPost(@RequestBody ReportPostDTO reportDTO){
        Report report = reportService.findOne(reportDTO.getId());
        if(report == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        report.setTimestamp(reportDTO.getTimestamp());
        report.setReason(reportDTO.getReason());
        report.setUser(userService.findOne(reportDTO.getUser().getUsername()));
        report.setAccepted(reportDTO.getAccepted());
        report.setPost(postService.findOne(reportDTO.getPost().getId()));
        report = reportService.save(report);
        return new ResponseEntity<>(new ReportPostDTO(report),HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
    @PutMapping(consumes = "application/json", value = "/comment")
    public ResponseEntity<ReportCommentDTO> updateReportComment(@RequestBody ReportCommentDTO reportDTO){
        Report report = reportService.findOne(reportDTO.getId());
        report.setTimestamp(reportDTO.getTimestamp());
        report.setReason(reportDTO.getReason());
        report.setUser(userService.findOne(reportDTO.getUser().getUsername()));
        report.setAccepted(reportDTO.getAccepted());
        report.setComment(commentService.findOne(reportDTO.getComment().getId()));
        report = reportService.save(report);
        return new ResponseEntity<>(new ReportCommentDTO(report),HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Integer id) {

        Report report = reportService.findOne(id);
        if (report != null) {
            reportService.delete(report);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
