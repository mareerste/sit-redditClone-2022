import { Router } from '@angular/router';
import { Post } from './../../model/post';
import { Observable } from 'rxjs';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.css']
})
export class PostListComponent implements OnInit {

  @Input()
  posts:Observable<Post[]>;
  @Output()
  clickedEventEmit = new EventEmitter<void>();
  constructor(private router:Router){}

  ngOnInit(): void {
  }

  getChange(){
    this.clickedEventEmit.emit();
  }

}
