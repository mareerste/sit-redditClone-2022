import { filter } from 'rxjs/operators';
import { PostService } from './../../services/post.service';
import { Post } from './../../model/post';
import { Component, OnInit, Output } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.scss']
})
export class MainPageComponent implements OnInit {
  
  posts:Observable<Post[]>;
  constructor(private postService:PostService, private route:ActivatedRoute) { }

  ngOnInit(): void {
    // this.route.params.subscribe(params => {
    //   if(params["entry"]){
    //   this.postService.filter(params["entry"]).subscribe((data:Post[])=>{
    //     this.posts=data;
    //   })
    //   }else{
    //     this.postService.getPosts().subscribe((data:Post[])=>{
    //       this.posts=data;
    //     })
    //   }
    // })
    this.posts = this.postService.getPosts();
    
  }

  filter(text:string){
    this.postService.filter(text);
  }

}
