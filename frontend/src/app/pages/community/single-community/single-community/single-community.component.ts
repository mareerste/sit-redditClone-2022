import { User } from 'src/app/model/user';
import { async } from '@angular/core/testing';
import { filter } from 'rxjs/operators';
import { Observable } from 'rxjs/Observable';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Post } from 'src/app/model/post';
import { AuthService, PostService } from 'src/app/service';
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
  community:Community;
  communityId;

  isModerator:boolean = false;
  comRulesTitle = "Community Rules"
  comFlairsTitle = "Community Flairs"
  comModeratorsTitle = "Community Moderators"

  showPosts:boolean;
  showEdit:boolean;

  loggedUser:User;
  moderators: User[];
  
  constructor(
    private auth:AuthService,
    private route:ActivatedRoute,
    private postService:PostService,
    private communityService:CommunityService,
    private router:Router,
  ) { }

  ngOnInit() {
    this.loggedUser = this.auth.getCurrentUser();
    console.log(this.loggedUser);
    
    this.communityId = this.route.snapshot.paramMap.get('id');
    this.loadCommunity(this.communityId)
    this.communityService.getCommunityPosts(this.communityId).subscribe(data=>{
      this.posts = data;
    });
    this.showPosts = true;
    
  }

  saveNewPost(post:Post){
    this.posts.push(post)
  }

  getChange(){
    this.communityService.getCommunityPosts(this.communityId).subscribe(data=>{
      this.posts = data;
    });
  }

  getCommunityChange(community: Community){
    this.community = community;
    this.communityService.updateCommunity(community).subscribe(data=>{
    })
  }

  loadCommunity(id:number){
    this.communityService.getCommunity(id).subscribe(data=>{
      this.community = data;
      this.moderators = data.moderators;
      this.checkForModerator()
    })
  }

  createPost(){
    this.router.navigate(['/community/'+this.community.id+'/create'])
  }

  showPostsClick(){
    this.showEdit = false;
    this.showPosts = true;
  }

  showEditClick(){
    this.showPosts = false;
    this.showEdit = true;
  }

  getDeleted(post:Post){
    let index = this.posts.findIndex(p => p.id == post.id);
    if (index !== -1) {
      this.posts.splice(index, 1);
    }
  }

  checkForModerator(){
    let index = this.moderators.findIndex(m => m.username == this.loggedUser.username);
    if (index !== -1) {
      this.isModerator = true;
    }
  }


}
