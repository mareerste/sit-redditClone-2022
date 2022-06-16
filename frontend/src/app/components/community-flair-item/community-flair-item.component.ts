import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Flair } from 'src/app/model/flair';

@Component({
  selector: 'app-community-flair-item',
  templateUrl: './community-flair-item.component.html',
  styleUrls: ['./community-flair-item.component.css']
})
export class CommunityFlairItemComponent implements OnInit {

  @Input()
  private flair:Flair;
  @Output()
  deleteFlairEmmiter = new EventEmitter<Flair>();
  
  constructor() { }

  ngOnInit() {
  }

  deleteFlair(){
    this.deleteFlairEmmiter.emit(this.flair)
  }

}
