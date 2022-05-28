import { User } from './../../model/user';
import { Post } from './../../model/post';
import { Component, OnInit, Input } from '@angular/core';
import { ReactionService } from 'src/app/service/reaction.service';

@Component({
  selector: 'post-list-item',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
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

}
