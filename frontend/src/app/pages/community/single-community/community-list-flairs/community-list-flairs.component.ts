import { Observable } from 'rxjs/Observable';
import { Component, Input, OnInit } from '@angular/core';
import { Flair } from 'src/app/model/flair';

@Component({
  selector: 'app-community-list-flairs',
  templateUrl: './community-list-flairs.component.html',
  styleUrls: ['./community-list-flairs.component.css']
})
export class CommunityListFlairsComponent implements OnInit {

  @Input()
  private title:string;
  @Input()
  private listItems:Observable<Flair>;
  constructor() { }

  ngOnInit() {
  }

}
