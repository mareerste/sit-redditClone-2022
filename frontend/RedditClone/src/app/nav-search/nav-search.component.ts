import { Component, OnInit } from '@angular/core';
import { EventEmitter } from '@angular/core';
import { Output } from '@angular/core';

@Component({
  selector: 'nav-search',
  templateUrl: './nav-search.component.html',
  styleUrls: ['./nav-search.component.scss']
})
export class NavSearchComponent implements OnInit {

  @Output()
  filterEntriesEvent:EventEmitter<string> = new EventEmitter();

  filterText:any;

  constructor() { }

  ngOnInit() {
    this.filterText='';
  }

  filter(){
    this.filterEntriesEvent.emit(this.filterText);
  }

  reset(){
    this.filterText='';
    this.filter();
  }

}
