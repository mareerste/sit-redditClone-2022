import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { ConfigService } from './config.service';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  constructor(
    private apiService: ApiService,
    private config: ConfigService,
    private http: HttpClient
  ) { }

  saveImage(formData: FormData): Observable<any> {
    return this.http.post<any>(`${this.config.image_url}`, formData)
  }

  getImage(path: string): Observable<any> {
    let params = new HttpParams();
    params = params.append("path", path);
    const options = {
      params: params
    };
    return this.http.get<any>(`${this.config.image_url}`, options)
  }

  getImage2(imageUrl: string): Observable<Blob> {
    return this.http.get(`${this.config.image_url}`, { responseType: 'blob' });
  }

  getImage3(path: string): Observable<any> {
    let params = new HttpParams();
    params = params.append("path", path);
    let headers = new HttpHeaders();
    headers = headers.append('Content-type', 'image/jpg');
    headers = headers.append('Accept' , 'application/json')
    const options = {
      encoding: null,
      params: params,
      headers:headers
      
    };
    return this.http.get<any>(`${this.config.image_url}/get`, options)
  }

  // async getImage4(path: string): Promise<Observable<Blob>> {
  //   let params = new HttpParams();
  //   params = params.append("path", path);
  
  //   const options = { encoding : null, url : "http://localhost:8080/RedditClone/image/get", method : 'GET', headers : { 'Accept' : 'application/json', 'Content-type' : 'image/jpg', }, params:params }; 
  //   // return this.http.get<Blob>(`${this.config.image_url}/get`, options)
  //   const response = await fetch(this.config.image_url+'/get')
  //   const data = await response.json()
    
  // }

  
}
