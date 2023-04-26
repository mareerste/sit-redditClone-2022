import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-search-communities',
  templateUrl: './search-communities.component.html',
  styleUrls: ['./search-communities.component.css']
})
export class SearchCommunitiesComponent implements OnInit {

  @Input()
  result: []

  @Output()
  clickedEventEmit = new EventEmitter<boolean>();
  constructor() { }

  ngOnInit() {
  }

  stopSearching(){
    this.clickedEventEmit.emit(false);
  }

}
