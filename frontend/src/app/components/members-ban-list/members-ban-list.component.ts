import { BanService } from './../../service/ban.service';
import { Banned } from './../../model/banned';
import { Component, Input, OnInit } from '@angular/core';
import { Community } from 'src/app/model/community';
import { User } from 'src/app/model/user';
import { UserService } from 'src/app/service';

@Component({
  selector: 'app-members-ban-list',
  templateUrl: './members-ban-list.component.html',
  styleUrls: ['./members-ban-list.component.css']
})
export class MembersBanListComponent implements OnInit {

  @Input()
  community:Community;
  bannedList:Banned[] = [];
  users:User[] = [];
  banedUsers: User[] = [];


  constructor(
    private banService: BanService,
    private userService: UserService,
  ) { }

  ngOnInit() {
    
    this.loadBannedList()
    this.loadUsers()
    this.loadBannedUsers()
  }

  loadBannedList(){
    this.banService.getCommunityBans(this.community.id).subscribe(data =>{
      this.bannedList = data;
      console.log(data);
    })
  }

  loadUsers(){
    this.userService.getUsers().subscribe(data=>{
      this.users = data;
      console.log(data);
    })
  }

  loadBannedUsers(){
    this.banService.getCommunityBannedUsers(this.community.id).subscribe(data =>{
      this.banedUsers = data;
      console.log(data);
    })
  }

isBanned(user:User):boolean{
  let index = this.banedUsers.findIndex(p => p.username == user.username);
    if (index !== -1) {
      return true
    }else
      return false;
}

}
