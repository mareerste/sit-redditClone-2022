import { takeUntil, map } from 'rxjs/operators';
import { CommunityService } from 'src/app/service/community.service';
import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { ApiService, AuthService, ConfigService, UserService } from 'src/app/service';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Community } from 'src/app/model/community';
import { Flair } from 'src/app/model/flair';
import { Post } from 'src/app/model/post';
import { MatInputModule } from '@angular/material/input';
import { ImageService } from 'src/app/service/image.service';

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

  title = 'Create post';
  form: FormGroup;
  submitted = false;
  imagePath;
  private community:Community;
  notification: DisplayMessage;
  returnUrl: string;
  private ngUnsubscribe: Subject<void> = new Subject<void>();

  @Output()
  saveNewPost = new EventEmitter<Post>();
  newPost;
  selectedFile:File;
  pdfFile:File;
  communityId;

  titleRequired = false;
  textRequired = false;
  flairRequired = false;

  constructor(
    private userService: UserService,
    private communityService: CommunityService,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private apiService:ApiService,
    private config:ConfigService,
    private imageService:ImageService,
    private httpClient:HttpClient
  ) {

  }
  onFileChanged(event){
    this.selectedFile = event.target.files[0];
  }

  onUpload() {
    const uploadImageData = new FormData();
    uploadImageData.append('imageFile', this.selectedFile, this.selectedFile.name);
  
    this.imageService.saveImage(uploadImageData).subscribe(res=>{
      this.imagePath = res.path;
    })

  }


  ngOnInit() {
    this.route.params
      .pipe(takeUntil(this.ngUnsubscribe))
      .subscribe((params: DisplayMessage) => {
        this.notification = params;
      });
      this.communityId = this.route.snapshot.paramMap.get('id');
      this.loadCommunity(this.communityId)
    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    this.form = this.formBuilder.group({
      title: ['', Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(64)])],
      text: ['', Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(32)])],
      flair: [],
      imagePath: [],
      files: []
    });
  }

  ngOnDestroy() {
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
  }

  onSubmit() {
    
    this.notification = undefined;
    this.submitted = true;
    this.titleRequired = false;
    this.textRequired = false;
    this.flairRequired = false;
    
    this.form.value.flair = JSON.parse(this.form.value.flair);
    if(this.imagePath != undefined){
      this.form.value.imagePath = this.imagePath;
    }
    console.log(this.pdfFile)
    if(this.pdfFile != undefined){
      this.form.value.files = this.pdfFile;
      console.log(this.form.value)
      // call pdf api
      
      this.communityService.savePostInCommunityPDF(this.form.value,this.community.id)
      .subscribe(data => {
        this.saveNewPost.emit(data);
        this.submitted = false;
        this.form.reset();
        this.router.navigate(['/community/'+this.community.id+'/posts'])
      },
        error => {
          this.submitted = false;
          if(this.form.value.text.length == 0)
            this.textRequired = true
          if(this.form.value.title.length == 0)
            this.titleRequired = true
          if(this.form.value.flair == null)
            this.flairRequired = true

          console.log(error)
          console.log(error['status'])
          console.log('Create post error');
          this.notification = { msgType: 'error', msgBody: 'Please fill all fields' };
        });
    }else{
    
    this.communityService.savePostInCommunity(this.form.value,this.community.id)
      .subscribe(data => {
        this.saveNewPost.emit(data);
        this.submitted = false;
        this.form.reset();
        this.router.navigate(['/community/'+this.community.id+'/posts'])
      },
        error => {
          this.submitted = false;
          if(this.form.value.text.length == 0)
            this.textRequired = true
          if(this.form.value.title.length == 0)
            this.titleRequired = true
          if(this.form.value.flair == null)
            this.flairRequired = true

          console.log(error)
          console.log(error['status'])
          console.log('Create post error');
          this.notification = { msgType: 'error', msgBody: 'Please fill all fields' };
        });
      }
  }
  loadCommunity(id:number){
    this.communityService.getCommunity(id).subscribe(data=>{
      this.community = data;
    })
  }

  backButton(){
    this.submitted = false;
        this.form.reset();
        this.router.navigate(['/community/'+this.community.id+'/posts'])
  }

  onFileSelected(event) {
    this.pdfFile = event.target.files[0];
  }
}
