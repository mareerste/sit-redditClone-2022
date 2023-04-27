import { Flair } from 'src/app/model/flair';
import { map } from 'rxjs/operators';
import { Post } from 'src/app/model/post';
import { Observable } from 'rxjs';
import { ConfigService } from './config.service';
import { ApiService } from './api.service';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Community } from '../model/community';
import { CommunityES } from '../model/communityES';

@Injectable({
  providedIn: 'root'
})
export class CommunityService {

  constructor(
    private apiService: ApiService,
    private config: ConfigService,
    private http: HttpClient
  ) {
  }

  saveCommunity(data: Community): Observable<Community> {
    const headers = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });
    let options = { headers: headers }
    return this.http.post<Community>(`${this.config.community_url}`, data, options)
  }

  savePostInCommunity(data: Post, commId: number): Observable<Post> {
    const headers = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });
    let options = { headers: headers }

    return this.http.post<Post>(`${this.config.community_url}/${commId}/posts`, data, options)
  }
  savePostInCommunityPDF(data: Post, commId: number): Observable<Post> {
    const formData: FormData = new FormData();
    formData.append('title', data.title);
    formData.append('text', data.text);
    formData.append('flair.id', data.flair.id.toString());
    formData.append('flair.name', data.flair.name);
    formData.append('files', data.files);
    if (data.imagePath != undefined) {
      formData.append('imagePath', data.imagePath);
    }
    const headers = new HttpHeaders({
      'enctype': 'multipart/form-data'
    });
    let options = { headers: headers }
    return this.http.post<Post>(`${this.config.community_url}/pdf/${commId}/posts`, formData, options)
  }

  saveCommunityPDF(data: Community): Observable<Community> {
    const formData = new FormData();
    formData.append('files', data.files);
    formData.append('name', data.name);
    formData.append('description', data.description);
    var rules = this.mapRules(data.rules)
    formData.append('rules', rules);
    for (let i = 0; i < data.flairs.length; i++) {
      const element = data.flairs[i];
      formData.append('flairs['+i+'].id', element.id.toString());
      formData.append('flairs['+i+'].name', element.name);
    }

    const headers = new HttpHeaders({
      'enctype': 'multipart/form-data'
    });
    let options = { headers: headers }
    // return null;
    return this.http.post<Community>(`${this.config.community_url}/pdf`, formData, options)
  }

  mapRules(rules:string[]): string {
    var retVal = ''
    rules.forEach(element => {
      if(retVal != ''){
        retVal = retVal + ', ' + element  
      }else{
        retVal += element
      }
      
    });
    return retVal;
  }

  getUsersCommunities(username: string): Observable<Community[]> {
    return this.http.get<Community[]>(`${this.config.user_url}/${username}/communities`);
  }

  getPostCommunity(id: number): Observable<Community> {
    return this.http.get<Community>(`${this.config.post_url}/${id}/community`);
  }

  updateCommunity(data: Community): Observable<Community> {
    const headers = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });
    let options = { headers: headers }

    return this.http.put<Community>(`${this.config.community_url}`, data, options)
  }
  getCommunity(id): Observable<Community> {
    return this.http.get<Community>(`${this.config.community_url}/${id}`);
  }

  //ES APIs
  getCommunityByName(name: string): Observable<CommunityES[]> {
    return this.http.get<CommunityES[]>(`${this.config.community_url}/name/${name}`);
  }

  getCommunityByDescription(description: string): Observable<CommunityES[]> {
    return this.http.get<CommunityES[]>(`${this.config.community_url}/description/${description}`);
  }

  getCommunityByRule(rule: string): Observable<CommunityES[]> {
    return this.http.get<CommunityES[]>(`${this.config.community_url}/rule/${rule}`);
  }

  getCommunityByPostRange(min: number, max: number): Observable<CommunityES[]> {
    return this.http.get<CommunityES[]>(`${this.config.community_url}/posts/${min}/${max}`);
  }

  getCommunityByKarma(min: number, max: number): Observable<CommunityES[]> {
    return this.http.get<CommunityES[]>(`${this.config.community_url}/karma/${min}/${max}`);
  }

  getCommunityByCombinedSearch(searchType: string, name: string, description: string, rule: string, minPost: number, maxPost: number, minKarma: number, maxKarma: number): Observable<CommunityES[]> {
    return this.http.get<CommunityES[]>(`${this.config.community_url}/search/${searchType}/${name}/${description}/${rule}/${minPost}/${maxPost}/${minKarma}/${maxKarma}`);
  }

  getCommunityPosts(id) {
    return this.apiService.get(`${this.config.community_url}/${id}/posts`);
  }

  deleteCommunityPost(idCommunity, idPost) {
    return this.apiService.delete(`${this.config.community_url}/${idCommunity}/posts/${idPost}`);
  }
}
