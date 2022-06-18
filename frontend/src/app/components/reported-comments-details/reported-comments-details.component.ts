import { Component, Input, OnInit } from '@angular/core';
import { Report } from 'src/app/model/report';

@Component({
  selector: 'app-reported-comments-details',
  templateUrl: './reported-comments-details.component.html',
  styleUrls: ['./reported-comments-details.component.css']
})
export class ReportedCommentsDetailsComponent implements OnInit {

  @Input()
  report:Report;
  constructor() { }

  ngOnInit() {
  }

}
