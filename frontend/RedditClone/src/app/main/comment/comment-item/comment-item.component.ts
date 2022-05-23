import { Comment } from './../../../model/comment';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-comment-item',
  templateUrl: './comment-item.component.html',
  styleUrls: ['./comment-item.component.scss']
})
export class CommentItemComponent implements OnInit {

  @Input()
  comment:Comment;
  @Input()
  showComments:boolean;

  constructor() { }

  ngOnInit(): void {
  }

}
