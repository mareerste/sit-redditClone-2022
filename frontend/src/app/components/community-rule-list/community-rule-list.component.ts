import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-community-rule-list',
  templateUrl: './community-rule-list.component.html',
  styleUrls: ['./community-rule-list.component.css']
})
export class CommunityRuleListComponent implements OnInit {

  @Input()
  rules:String[] = [];
  @Output()
  deleteRuleEmmiter = new EventEmitter<string>();

  constructor() { }

  ngOnInit() {
  }

  deleteRule(data:string){
    this.deleteRuleEmmiter.emit(data);
  }

}
