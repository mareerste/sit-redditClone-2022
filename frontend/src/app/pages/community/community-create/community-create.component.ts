import { Flair } from 'src/app/model/flair';
import { CommunityService } from './../../../service/community.service';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Community } from 'src/app/model/community';
import { ActivatedRoute, Router } from '@angular/router';
import { FlairService } from 'src/app/service/flair.service';
import { AuthService } from 'src/app/service';

interface DisplayMessage {
  msgType: string;
  msgBody: string;
}

@Component({
  selector: 'app-community-create',
  templateUrl: './community-create.component.html',
  styleUrls: ['./community-create.component.css']
})

export class CommunityCreateComponent implements OnInit {

community:Community;
form:FormGroup;
submitted=false;
notification: DisplayMessage;
returnUrl: string;
communityFlairs:Flair[] = [];
private ngUnsubscribe: Subject<void> = new Subject<void>();

  constructor(
    private router:Router,
    private route:ActivatedRoute,
    private formBuilder:FormBuilder,
    private communityService:CommunityService,
    private flairService:FlairService,
    private authSetvice:AuthService,
  ) { }

  ngOnInit() {
    this.route.params
      .pipe(takeUntil(this.ngUnsubscribe))
      .subscribe((params: DisplayMessage) => {
        this.notification = params;
      });
    
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    this.form = this.formBuilder.group({
      name: ['', Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(64)])],
      description: ['', Validators.compose([Validators.required, Validators.minLength(5), Validators.maxLength(32)])],
      rules: new FormArray([
      ]),
      flairs: new FormArray([
      ])
    });
  }

  ngOnDestroy() {
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
  }

  onSubmit() {
    this.notification = undefined;
    if(this.form.valid){
    let promise = new Promise((resolve,reject)=>{
      this.saveFlairs()
      setTimeout(()=>{
        resolve("succes");
      },2000)
      
    })
    promise.then((message=>{
      console.log(message)
    })).then(() => this.saveCommunity())
  }else{
    this.notification = { msgType: 'error', msgBody: "Input all fields" };
  }
  }

  addRule(){
    (<FormArray>this.form.get('rules')).push(new FormControl(null, Validators.required));
  }

  removeRule(i){
    (<FormArray>this.form.get('rules')).removeAt(i);
  }

  addFlair(){
    (<FormArray>this.form.get('flairs')).push(new FormControl(null, Validators.required));
  }

  removeFlair(i){
    (<FormArray>this.form.get('flairs')).removeAt(i);
  }
  saveFlairs():Flair[]{
    this.form.value.flairs.forEach(element => {
      var newFlair = new Flair()
      newFlair.name = element
      this.flairService.saveFlair(newFlair).subscribe(data=>{
        this.communityFlairs.push(data)
      })
    });
    return this.communityFlairs;
  }

  saveCommunity(){
    this.submitted = true;
    this.form.value.flairs = this.communityFlairs;
    this.communityService.saveCommunity(this.form.value)
      .subscribe(data => {
        this.router.navigate([this.returnUrl]);
      },
        error => {
          this.submitted = false;
          console.log('Create community error');
          this.notification = { msgType: 'error', msgBody: error['error'].message };
        });
  }
  
}
