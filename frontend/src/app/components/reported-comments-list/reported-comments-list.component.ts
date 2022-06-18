import { Component, OnInit, EventEmitter, Output, Input } from '@angular/core';
import { Report } from 'src/app/model/report';
import { ReportService } from 'src/app/service/report.service';

@Component({
  selector: 'app-reported-comments-list',
  templateUrl: './reported-comments-list.component.html',
  styleUrls: ['./reported-comments-list.component.css']
})
export class ReportedCommentsListComponent implements OnInit {
  @Input()
  comments:Report[] = []
  @Output()
  showCommentEmmiter = new EventEmitter<Report>();

  constructor(
    private reportService:ReportService,
  ) { }

  ngOnInit() {
  }

  showComment(comment:Report){
    this.showCommentEmmiter.emit(comment);
  }


}
