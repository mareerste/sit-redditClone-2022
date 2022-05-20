import { Post } from './../../../model/post';
import { PostService } from './../../../services/post.service';
import { User } from './../../../model/user';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.scss']
})
export class PostListComponent implements OnInit {
  // pod anotacijom se stavljaju vrednosti koje se prenose u potomacku komponentu => [post]="post"
  // @Input
  // posts; Post[];

  @Input()
  posts:Post[];
  constructor(private postService:PostService) { }

  ngOnInit(): void {
    this.postService.getPosts().subscribe((data:Post[])=>{
      this.posts=data;
    })
  }

}
