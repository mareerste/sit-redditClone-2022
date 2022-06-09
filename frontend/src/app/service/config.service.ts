import { HttpClient } from '@angular/common/http';
import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  constructor(private http: HttpClient){}

  private _api_url = 'http://localhost:8080/RedditClone';
  get api_url(): string {
    return this._api_url;
  }
  private _user_url = this._api_url + '/user';
  get user_url(): string {
    return this._user_url;
  }

  private _login_url = this._api_url + '/user/login';
  get login_url(): string {
    return this._login_url;
  }

  private _whoami_url = this._user_url + '/whoami';
  get whoami_url(): string {
    return this._whoami_url;
  }


  private _all_posts_url = this._api_url + '/post/all';
  get all_posts_url(): string {
    return this._all_posts_url;
  }

  private _community_url = this._api_url + '/community';
  get community_url(): string {
    return this._community_url;
  }

  private _comment_url = this._api_url + '/comment';
  get comment_url(): string {
    return this._comment_url;
  }

  private _flair_url = this._api_url + '/flair';
  get flair_url(): string {
    return this._flair_url;
  }

  // /{id}/posts
  private _community_posts_url = this._api_url + '/community/{id}/posts';
  get community_posts_url(): string {
    return this._community_posts_url;
  }

  private _post_url = this._api_url + '/post';
  get post_url(): string {
    return this._post_url;
  }
  
  private _signup_url = this._api_url + '/signup';
  get signup_url(): string {
    return this._signup_url;
  }

  private _reaction_url = this._api_url + '/reaction';
  get reaction_url(): string {
    return this._reaction_url;
  }

}
