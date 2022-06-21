import { AuthService } from './../service/auth.service';
import { UserService } from './../service/user.service';

import { ConfigService } from './../service/config.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { User } from '../model/user';

@Component({
  selector: 'main-nav',
  templateUrl: './main-nav.component.html',
  styleUrls: ['./main-nav.component.css']
})
export class MainNavComponent implements OnInit {

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  constructor(private breakpointObserver: BreakpointObserver,
    private router: Router,
    private configService: ConfigService,
    private userService: UserService,
    private authService: AuthService
  ) { }
  ngOnInit(): void {
  }

  hasSignedIn() {
    return !!this.authService.getCurrentUser();
  }
  isLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }

  userName() {
    const user = this.authService.getCurrentUser();
    return user.username;
  }

  logout(): void {
    this.authService.logout();
    // this.router.navigate(['/']);
  }

  showProfile() {
    var user:User = this.authService.getCurrentUser()
    this.router.navigate(['user',user.username])
  }
}
