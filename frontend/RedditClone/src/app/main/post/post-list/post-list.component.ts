import { Router } from '@angular/router';
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

  @Input()
  posts:Post[];
  constructor(private router:Router){}

  ngOnInit(): void {
  }

}
