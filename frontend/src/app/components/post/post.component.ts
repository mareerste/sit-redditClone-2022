import { Reaction } from './../../model/reaction';
import { User } from './../../model/user';
import { Post } from './../../model/post';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { ReactionService } from 'src/app/service/reaction.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReactionType } from 'src/app/model/enumerations/reaction-type.enum';
import { NotifierService } from 'src/app/service/notifier.service';
import { CommunityService } from 'src/app/service/community.service';
import { Community } from 'src/app/model/community';
import { AuthService } from 'src/app/service';

@Component({
  selector: 'post-list-item',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  @Input()
  post: Post;
  @Input()
  showComments: boolean;
  karma: number = 0;
  form: FormGroup;
  community: Community
  @Output()
  clickedEventEmitChange = new EventEmitter<Post>();
  @Output()
  clickedEventEmitDelete = new EventEmitter<Post>();

  private users: User[];
  constructor(
    private reactionService: ReactionService,
    private router: Router,
    private formBuilder: FormBuilder,
    private notifierService: NotifierService,
    private communityService: CommunityService,
    private auth: AuthService) { }

  ngOnInit(): void {
    this.loadKarma()
    this.communityService.getPostCommunity(this.post.id).subscribe(data => {
      this.community = data
    })
    this.form = this.formBuilder.group({
      type: ['', Validators.compose([Validators.required])],
      post: ['', Validators.compose([])]
    });
  }

  showCommentsMethod(): void {
    this.showComments = true;
  }

  showPost() {
    this.router.navigate(
      ['post', this.post.id]
    )
  }

  onSubmitUp() {
    if (this.isLoggedIn()) {
      this.form.value.type = "UPVOTE"
      this.form.value.post = this.post.id
      console.log(this.form.value)
      this.reactionService.sendReaction(this.form.value)
        .subscribe(data => {
          this.loadKarma()
          console.log(data)
        },
          error => {
            this.notifierService.showNotification("You are already up vote this post")
            console.log(error)
            console.log(error['status'])
            console.log('Create reaction error');
          });
    } else {
      this.notifierService.showNotification("You need to login first")
    }

  }

  onSubmitDown() {
    if (this.isLoggedIn()) {
      this.form.value.type = "DOWNVOTE"
      this.form.value.post = this.post.id
      console.log(this.form.value)
      this.reactionService.sendReaction(this.form.value)
        .subscribe(data => {
          this.loadKarma()
          console.log(data)
        },
          error => {
            this.notifierService.showNotification("You are already down vote this post")
            console.log(error)
            console.log(error['status'])
            console.log('Create reaction error');
          });
    } else {
      this.notifierService.showNotification("You need to login first")
    }

  }

  isLoggedIn():User {
    return this.auth.getCurrentUser();
  }
  deletePost() {
    if(this.isLoggedIn() == null){
      this.notifierService.showNotification("You need to login first")
      return
    }

    if(this.isLoggedIn().username == this.post.user.username){
      this.communityService.deleteCommunityPost(this.community.id, this.post.id).subscribe(data => {
        this.clickedEventEmitDelete.emit(this.post);
      });
    }else{
      this.notifierService.showNotification("You can delete only your posts")
    }
    
  }

  loadKarma(){
    this.reactionService.getKarmaForPost(this.post.id).subscribe(Data => {
      this.karma = Data
    })
  }

}
