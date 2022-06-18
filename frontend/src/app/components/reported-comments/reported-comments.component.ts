import { Component, OnInit } from '@angular/core';
import { Report } from 'src/app/model/report';
import { CommentService } from 'src/app/service/comment.service';
import { ReportService } from 'src/app/service/report.service';

@Component({
  selector: 'app-reported-comments',
  templateUrl: './reported-comments.component.html',
  styleUrls: ['./reported-comments.component.css']
})
export class ReportedCommentsComponent implements OnInit {

  presentComment:Report;
  reports:Report[] = [];
  constructor(
    private reportService:ReportService,
    private commentService:CommentService,
  ) { }

  ngOnInit() {
    this.loadReports()
  }

  showComment(comment:Report){
    this.presentComment = comment;
  }

  acceptReport(){
    this.reportService.acceptReport(this.presentComment).subscribe(data=>{
      this.deleteOthersReports(this.presentComment)
      this.commentService.deleteLogicComment(this.presentComment.comment.id).subscribe(data=>{
        this.presentComment = null  
      })
    })
  }

  declineReport(){
    this.reportService.declineReport(this.presentComment).subscribe(data=>{
      this.deleteReport()
    })
  }

  deleteReport(){
    let index = this.reports.findIndex(r => r.id == this.presentComment.id);
    if (index !== -1) {
      this.reports.splice(index, 1);
      this.presentComment = null
    }
  }

  deletePassedReport(report:Report){
    let index = this.reports.findIndex(r => r.id == report.id);
    if (index !== -1) {
      this.reports.splice(index, 1);
      this.presentComment = null
    }
  }

  deleteOthersReports(report:Report){
    var removeValFromIndex = []
    this.reports.forEach(element => {
      if(element.comment.id == report.comment.id){
        let index = this.reports.findIndex(r => r.id == element.id);
        removeValFromIndex.push(index);
      }
    });

    for (var i = removeValFromIndex.length -1; i >= 0; i--)
       this.reports.splice(removeValFromIndex[i],1);
  }

  loadReports(){
    this.reportService.getCommunityReportedComments().subscribe(data=>{
      this.reports = data;
    })
  }
}
