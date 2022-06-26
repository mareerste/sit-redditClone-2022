import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { User } from 'src/app/model/user';
import { AuthService, UserService } from 'src/app/service';
import { ImageService } from 'src/app/service/image.service';
interface DisplayMessage {
  msgType: string;
  msgBody: string;
}
@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  user: User;
  showProfil = true;
  editProfile = false;
  image;
  form: FormGroup
  notification: DisplayMessage;
  myProfile: boolean = false;
  changePassword: false;
  validate = true;
  usersKarma = 0;

  selectedFile:File;
  imagePath;

  constructor(
    private userService: UserService,
    private authService: AuthService,
    private imageService:ImageService,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private sanitizer:DomSanitizer,
  ) { }

  ngOnInit() {
    this.userService.getUser(this.route.snapshot.paramMap.get('username')).subscribe(data => {
      this.user = data
      if (data.avatar != null) {

        this.imageService.getImage3(data.avatar).subscribe(data => {
          var unsafeImageUrl = URL.createObjectURL(data);
          this.image = this.sanitizer.bypassSecurityTrustUrl(unsafeImageUrl);
        })
      }
      this.loadUsersKarma()
      this.isThatMe()
    })
    this.form = this.formBuilder.group({
      username: ['', Validators.compose([Validators.minLength(3), Validators.maxLength(64)])],
      displayName: ['', Validators.compose([Validators.minLength(3), Validators.maxLength(64)])],
      password: ['', Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(32)])],
      newPassword: ['', Validators.compose([Validators.minLength(3), Validators.maxLength(32)])],
      repNewPassword: ['', Validators.compose([Validators.minLength(3), Validators.maxLength(32)])],
      description: ['', Validators.compose([Validators.minLength(5), Validators.maxLength(32)])],
      email: ['', Validators.compose([Validators.minLength(10), Validators.maxLength(64), Validators.email])],
      imagePath: []
    });
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
    this.validate = true;

    if (this.authService.isMyPassword(this.form.value.password)) {
      if (this.form.value.displayName != "") {
        this.user.displayName = this.form.value.displayName
      }
      if(this.imagePath != undefined)
        this.user.avatar = this.imagePath;
      if (this.form.value.description != "") {
        this.user.description = this.form.value.description
      }
      if (this.form.value.email != "") {
        this.user.email = this.form.value.email
      }
      if (this.changePassword) {
        if ((this.form.value.newPassword != "") && (this.form.value.newPassword == this.form.value.repNewPassword)) {
          this.user.password = this.form.value.newPassword
        } else {
          this.validate = false
          this.notification = { msgType: 'error', msgBody: "new password and confirm password do not match" };
        }
      }
      if (this.validate) {
        this.userService.updateUser(this.user).subscribe(()=>{
          this.switchLayout()
        })
      }

    } else {
      this.notification = { msgType: 'error', msgBody: "Wrong password" };
    }
  }

  loadUsersKarma(){
    this.userService.getUsersKarma(this.user.username).subscribe(data=>{
      this.usersKarma = data
    })
  }

  isThatMe() {
    var logged: User = this.authService.getCurrentUser()
    if (logged.username == this.user.username)
      this.myProfile = true
  }

  switchLayout() {
    this.showProfil = !this.showProfil
    this.editProfile = !this.editProfile
  }
}
