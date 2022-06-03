import { Observable } from 'rxjs/Observable';
import { Component, Input, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';

@Component({
  selector: 'app-community-list-moderators',
  templateUrl: './community-list-moderators.component.html',
  styleUrls: ['./community-list-moderators.component.css']
})
export class CommunityListModeratorsComponent implements OnInit {

  @Input()
  private title:string;
  @Input()
  private listItems:Observable<User>;
  constructor() { }

  ngOnInit() {
  }

}
