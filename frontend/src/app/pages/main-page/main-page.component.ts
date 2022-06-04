import { UserService } from './../../service/user.service';
import { ConfigService } from './../../service/config.service';
import { Post } from './../../model/post';
import { ActivatedRoute } from '@angular/router';
import { PostService } from './../../service/post.service';
import { BehaviorSubject, Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service';
import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

  whoamIResponse = {};
  posts:Observable<Post[]>;
  refreshPosts = new BehaviorSubject<boolean>(true);
  constructor(
    private postService:PostService, 
    private route:ActivatedRoute,
    private config:ConfigService,
    private userService:UserService,
    private auth:AuthService) { }

  ngOnInit(): void {
    this.posts = this.postService.getPosts();
    // this.posts = this.refreshPosts.pipe(switchMap(_ => this.postService.getPosts()));
  }
  getChange(){
    this.posts = this.postService.getPosts();
  }

  filter(text:string){
    // this.postService.filter(text);
  }

  makeRequest(path) {
    if (this.config.whoami_url.endsWith(path)) {
      this.userService.getMyInfo()
        .subscribe(res => {
          this.forgeResonseObj(this.whoamIResponse, res, path);
        }, err => {
          this.forgeResonseObj(this.whoamIResponse, err, path);
        });
    } else {
      // this.userService.getAll()
      //   .subscribe(res => {
      //     this.forgeResonseObj(this.allUserResponse, res, path);
      //   }, err => {
      //     this.forgeResonseObj(this.allUserResponse, err, path);
      //   });
    }
  }

  forgeResonseObj(obj, res, path) {
    obj['path'] = path;
    obj['method'] = 'GET';
    if (res.ok === false) {
      // err
      obj['status'] = res.status;
      try {
        obj['body'] = JSON.stringify(JSON.parse(res._body), null, 2);
      } catch (err) {
        console.log(res);
        obj['body'] = res.error.message;
      }
    } else {
      // 200
      obj['status'] = 200;
      obj['body'] = JSON.stringify(res, null, 2);
    }
  }


}
