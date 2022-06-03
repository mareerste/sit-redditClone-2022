import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Post } from './../model/post';
import {Injectable} from '@angular/core';
import {ApiService} from './api.service';
import {ConfigService} from './config.service';

@Injectable()
export class PostService {

  constructor(
    private apiService: ApiService,
    private config: ConfigService,
    private http:HttpClient
  ) {
  }

  getPosts() {
    return this.apiService.get(this.config.all_posts_url);
  }

  getPost(id){
    return this.apiService.get(this.config.post_url,id);
  }

  getPost2(id:number):Observable<Post> {
    return this.http.get<Post>(`${this.config.post_url}/${id}`);
  }

  updatePost(post:Post){
    return this.apiService.put(this.config.post_url,post);
  }

  deletePost(id){
    return this.apiService.delete(this.config.post_url,id);
  }

  filter(entryText:string):Observable<Post[]>{
    // const params:HttpParams = new HttpParams().set('entry',entryText);
    let params:HttpParams = new HttpParams();
    params = params.append('entry',entryText);
    return this.apiService.get(this.config.all_posts_url,{params});
  }

}
