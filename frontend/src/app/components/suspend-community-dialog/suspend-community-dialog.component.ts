import { Community } from 'src/app/model/community';
import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material';
import { CommunityService } from 'src/app/service/community.service';
import { NotifierService } from 'src/app/service/notifier.service';

@Component({
  selector: 'app-suspend-community-dialog',
  templateUrl: './suspend-community-dialog.component.html',
  styleUrls: ['./suspend-community-dialog.component.css']
})
export class SuspendCommunityDialogComponent implements OnInit {

  form:FormGroup
  reasonRequired = false;
  constructor(@Inject(MAT_DIALOG_DATA) public data:any,
  private communityService:CommunityService,
  private notifierService:NotifierService,
  private formBuilder:FormBuilder,
  ) { }

  ngOnInit() {
    this.form = this.formBuilder.group({
      reason: ['', Validators.compose([Validators.required, Validators.minLength(10), Validators.maxLength(64)])],
    });
  }

  onSubmitSuspend(){
    this.reasonRequired = false;
    if(this.form.valid){
      var community:Community = this.data.community
      community.suspended = true;
      community.suspendedReason = this.form.value.reason;
      this.communityService.updateCommunity(community).subscribe(data=>{console.log(data)})
      
    }else{
      this.reasonRequired = true;
    }
  }

}
