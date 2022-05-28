import { Observable } from 'rxjs';
import { ConfigService } from './config.service';
import { ApiService } from './api.service';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
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

  getUsersCommunities(username:string):Observable<Community[]> {
    return this.http.get<Community[]>(`${this.config.user_url}/${username}/communities`);
  }

}
