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

  constructor(private http:HttpClient,
    private config: ConfigService) { }

    getPosts(): Observable<Post[]>{
      return this.http.get<Post[]>(`${this.allPostsUrl}`);
    }

  }
