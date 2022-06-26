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

  getImage3(path: string): Observable<Blob> {
    let params = new HttpParams();
    params = params.append("path", path);
    let headers = new HttpHeaders();
    headers = headers.append('Content-type', 'image/jpg');
    headers = headers.append('Accept' , 'application/json')
  
    const options = {
      encoding: null,
      params: params,
      headers:headers,
      responseType: 'blob' 
    };

    return this.http.get(`${this.config.image_url}/get`,{responseType:'blob',headers:headers,params: params})
  }


  
}
