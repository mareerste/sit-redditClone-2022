import { Report } from './../../model/report';
import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-reported-posts-details',
  templateUrl: './reported-posts-details.component.html',
  styleUrls: ['./reported-posts-details.component.css']
})
export class ReportedPostsDetailsComponent implements OnInit {

  @Input()
  report:Report
  constructor(
    private router:Router,
  ) { }

  ngOnInit() {
  }

  showPost() {
    this.router.navigate(
      ['post', this.report.post.id]
    )
  }
}
