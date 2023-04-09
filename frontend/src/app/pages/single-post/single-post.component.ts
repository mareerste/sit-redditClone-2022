import { CommunityService } from './../../service/community.service';
import { Post } from './../../model/post';
import { PostService } from './../../service/post.service';

import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-single-post',
  templateUrl: './single-post.component.html',
  styleUrls: ['./single-post.component.css'],
})
export class SinglePostComponent implements OnInit {

  
  button = "View";
  postId;
  post;
  community;
  constructor(
    private route:ActivatedRoute,
    private postService:PostService,
    private communityService:CommunityService,
    private router:Router
  ) { }

  ngOnInit() {
    this.postId = this.route.snapshot.paramMap.get('id');
    this.loadData(this.postId);
    this.loadCommunity(this.postId);
  }

  getCommunity(){
    console.log("Get Community called");
    this.showPost()
  }

  loadCommunity(id:number){
    this.communityService.getPostCommunity(id).subscribe(data=>{
      this.community = data;
    })
  }

  loadData(id:number){
    this.postService.getPost3(id).subscribe(data => {
      this.post = data;
      
    });  
  }

  getChange(post:Post){
    // window.location.reload()
  }

  getDelete(post:Post){
    this.router.navigate(['/'])
  }

  showPost(){
    this.router.navigate(
      ['community',this.community.id,'posts']
      )
   }

}
