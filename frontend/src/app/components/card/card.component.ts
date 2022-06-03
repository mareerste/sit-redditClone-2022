import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  @Input()
  private title:string;
  @Input()
  private text:string;
  @Input()
  private button:string;

  @Output()
  clickedEventEmit = new EventEmitter<void>();

  constructor() { }
  ngOnInit() {
  }

  clickOnButton(){
    this.clickedEventEmit.emit();
  }

}
