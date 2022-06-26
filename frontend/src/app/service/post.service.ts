import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
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

  getPost(id:number):Observable<any> {
    return this.http.get(`${this.config.post_url}/${id}`, {observe: 'response'});
  }

  getPost2(id:number):Observable<Post> {
    return this.http.get<Post>(`${this.config.post_url}/${id}`);
  }

  updatePost(post:Post){
    const headers = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });
    let options = {headers: headers}
    console.log("update service post")
    console.log(post)
    return this.http.put(`${this.config.post_url}`,post,options);
  }

  deletePost(id){
    return this.http.delete(`${this.config.post_url}/${id}`);
  }

  filter(entryText:string):Observable<Post[]>{
    // const params:HttpParams = new HttpParams().set('entry',entryText);
    let params:HttpParams = new HttpParams();
    params = params.append('entry',entryText);
    return this.apiService.get(this.config.all_posts_url,{params});
  }

}
