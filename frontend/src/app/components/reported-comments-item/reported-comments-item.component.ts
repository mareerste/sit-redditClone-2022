import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Report } from 'src/app/model/report';

@Component({
  selector: 'app-reported-comments-item',
  templateUrl: './reported-comments-item.component.html',
  styleUrls: ['./reported-comments-item.component.css']
})
export class ReportedCommentsItemComponent implements OnInit {

  @Input()
  report:Report;
  @Output()
  showCommentEmmiter = new EventEmitter<Report>();
  constructor() { }

  ngOnInit() {
  }

  showComment(){
    this.showCommentEmmiter.emit(this.report);
  }

}
