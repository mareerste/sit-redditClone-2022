import { User } from './../../model/user';
import { Post } from './../../model/post';
import { Component, OnInit, Input } from '@angular/core';
import { ReactionService } from 'src/app/service/reaction.service';
import { Router } from '@angular/router';

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
  constructor(
    private reactionService:ReactionService,
    private router:Router) { }

 ngOnInit(): void {
  this.reactionService.getKarmaForPost(this.post.id).subscribe(Data => {
    this.karma = Data
  })
  console.log(this.post)
 }

 showCommentsMethod():void{
   this.showComments = true;
 }

 showPost(){
  this.router.navigate(
    ['post',this.post.id]
    )
 }

}
