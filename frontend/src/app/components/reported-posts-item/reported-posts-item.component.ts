import { Report } from './../../model/report';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Post } from 'src/app/model/post';

@Component({
  selector: 'app-reported-posts-item',
  templateUrl: './reported-posts-item.component.html',
  styleUrls: ['./reported-posts-item.component.css']
})
export class ReportedPostsItemComponent implements OnInit {

  @Input()
  report:Report;
  @Output()
  showPostEmmiter = new EventEmitter<Report>();
  constructor() { }

  ngOnInit() {
  }

  showPost(){
    this.showPostEmmiter.emit(this.report);
  }

}
