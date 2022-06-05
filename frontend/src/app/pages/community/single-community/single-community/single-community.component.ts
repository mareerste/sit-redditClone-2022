import { async } from '@angular/core/testing';
import { filter } from 'rxjs/operators';
import { Observable } from 'rxjs/Observable';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Post } from 'src/app/model/post';
import { PostService } from 'src/app/service';
import { CommunityService } from 'src/app/service/community.service';
import { Community } from 'src/app/model/community';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-single-community',
  templateUrl: './single-community.component.html',
  styleUrls: ['./single-community.component.css']
})
export class SingleCommunityComponent implements OnInit {

  posts:Post[];
  postsClone:Post[];
  community:Community = null;
  communityId;
  // creationDate;
  comRulesTitle = "Community Rules"
  comFlairsTitle = "Community Flairs"
  comModeratorsTitle = "Community Moderators"
  
  constructor(
    private route:ActivatedRoute,
    private postService:PostService,
    private communityService:CommunityService,
    private router:Router
  ) { }

  ngOnInit() {
    this.communityId = this.route.snapshot.paramMap.get('id');
    this.loadCommunity(this.communityId)
    this.communityService.getCommunityPosts(this.communityId).subscribe(data=>{
      this.posts = data;
    });
    
    // this.creationDate = this.community.creationDate
  }

  saveNewPost(post:Post){
    // this.posts = this.communityService.getCommunityPosts(this.communityId);
    this.posts.push(post)
  }

  getChange(){
    // this.posts = this.communityService.getCommunityPosts(this.communityId);
    this.communityService.getCommunityPosts(this.communityId).subscribe(data=>{
      this.posts = data;
    });
  }

  loadCommunity(id:number){
    this.communityService.getCommunity(id).subscribe(data=>{
      this.community = data;
      console.log(this.community)
      // this.creationDate = this.community.creationDate
    })
  }

  changeCommunity(){
    console.log("clicked")
    this.community.name = "Promenjeno ime"
    console.log(this.community)
  }

}
