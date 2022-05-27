import { map } from 'rxjs/operators';
import { ConfigService } from './config.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl = this.config.api_url;

  currentUser:any;
  constructor(
    private http:HttpClient,
    private config: ConfigService) { }

    getMyInfo() {
      return this.http.get(this.config.whoami_url)
        .pipe(map(user => {
          this.currentUser = user;
          return user;
        }));
    }

}
