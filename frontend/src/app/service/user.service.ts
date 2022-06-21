import {Injectable} from '@angular/core';
import {ApiService} from './api.service';
import {ConfigService} from './config.service';
import {map} from 'rxjs/operators';
import { Observable } from 'rxjs-compat/Observable';
import { User } from '../model/user';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  currentUser;

  constructor(
    private apiService: ApiService,
    private http:HttpClient,
    private config: ConfigService
  ) {
  }

  getUser(username:String):Observable<User>{
    return this.http.get<User>(`${this.config.user_url}/${username}`)
  }

  updateUser(user:User){
    const headers = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });
    let options = {headers: headers}
    console.log("update service user")
    return this.http.put(`${this.config.user_url}`,user,options);
  }

  getMyInfo() {
    return this.apiService.get(this.config.whoami_url)
      .pipe(map(user => {
        this.currentUser = user;
        return user;
      }));
  }

  getUsers():Observable<User[]> {
    return this.http.get<User[]>(`${this.config.user_url}`);
  }

}
