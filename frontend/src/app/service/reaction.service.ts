import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ConfigService } from './config.service';
import { ApiService } from './api.service';
import { Injectable } from '@angular/core';
import { Reaction } from '../model/reaction';

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

  sendReaction(data:Reaction){
    const createPostHeaders = new HttpHeaders({
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        });
        console.log(data)
        return this.apiService.post(`${this.config.reaction_url}`, data, createPostHeaders)
            .pipe(map(() => {
              console.log('Reaction created successfully');
            }));
  }
}
