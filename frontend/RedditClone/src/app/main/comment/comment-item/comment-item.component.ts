import { Comment } from './../../../model/comment';
import { Component, OnInit, Input } from '@angular/core';
import { ReactionService } from 'src/app/services/reaction.service';

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
  karma:number = 0;

  constructor(private reactionService:ReactionService) { }

  ngOnInit(): void {
    this.reactionService.getKarmaForComment(this.comment.id).subscribe(Data => {
      this.karma = Data
    })
  }

}
