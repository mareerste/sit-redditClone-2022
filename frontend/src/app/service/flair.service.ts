import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Flair } from '../model/flair';
import { ApiService } from './api.service';
import { ConfigService } from './config.service';

@Injectable({
  providedIn: 'root'
})
export class FlairService {

  constructor(
    private apiService: ApiService,
    private config: ConfigService,
    private http:HttpClient
  ) { }

  saveFlair(data:Flair):Observable<Flair>{
    const headers = new HttpHeaders({
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        });
        let options = {headers: headers}
        // data.flair = this.community.flairs[0]
        console.log("ovde")
        console.log(data)
        return this.http.post<Flair>(`${this.config.flair_url}`, data, options)
            // .pipe(map(() => {
            //   console.log('Post created successfully');
            // }));
  }
}
