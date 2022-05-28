import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'nav-search',
  templateUrl: './nav-search.component.html',
  styleUrls: ['./nav-search.component.css']
})
export class NavSearchComponent implements OnInit {

  @Output()
  filterEntriesEvent:EventEmitter<string> = new EventEmitter();

  filterText:String = "";

  constructor(private route:ActivatedRoute, private router:Router) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      if(params["filterText"])
        this.filterText = params["filterText"];
    })
  }

  filter(){
    // this.filterEntriesEvent.emit(this.filterText);
  }

  search():void{
    if(this.filterText)
    this.router.navigateByUrl('/search/'+this.filterText);
  }

  reset(){
    this.filterText='';
    this.filter();
  }

}
