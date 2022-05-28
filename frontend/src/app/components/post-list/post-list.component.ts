import { Router } from '@angular/router';
import { Post } from './../../model/post';
import { Observable } from 'rxjs';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.css']
})
export class PostListComponent implements OnInit {

  @Input()
  posts:Observable<Post[]>;
  constructor(private router:Router){}

  ngOnInit(): void {
  }

}
