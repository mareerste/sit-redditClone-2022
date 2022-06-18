import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-community-rule-item',
  templateUrl: './community-rule-item.component.html',
  styleUrls: ['./community-rule-item.component.css']
})
export class CommunityRuleItemComponent implements OnInit {

  @Input()
  private rule:string;
  @Output()
  deleteRuleEmmiter = new EventEmitter<string>();
  
  constructor() { }

  ngOnInit() {
  }

  deleteRule(){
    this.deleteRuleEmmiter.emit(this.rule)
  }

}
