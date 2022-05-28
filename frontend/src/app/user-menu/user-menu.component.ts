import { CommunityService } from './../service/community.service';
import { Component, OnInit } from '@angular/core';
import {AuthService} from '../service/auth.service';
import {UserService} from '../service/user.service';
import { Community } from '../model/community';

@Component({
  selector: 'app-user-menu',
  templateUrl: './user-menu.component.html',
  styleUrls: ['./user-menu.component.css']
})
export class UserMenuComponent implements OnInit {

  myCommunities:Community[];  

  constructor(
    private authService: AuthService,
    private userService: UserService,
    private communityService:CommunityService,
    ) { }

  ngOnInit() {
    this.communityService.getUsersCommunities(this.authService.getCurrentUser().username).subscribe(data => {
      this.myCommunities = data
    });
  }

  logout() {
    this.authService.logout();
  }

}
