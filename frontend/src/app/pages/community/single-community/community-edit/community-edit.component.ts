import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-community-edit',
  templateUrl: './community-edit.component.html',
  styleUrls: ['./community-edit.component.css']
})
export class CommunityEditComponent implements OnInit {

  showDesc:boolean = false;
  showMembers:boolean = false;
  showFlairs:boolean = false;
  showRules:boolean = false;
  showPosts:boolean = false;
  showComments:boolean = false;
  showArray=[this.showDesc,this.showMembers,this.showFlairs,this.showRules, this.showPosts, this.showComments]

  constructor() { }

  ngOnInit() {
  }

  showDescClick(){
    this.clearShow()
    this.showDesc = true;
  }
  showMembersClick(){
    this.clearShow()
    this.showMembers = true;
  }
  showFlairsClick(){
    this.clearShow()
    this.showFlairs = true;
  }
  showRulesClick(){
    this.clearShow()
    this.showRules = true;
  }
  showPostsClick(){
    this.clearShow()
    this.showPosts = true;
  }
  showCommentsClick(){
    this.clearShow()
    this.showComments = true;
  }
  clearShow(){
    this.showDesc = false;
    this.showMembers = false;
    this.showFlairs = false;
    this.showRules = false;
    this.showPosts = false;
    this.showComments = false;
  }

}
