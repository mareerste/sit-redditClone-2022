import { takeUntil, map } from 'rxjs/operators';
import { CommunityService } from 'src/app/service/community.service';
import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { ApiService, AuthService, ConfigService, UserService } from 'src/app/service';
import { HttpHeaders } from '@angular/common/http';
import { Community } from 'src/app/model/community';
import { Flair } from 'src/app/model/flair';

interface DisplayMessage {
  msgType: string;
  msgBody: string;
}

@Component({
  selector: 'app-community-create-post',
  templateUrl: './community-create-post.component.html',
  styleUrls: ['./community-create-post.component.css']
})
export class CommunityCreatePostComponent implements OnInit {

  title = 'Sign up';
  form: FormGroup;
  submitted = false;
  @Input()
  private community:Community;
  private selectedFlair:Flair;

  notification: DisplayMessage;

  returnUrl: string;
  private ngUnsubscribe: Subject<void> = new Subject<void>();

  constructor(
    private userService: UserService,
    private communityService: CommunityService,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private apiService:ApiService,
    private config:ConfigService
  ) {

  }

  ngOnInit() {
    this.route.params
      .pipe(takeUntil(this.ngUnsubscribe))
      .subscribe((params: DisplayMessage) => {
        this.notification = params;
      });
    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    console.log(this.returnUrl)
    this.form = this.formBuilder.group({
      title: ['', Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(30)])],
      text: ['', Validators.compose([Validators.required, Validators.minLength(3)])],
      flair: []
      // email: ['', Validators.compose([Validators.required, Validators.minLength(10), Validators.maxLength(64),Validators.email])],
    });
  }

  ngOnDestroy() {
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
  }

  onSubmit(post) {
    
    this.notification = undefined;
    this.submitted = true;
    // console.log("POSTTTTT")
    // post.flair = this.community.flairs[0]
    // console.log(post)
    // this.communityService.savePostInCommunity(post,this.community.id).subscribe(data => {
    //   console.log(data)
    // },
    //     error => {
    //       this.submitted = false;
    //       console.log('Create post error');
    //       this.notification = { msgType: 'error', msgBody: error['error'].message };
    //     });

// OVO SADA RADIIII
    // this.form.value.flair = this.community.flairs[0]
    // const json = JSON.stringify(obj);
    // const flairTag = JSON.stringify(this.form.value.flair)
    // console.log(flairTag)
    // this.form.value.flair = flairTag
    console.log(this.form.value.flair)
    this.form.value.flair = JSON.parse(this.form.value.flair);
    this.communityService.savePostInCommunity(this.form.value,this.community.id)
      .subscribe(data => {
        // console.log(data);
        // window.location.reload();
        // this.router.navigate([this.returnUrl]);
        // console.log(this.router.url)
        // this.router.navigate(
        //   ['community/'+this.community.id+'/posts']
        //   )
        window.location.reload()
        
      },
        error => {
          this.submitted = false;
          console.log('Create post error');
          this.notification = { msgType: 'error', msgBody: error['error'].message };
        });

  }

  createPost(post) {
    const createPostHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });
    post.flair = this.community.flairs[0]
    console.log(JSON.stringify(post));
    
    return this.apiService.post(`${this.config.community_url}/${this.community.id}/posts`, JSON.stringify(post), createPostHeaders)
      .pipe(map(() => {
        console.log('Post created successfully');
      }));
  }

}
