import { Reaction } from './../model/reaction';
import { Comment } from './../model/comment';
import { Observable } from 'rxjs';
import { Post } from './../model/post';
import { ConfigService } from './config.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ReactionService {
  private baseUrl = this.config.api_url;
private reactionURL = this.config.reaction_url;

  constructor(private http:HttpClient,
    private config: ConfigService) { }

    getKarmaForPost(id:number):Observable<number> {
      return this.http.get<number>(`${this.reactionURL}/post/${id}/karma`);
    }

    getPostReactions(id:number): Observable<Reaction[]>{
      return this.http.get<Reaction[]>(`${this.reactionURL}/post/${id}`);
    }

    getKarmaForComment(id:number):Observable<number> {
      return this.http.get<number>(`${this.reactionURL}/comment/${id}/karma`);
    }

}
