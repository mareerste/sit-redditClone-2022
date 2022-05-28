import { JwtUtilsService } from './jwt-utils.service';
import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { ApiService } from './api.service';
import { UserService } from './user.service';
import { ConfigService } from './config.service';
import { catchError, map } from 'rxjs/operators';
import { Router } from '@angular/router';
import { of } from 'rxjs/internal/observable/of';
import { Observable } from 'rxjs';
import { _throw } from 'rxjs/observable/throw';

@Injectable()
export class AuthService {

  constructor(
    private apiService: ApiService,
    private userService: UserService,
    private config: ConfigService,
    private router: Router,
    private jwtUtilsService:JwtUtilsService
  ) {
  }

  private access_token = null;

  login(user) {
    const loginHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });
    // const body = `username=${user.username}&password=${user.password}`;
    const body = {
      'username': user.username,
      'password': user.password
    };
    return this.apiService.post(this.config.login_url, JSON.stringify(body), loginHeaders)
      .pipe(map((res) => {
        console.log('Login success');
        this.access_token = res.accessToken;
        localStorage.setItem("jwt", res.accessToken)
        localStorage.setItem('currentUser', JSON.stringify({
          username: user.username,
          roles: this.jwtUtilsService.getRoles(res.accessToken),
          token: res.accessToken
        }));
      }));
  }

  signup(user) {
    const signupHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });
    return this.apiService.post(this.config.signup_url, JSON.stringify(user), signupHeaders)
      .pipe(map(() => {
        console.log('Sign up success');
      }));
  }

  // logout() {
  //   this.userService.currentUser = null;
  //   this.access_token = null;
  //   this.router.navigate(['/']);
  // }

  tokenIsPresent() {
    return this.access_token != undefined && this.access_token != null;
  }

  // getToken() {
  //   return this.access_token;
  // }

  getToken(): String {
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    var token = currentUser && currentUser.token;
    return token ? token : "";
  }

  logout(): void {
    localStorage.removeItem('currentUser');
    localStorage.removeItem('jwt');
  }

  isLoggedIn(): boolean {
    if (this.getToken() != '') return true;
    else return false;
  }

  getCurrentUser() {
    if (localStorage.currentUser) {
      return JSON.parse(localStorage.currentUser);
    }
    else {
      return undefined;
    }
  }

}
