import { takeUntil } from 'rxjs/operators';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, Input, OnInit } from '@angular/core';
import { Post } from 'src/app/model/post';
import { Subject } from 'rxjs';
import { PostService } from 'src/app/service';
import { Router, ActivatedRoute } from '@angular/router';
import { Community } from 'src/app/model/community';
import { CommunityService } from 'src/app/service/community.service';
import { MatInputModule } from '@angular/material/input';
import { Flair } from 'src/app/model/flair';
import { ImageService } from 'src/app/service/image.service';

interface DisplayMessage {
  msgType: string;
  msgBody: string;
}

@Component({
  selector: 'app-community-update-post',
  templateUrl: './community-update-post.component.html',
  styleUrls: ['./community-update-post.component.css']
})
export class CommunityUpdatePostComponent implements OnInit {

  title = 'Update post'
  form: FormGroup
  post: Post
  community: Community;
  notification: DisplayMessage;
  private ngUnsubscribe: Subject<void> = new Subject<void>();
  titleRequired = false;
  textRequired = false;
  flairRequired = false;
  ngSelect: Flair;
  flairs: Flair[] = []
  selectedFile:File;
  imagePath;

  constructor(
    private postService: PostService,
    private communityService: CommunityService,
    private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private imageService:ImageService
  ) { }

  ngOnInit() {
    var postId: any = this.route.snapshot.paramMap.get('id');
    this.loadPost(postId)

    this.route.params
      .pipe(takeUntil(this.ngUnsubscribe))
      .subscribe((params: DisplayMessage) => {
        this.notification = params;
      });
      // this.form = this.formBuilder.group({
      //   title: ['',Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(64)])],
      //   text: ['',Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(32)])],
      //   flair: []
      // });

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

  onSubmit() {

    this.notification = undefined;
    this.titleRequired = false;
    this.textRequired = false;
    this.flairRequired = false;
    console.log("this.form.value")
    console.log(this.form.value)
    var copyPost = this.post;
    if(this.form.value.text != "")
      copyPost.text = this.form.value.text;
    if(this.form.value.flair != this.post.flair)
      copyPost.flair = JSON.parse(this.form.value.flair);
    if(this.imagePath != undefined)
      copyPost.imagePath = this.imagePath;

    this.postService.updatePost(copyPost).subscribe(data=> {
        
        this.form.reset();
        this.router.navigate(['/post/'+this.post.id])
      })
    
  }

  build(){
    this.form = this.formBuilder.group({
      name: [this.post.title,Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(64)])],
      text: [this.post.text,Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(32)])],
      flair: [this.post.flair],
      imagePath: [this.post.imagePath]
    });
  }

  loadPost(id: number) {
    this.postService.getPost2(id).subscribe(data => {
      this.post = data;
      this.build()
      this.loadCommunity(data)
    })
  }

  loadCommunity(post: Post) {
    this.communityService.getPostCommunity(post.id).subscribe(data => {
      this.community = data;
    })
  }


  backButton() {
    this.form.reset();
    this.router.navigate(['/'])
  }

}
