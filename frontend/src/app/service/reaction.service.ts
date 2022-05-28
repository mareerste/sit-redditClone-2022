import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { ConfigService } from './config.service';
import { ApiService } from './api.service';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ReactionService {

  constructor(
    private apiService: ApiService,
    private config: ConfigService,
    private http:HttpClient
  ) {
  }

  getPosts() {
    return this.apiService.get(this.config.all_posts_url);
  }

  getKarmaForPost(id:number):Observable<number> {
    return this.http.get<number>(`${this.config.reaction_url}/post/${id}/karma`);
  }

  getKarmaForComment(id:number):Observable<number> {
    return this.http.get<number>(`${this.config.reaction_url}/comment/${id}/karma`);
  }
}
