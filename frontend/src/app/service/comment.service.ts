import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
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

  deleteComment(id){
    return this.apiService.delete(`${this.config.comment_url}/${id}`);
  }

}
