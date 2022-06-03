import { Observable } from 'rxjs';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-community-list-atribute',
  templateUrl: './community-list-atribute.component.html',
  styleUrls: ['./community-list-atribute.component.css']
})
export class CommunityListAtributeComponent implements OnInit {

  @Input()
  private title:string;
  @Input()
  private listItems:Observable<any>;
  constructor() { }

  ngOnInit() {
  }

}
