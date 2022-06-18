import { Component, OnInit } from '@angular/core';
import { Report } from 'src/app/model/report';
import { ReportService } from 'src/app/service/report.service';

@Component({
  selector: 'app-reported-comments-list',
  templateUrl: './reported-comments-list.component.html',
  styleUrls: ['./reported-comments-list.component.css']
})
export class ReportedCommentsListComponent implements OnInit {

  comments:Report[] = []
  constructor(
    private reportService:ReportService,
  ) { }

  ngOnInit() {
    this.reportService.getCommunityReportedComments().subscribe(data=>{
      this.comments = data;
      console.log(data)
    })
  }

}
