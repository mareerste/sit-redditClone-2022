import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Banned } from 'src/app/model/banned';
import { Community } from 'src/app/model/community';
import { Flair } from 'src/app/model/flair';
import { Post } from 'src/app/model/post';
import { User } from 'src/app/model/user';
import { UserService } from 'src/app/service';
import { BanService } from 'src/app/service/ban.service';

@Component({
  selector: 'app-community-edit',
  templateUrl: './community-edit.component.html',
  styleUrls: ['./community-edit.component.css']
})
export class CommunityEditComponent implements OnInit {

  showDesc: boolean = false;
  showMembers: boolean = false;
  showFlairs: boolean = false;
  showRules: boolean = false;
  showPosts: boolean = false;
  showComments: boolean = false;
  // showArray=[this.showDesc,this.showMembers,this.showFlairs,this.showRules, this.showPosts, this.showComments]

  @Output()
  clickedEventEmitChange = new EventEmitter<Community>();
  @Input()
  community: Community;
  formDesc: FormGroup;
  descRequired = false;
  formName: FormGroup;
  nameRequired = false;
  @Output()
  clickedEventEmitDeletePost = new EventEmitter<Post>();

  bannedList:Banned[] = [];
  users:User[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private banService: BanService,
    private userService: UserService,
  ) { }

  ngOnInit() {
    this.formDesc = this.formBuilder.group({
      name: ['', Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(64)])],
      description: ['', Validators.compose([Validators.required, Validators.minLength(5), Validators.maxLength(32)])],
    });
    // this.loadBannedList()
    // this.loadUsers()
  }

  showDescClick() {
    this.clearShow()
    this.showDesc = true;
  }
  showMembersClick() {
    this.clearShow()
    this.showMembers = true;
  }
  showFlairsClick() {
    this.clearShow()
    this.showFlairs = true;
  }
  showRulesClick() {
    this.clearShow()
    this.showRules = true;
  }
  showPostsClick() {
    this.clearShow()
    this.showPosts = true;
  }
  showCommentsClick() {
    this.clearShow()
    this.showComments = true;
  }
  clearShow() {
    this.showDesc = false;
    this.showMembers = false;
    this.showFlairs = false;
    this.showRules = false;
    this.showPosts = false;
    this.showComments = false;
  }

  changeDescription() {
    this.descRequired = false;
    this.nameRequired = false;
    if (this.formDesc.value.name.length == 0){
      this.nameRequired = true
    }else if (this.formDesc.value.description.length == 0){
      this.descRequired = true;
    }else{
      this.community.name = this.formDesc.value.name;
      this.community.description = this.formDesc.value.description;
      this.clickedEventEmitChange.emit(this.community);
    }
  }

  newFlairAdded(data:Flair){
    var flairs:Flair [] = []
    flairs = this.community.flairs;
    flairs.push(data);
    this.community.flairs = flairs;
    this.clickedEventEmitChange.emit(this.community);
  }

  newRuleAdded(data:string){
    var rules:string [] = []
    rules = this.community.rules;
    rules.push(data);
    this.community.rules = rules;
    this.clickedEventEmitChange.emit(this.community);
  }

  deleteFlair(flair:Flair){
    var flairs:Flair[]
    flairs = this.community.flairs;
    let index = flairs.findIndex(f => f.id == flair.id);
    if (index !== -1) {
      this.community.flairs.splice(index, 1);
      this.clickedEventEmitChange.emit(this.community);
    }
  }

  deleteRule(rule:string){
    var rules:string[]
    rules = this.community.rules;
    let index = rules.findIndex(r => r == rule);
    if (index !== -1) {
      this.community.rules.splice(index, 1);
      this.clickedEventEmitChange.emit(this.community);
    }
  }

  deletePost(post:Post){
    this.clickedEventEmitDeletePost.emit(post)
  }

  // newFlairAdded(flair:Flair){
  //   this.community.flairs.push(flair);
  // }

  // loadBannedList(){
  //   this.banService.getBanForUserInCommunity(this.community.id).subscribe(data =>{
  //     this.bannedList = data;
  //     console.log(data);
  //   })
  // }

  // loadUsers(){
  //   this.userService.getUsers().subscribe(data=>{
  //     this.users = data;
  //     console.log(data);
  //   })
  // }


}
