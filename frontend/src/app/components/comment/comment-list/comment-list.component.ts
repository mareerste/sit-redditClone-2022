import { Component, OnInit, Input } from '@angular/core';
import { Comment } from 'src/app/model/comment';
import { ReactionType } from 'src/app/model/enumerations/reaction-type.enum';
import { AuthService } from 'src/app/service';

@Component({
  selector: 'app-comment-list',
  templateUrl: './comment-list.component.html',
  styleUrls: ['./comment-list.component.css']
})
export class CommentListComponent implements OnInit {

  @Input()
  comments:any;
  loggedUser;

  constructor(
    private auth:AuthService,
    
  ) { }

  ngOnInit(): void {
    this.loggedUser = this.auth.getCurrentUser();
  }

  getChange(type:ReactionType){
    console.log(type)
    if(type == ReactionType.UPVOTE){
      console.log("UPVOTE")
    }else if(type == ReactionType.DOWNVOTE){
      console.log("DOWNVOTE")
    }
  }

  getDelete(comment: Comment) {
    let index = this.comments.findIndex(c => c.id == comment.id);
    if (index !== -1) {
      this.comments.splice(index, 1);
    }
  }
}
