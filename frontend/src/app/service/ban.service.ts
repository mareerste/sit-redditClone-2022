
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs-compat/Observable';
import { Banned } from '../model/banned';
import { User } from '../model/user';
import { ApiService } from './api.service';
import { ConfigService } from './config.service';

@Injectable({
  providedIn: 'root'
})
export class BanService {

  constructor(
    private apiService: ApiService,
    private config: ConfigService,
    private http: HttpClient
  ) { }

  getCommunityBans(id: number): Observable<Banned[]> {
    return this.http.get<Banned[]>(`${this.config.ban_community_url}/${id}`);
  }

  saveBan(data: Banned): Observable<Banned> {
    const headers = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });
    let options = { headers: headers }
    return this.http.post<Banned>(`${this.config.ban_url}`, data, options)
  }
  deleteBan(id: number) {
    return this.http.delete(`${this.config.ban_url}/${id}`);
  }

  getCommunityBannedUsers(id: number): Observable<User[]> {
    return this.http.get<User[]>(`${this.config.ban_community_url}/${id}/users`);
  }

  // /{idCommunity}/user/{idUser}
  getBanForUserInCommunity(id: number, username: String): Observable<boolean> {
    return this.http.get<boolean>(`${this.config.ban_community_url}/${id}/user/${username}`);
  }

  deleteBanForUserInCommunity(id: number, username: String){
    return this.http.delete(`${this.config.ban_community_url}/${id}/user/${username}`);
  }
}
