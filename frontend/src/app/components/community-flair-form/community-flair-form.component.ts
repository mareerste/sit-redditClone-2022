import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { Flair } from 'src/app/model/flair';
import { FlairService } from 'src/app/service/flair.service';

@Component({
  selector: 'app-community-flair-form',
  templateUrl: './community-flair-form.component.html',
  styleUrls: ['./community-flair-form.component.css']
})
export class CommunityFlairFormComponent implements OnInit {

  form:FormGroup;
  submitted=false;
  flairRequired = false;
  private ngUnsubscribe: Subject<void> = new Subject<void>();
  @Output()
  saveNewFlair = new EventEmitter<Flair>();

  constructor(
    private flairService:FlairService,
    private formBuilder:FormBuilder,
    ) { }

  ngOnInit() {
    this.form = this.formBuilder.group({
      flair: ['', Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(64)])],
    });
  }

  ngOnDestroy() {
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
  }

  onSubmit(){
    this.flairRequired = false;
    if(this.form.valid){
      var flair = new Flair();
      flair.name = this.form.value.flair;
      this.flairService.saveFlair(flair).subscribe(data=>{
        console.log(data)
        this.saveNewFlair.emit(data);
        
      },error => {
        this.submitted = false;
        console.log('Create community error');
        
      })
    }else{
      this.flairRequired = true;
      this.submitted = false;
    }
  }

}
