import { map } from 'rxjs/operators';
import { Post } from 'src/app/model/post';
import { Observable } from 'rxjs';
import { ConfigService } from './config.service';
import { ApiService } from './api.service';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Community } from '../model/community';

@Injectable({
  providedIn: 'root'
})
export class CommunityService {

  constructor(
    private apiService: ApiService,
    private config: ConfigService,
    private http:HttpClient
  ) {
  }

  saveCommunity(data:Community):Observable<Community>{
    const headers = new HttpHeaders({
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        });
        let options = {headers: headers}
        return this.http.post<Community>(`${this.config.community_url}`, data, options)
  }

  savePostInCommunity(data:Post, commId:number):Observable<Post>{
    const headers = new HttpHeaders({
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        });
        let options = {headers: headers}
        
        return this.http.post<Post>(`${this.config.community_url}/${commId}/posts`, data, options)
  }
  // createPost(post) {
  //   const createPostHeaders = new HttpHeaders({
  //     'Accept': 'application/json',
  //     'Content-Type': 'application/json'
  //   });
  //   post.flair = this.community.flairs[0]
  //   console.log(JSON.stringify(post));
    
  //   return this.apiService.post(`${this.config.community_url}/${this.community.id}/posts`, JSON.stringify(post), createPostHeaders)
  //     .pipe(map(() => {
  //       console.log('Post created successfully');
  //     }));
  // }

  getUsersCommunities(username:string):Observable<Community[]> {
    return this.http.get<Community[]>(`${this.config.user_url}/${username}/communities`);
  }

  getPostCommunity(id:number):Observable<Community> {
    return this.http.get<Community>(`${this.config.post_url}/${id}/community`);
  }

  updateCommunity(data:Community):Observable<Community>{
    const headers = new HttpHeaders({
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        });
        let options = {headers: headers}
        // data.flair = this.community.flairs[0]
        console.log("ovde")
        console.log(data)
        return this.http.put<Community>(`${this.config.community_url}`, data, options)
  }
  getCommunity(id):Observable<Community>{
    return this.http.get<Community>(`${this.config.community_url}/${id}`);
  }

  getCommunityPosts(id) {
    return this.apiService.get(`${this.config.community_url}/${id}/posts`);
  }

  deleteCommunityPost(idCommunity, idPost) {
    console.log(`${this.config.community_url}/${idCommunity}/posts/${idPost}`)
    return this.apiService.delete(`${this.config.community_url}/${idCommunity}/posts/${idPost}`);
  }
}
