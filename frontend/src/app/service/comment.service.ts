import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Comment } from '../model/comment';
import { ApiService } from './api.service';
import { ConfigService } from './config.service';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(
    private apiService:ApiService,
    private config:ConfigService,
    private http:HttpClient
  ) { }

  saveComment(data:Comment):Observable<Comment>{
    const headers = new HttpHeaders({
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        });
        let options = {headers: headers}
        console.log(data)
        return this.http.post<Comment>(`${this.config.comment_url}`, data, options)
  }

  updateComment(comment:Comment){
    const headers = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });
    let options = {headers: headers}
    console.log("update service comment")
    return this.http.put(`${this.config.comment_url}`,comment, options);
  }

  deleteComment(id){
    return this.apiService.delete(`${this.config.comment_url}/${id}`);
  }

  deleteLogicComment(id){
    return this.apiService.delete(`${this.config.comment_delete_url}/${id}`);
  }

}
