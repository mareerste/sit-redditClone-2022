import { HttpClient, HttpParams } from '@angular/common/http';
import { ImageService } from 'src/app/service/image.service';
import { ReportDialogComponent } from './../report-dialog/report-dialog.component';

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
import { Report } from 'src/app/model/report';
import { MatDialog } from '@angular/material';
import { BanService } from 'src/app/service/ban.service';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'post-list-item',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  imageDirectoryPath: any = 'C:/Users/HP/Documents/Predavanja/Semestar 4/Project_RedditClone_2022/image/';
  imageNape: any = 'malibot.jpg';
  // \HP\Documents\Predavanja\Semestar 4\Project_RedditClone_2022\image\malibot.jpg
  @Input()
  post: Post;
  path;
  image;
  imageUser;
  @Input()
  showComments: boolean;
  karma: number = 0;
  communitySuspended = false;
  form: FormGroup;
  community: Community
  @Output()
  clickedEventEmitChange = new EventEmitter<Post>();
  @Output()
  clickedEventEmitDelete = new EventEmitter<Post>();
  isBanned = false;
  report: Report;
  

  private users: User[];
  constructor(
    private reactionService: ReactionService,
    private banService: BanService,
    private router: Router,
    private httpClient: HttpClient,
    private formBuilder: FormBuilder,
    private notifierService: NotifierService,
    private communityService: CommunityService,
    private imageService: ImageService,
    private sanitizer:DomSanitizer,
    private auth: AuthService,
    public dialog: MatDialog) { }

  ngOnInit(): void {
    console.log("DOBIJENI POST", this.post)

    if (this.post.imagePath != null) {

      this.imageService.getImage3(this.post.imagePath).subscribe(data => {
        var unsafeImageUrl = URL.createObjectURL(data);
        this.image = this.sanitizer.bypassSecurityTrustUrl(unsafeImageUrl);
      })
    }
    if (this.post.user.avatar != null) {

      this.imageService.getImage3(this.post.user.avatar).subscribe(data => {
        var unsafeImageUrl = URL.createObjectURL(data);
        this.imageUser = this.sanitizer.bypassSecurityTrustUrl(unsafeImageUrl);
      })
    }
    this.loadKarma()
    this.communityService.getPostCommunity(this.post.id).subscribe(data => {
      this.community = data
      if (data.suspended)
        this.communitySuspended = true;
      this.checkIfBanned()
    })

    this.form = this.formBuilder.group({
      type: ['', Validators.compose([Validators.required])],
      post: ['', Validators.compose([])]
    });

  }

  openReportDialog(): void {
    this.dialog.open(ReportDialogComponent, { data: { post: this.post } })
  }

  showCommentsMethod(): void {
    this.showComments = true;
  }

  showPost() {
    this.router.navigate(
      ['post', this.post.id]
    )
  }

  isMyPost() {
    if (this.auth.isLoggedIn()) {
      if (this.auth.getCurrentUser().username == this.post.user.username)
        return true;
      else false;
    } else {
      return false
    }
  }

  editPost() {
    if (this.auth.getCurrentUser().username == this.post.user.username)
      this.router.navigate(['/post/' + this.post.id + '/edit'])
    else {
      this.notifierService.showNotification("You can edit only your posts..")
    }

  }

  onSubmitUp() {
    if (this.isLoggedIn()) {
      this.form.value.type = "UPVOTE"
      this.form.value.post = this.post.id
      this.reactionService.sendReaction(this.form.value)
        .subscribe(data => {
          this.post.reactions++;
          this.loadKarma()
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
      this.reactionService.sendReaction(this.form.value)
        .subscribe(data => {
          this.post.reactions++;
          this.loadKarma()
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

  isLoggedIn(): User {
    return this.auth.getCurrentUser();
  }
  deletePost() {
    if (this.isLoggedIn() == null) {
      this.notifierService.showNotification("You need to login first")
      return
    }

    if (this.isLoggedIn().username == this.post.user.username) {
      this.communityService.deleteCommunityPost(this.community.id, this.post.id).subscribe(data => {
        this.clickedEventEmitDelete.emit(this.post);
      });
    } else {
      this.notifierService.showNotification("You can delete only your posts")
    }

  }

  loadKarma() {
    this.reactionService.getKarmaForPost(this.post.id).subscribe(Data => {
      this.karma = Data
    })
  }

  checkIfBanned() {
    if (this.auth.isLoggedIn()) {
      var user: User = this.auth.getCurrentUser()
      this.banService.getBanForUserInCommunity(this.community.id, user.username).subscribe(data => {
        this.isBanned = data
      })
    } else return false

  }

}
