import { ReactionType } from './../../../model/enums/reaction-type';
import { Observable } from 'rxjs';
import { ReactionService } from './../../../services/reaction.service';
import { Post } from './../../../model/post';
import { User } from './../../../model/user';
import { PostService } from './../../../services/post.service';
import { Component, OnInit, Input } from '@angular/core';
import { Reaction } from 'src/app/model/reaction';

@Component({
  selector: 'post-list-item',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnInit {

  @Input()
  post:Post;
  @Input()
  showComments:boolean;
  karma:number = 0;

  private users:User[];
  constructor(private reactionService:ReactionService) { }

 ngOnInit(): void {
  this.reactionService.getKarmaForPost(this.post.id).subscribe(Data => {
    this.karma = Data
  })
 
  
 }

 showCommentsMethod():void{
   this.showComments = true;
 }

//  countKarma():void{
//    this.reactions.forEach(reaction => {
//      if(reaction.type == ReactionType.UPVOTE){
//        this.karma++
//      }else{
//        this.karma--
//      }
//    });
//  }

}
