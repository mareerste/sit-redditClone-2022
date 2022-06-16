import { async } from '@angular/core/testing';
import { ReactionService } from './../../service/reaction.service';
import { Router } from '@angular/router';
import { Post } from './../../model/post';
import { Observable } from 'rxjs';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { take } from 'rxjs/operators';

@Component({
  selector: 'post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.css']
})
export class PostListComponent implements OnInit {

  @Input()
  posts: Post[];
  @Output()
  clickedEventEmit = new EventEmitter<void>();
  constructor(
  ) { }

  ngOnInit(): void {
    // this.posts.sort();
    this.sortByPopularity()
  }

  sortByPopularity() {
    this.posts.sort((first, second) => second.reactions - first.reactions)
    console.log(this.posts)
  }

  getChange(post:Post) {
    let index = this.posts.findIndex(p => p.id == post.id);
    this.posts[index] = post;
  }

  getDelete(post: Post) {
    let index = this.posts.findIndex(p => p.id == post.id);
    if (index !== -1) {
      this.posts.splice(index, 1);
    }
  }

}
