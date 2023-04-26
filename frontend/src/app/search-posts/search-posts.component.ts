import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-search-posts',
  templateUrl: './search-posts.component.html',
  styleUrls: ['./search-posts.component.css']
})
export class SearchPostsComponent implements OnInit {

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
