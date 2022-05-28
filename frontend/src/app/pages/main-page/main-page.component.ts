import { Post } from './../../model/post';
import { ActivatedRoute } from '@angular/router';
import { PostService } from './../../service/post.service';
import { Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

  posts:Observable<Post[]>;
  constructor(private postService:PostService, private route:ActivatedRoute) { }

  ngOnInit(): void {
    this.posts = this.postService.getPosts();
    
  }

  filter(text:string){
    // this.postService.filter(text);
  }


}
