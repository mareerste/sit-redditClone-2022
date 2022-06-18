import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Community } from 'src/app/model/community';
import { Post } from 'src/app/model/post';
import { Report } from 'src/app/model/report';
import { ReportService } from 'src/app/service/report.service';

@Component({
  selector: 'app-reported-posts-list',
  templateUrl: './reported-posts-list.component.html',
  styleUrls: ['./reported-posts-list.component.css']
})
export class ReportedPostsListComponent implements OnInit {

  @Input()
  community:Community;
  @Input()
  reports:Report[] = [];
  @Output()
  showPostEmmiter = new EventEmitter<Report>();

  constructor(
    private reportService:ReportService,
  ) { }

  ngOnInit() {
    // this.reportService.getCommunityReportedPosts(this.community).subscribe(data =>{
    //   this.reports = data;
    //   console.log(data);
    // })
  }

  showPost(report:Report){
    console.log("emit 2")
    this.showPostEmmiter.emit(report);
  }

}
