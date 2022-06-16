import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Flair } from 'src/app/model/flair';

@Component({
  selector: 'app-community-flair-list',
  templateUrl: './community-flair-list.component.html',
  styleUrls: ['./community-flair-list.component.css']
})
export class CommunityFlairListComponent implements OnInit {

  @Input()
  flairs:Flair[] = [];
  @Output()
  deleteFlairEmmiter = new EventEmitter<Flair>();

  constructor() { }

  ngOnInit() {
  }

  deleteFlair(data:Flair){
    this.deleteFlairEmmiter.emit(data);
  }

}
