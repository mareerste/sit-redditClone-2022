import { Post } from './../../../model/post';
import { User } from './../../../model/user';
import { PostService } from './../../../services/post.service';
import { Component, OnInit, Input } from '@angular/core';

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

  private users:User[];
  constructor(private postService:PostService) { }

 ngOnInit(): void {
   
 }

}
