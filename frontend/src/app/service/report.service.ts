import { Report } from './../model/report';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Community } from '../model/community';
import { ApiService } from './api.service';
import { ConfigService } from './config.service';

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  constructor(
    private apiService: ApiService,
    private config: ConfigService,
    private http:HttpClient
  ) { }

  saveReport(data:Report):Observable<Report>{
    const headers = new HttpHeaders({
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        });
        let options = {headers: headers}
        return this.http.post<Report>(`${this.config.report_url}`, data, options)
  }

  getCommunityReportedPosts(community:Community):Observable<Report[]>{
    return this.http.get<Report[]>(`${this.config.report_community_url}/${community.id}/posts`);
  }

  getCommunityReportedComments():Observable<Report[]>{
    return this.http.get<Report[]>(`${this.config.report_url}/comments`);
  }

  acceptReport(data:Report):Observable<Report>{
    const headers = new HttpHeaders({
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        });
        let options = {headers: headers}
        return this.http.put<Report>(`${this.config.report_accept_url}`, data, options)
  }

  declineReport(data:Report):Observable<Report>{
    const headers = new HttpHeaders({
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        });
        let options = {headers: headers}
        return this.http.put<Report>(`${this.config.report_decline_url}`, data, options)
  }
}
