import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Community } from 'src/app/model/community';
import { Post } from 'src/app/model/post';
import { Report } from 'src/app/model/report';
import { PostService } from 'src/app/service';
import { ReportService } from 'src/app/service/report.service';

@Component({
  selector: 'app-reported-posts',
  templateUrl: './reported-posts.component.html',
  styleUrls: ['./reported-posts.component.css']
})
export class ReportedPostsComponent implements OnInit {

  @Input()
  community:Community;
  report:Report;
  reports:Report[] = [];

  @Output()
  declineEventEmitter = new EventEmitter<Report>();
  @Output()
  acceptEventEmitter = new EventEmitter<Post>();
  // clickedEventEmitDeletePost
  constructor(
    private reportService:ReportService,
    private postService:PostService
  ) { }

  ngOnInit() {
    this.loadReports()

  }

  showPost(report:Report){
    this.report = report;
  }

  loadReports(){
    this.reportService.getCommunityReportedPosts(this.community).subscribe(data =>{
      this.reports = data;
      console.log(data);
    })
  }

  acceptReport(){
    this.reportService.acceptReport(this.report, this.community.id).subscribe(data=>{
      this.deleteOthersReports(this.report)
      this.acceptEventEmitter.emit(this.report.post)
      this.postService.deletePost(this.report.post.id, this.community.id).subscribe(data=>{
        this.report = null
      })
    })
  }

  declineReport(){
    this.reportService.declineReport(this.report).subscribe(data=>{
      this.deleteReport()
    })
  }

  deleteReport(){
    let index = this.reports.findIndex(r => r.id == this.report.id);
    if (index !== -1) {
      this.reports.splice(index, 1);
      this.report = null
    }
  }

  deletePassedReport(report:Report){
    let index = this.reports.findIndex(r => r.id == report.id);
    if (index !== -1) {
      this.reports.splice(index, 1);
      this.report = null
    }
  }

  deleteOthersReports(report:Report){
    var removeValFromIndex = []
    this.reports.forEach(element => {
      if(element.post.id == report.post.id){
        let index = this.reports.findIndex(r => r.id == element.id);
        removeValFromIndex.push(index);
      }
    });

    for (var i = removeValFromIndex.length -1; i >= 0; i--)
       this.reports.splice(removeValFromIndex[i],1);
  }
  

}
