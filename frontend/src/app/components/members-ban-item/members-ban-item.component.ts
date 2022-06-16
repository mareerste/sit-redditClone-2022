import { AuthService } from './../../service/auth.service';
import { Banned } from './../../model/banned';
import { Component, Input, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import { Community } from 'src/app/model/community';
import { BanService } from 'src/app/service/ban.service';

@Component({
  selector: 'app-members-ban-item',
  templateUrl: './members-ban-item.component.html',
  styleUrls: ['./members-ban-item.component.css']
})
export class MembersBanItemComponent implements OnInit {
  @Input()
  user:User;
  @Input()
  community:Community;
  @Input()
  banned:boolean;
  ban:Banned;
  showBtn:boolean = false;
  constructor(
    private banService:BanService,
    private auth:AuthService,
  ) { }

  ngOnInit() {
    this.showBtn = this.isModerator(this.user);
  }

  saveBan(){
    var ban = new Banned();
    ban.community = this.community;
    ban.user = this.user;
    this.banService.saveBan(ban).subscribe(data=>{
      this.banned = !this.banned;
    })
  }

  deleteBan(){
    this.banService.deleteBanForUserInCommunity(this.community.id, this.user.username).subscribe(data=>{
      this.banned = !this.banned;
    })
  }

  isModerator(user:User):boolean{
    var moderators : User[] 
    moderators = this.community.moderators;
    let index = moderators.findIndex(m => m.username == user.username);
      if (index !== -1) {
        return true
      }else
        return false;
  }

}
