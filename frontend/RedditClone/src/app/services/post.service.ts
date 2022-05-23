import { Post } from './../model/post';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpParams } from '@angular/common/http';
import { ApiService } from './api.service';
import { ConfigService } from './config.service';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  private baseUrl = this.config.api_url;
  private allPostsUrl = this.config.all_posts_url;
  // private commPostsUrl = this.config.community_posts_url;
  private postUrl = this.config.post_url;

  constructor(private http:HttpClient,
    private config: ConfigService) { }

    getPosts(): Observable<Post[]>{
      return this.http.get<Post[]>(`${this.allPostsUrl}`);
    }

    getPost(id:number):Observable<Post> {
      return this.http.get<Post>(`${this.postUrl}/${id}`);
    }

    // addPost(post:Post):Observable<Post> {
    //   return this.http.post<Post>(`${this.commPostsUrl}`,post);
    // }

    updatePost(post:Post):Observable<Post> {
      return this.http.put<Post>(`${this.postUrl}`,post);
    }

    deletePost(id:number):Observable<void> {
      return this.http.delete<void>(`${this.postUrl}/${id}`);
    }

    filter(entryText:string):Observable<Post[]>{
      // const params:HttpParams = new HttpParams().set('entry',entryText);
      let params:HttpParams = new HttpParams();
      params = params.append('entry',entryText);
      return this.http.get<Post[]>(this.allPostsUrl,{params});
    }

  }
