import { Observable } from 'rxjs';
import { Comment } from './../../../model/comment';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-comment-list',
  templateUrl: './comment-list.component.html',
  styleUrls: ['./comment-list.component.scss']
})
export class CommentListComponent implements OnInit {

  @Input()
  comments:any;

  constructor() { }

  ngOnInit(): void {
  }

}
