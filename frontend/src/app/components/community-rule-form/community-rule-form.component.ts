import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-community-rule-form',
  templateUrl: './community-rule-form.component.html',
  styleUrls: ['./community-rule-form.component.css']
})
export class CommunityRuleFormComponent implements OnInit {

  form:FormGroup;
  submitted=false;
  ruleRequired = false;
  private ngUnsubscribe: Subject<void> = new Subject<void>();
  @Output()
  saveNewRule = new EventEmitter<String>();

  constructor(
    private formBuilder:FormBuilder,
  ) { }

  ngOnInit() {
    this.form = this.formBuilder.group({
      rule: ['', Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(64)])],
    });
  }

  onSubmit(){
    this.ruleRequired = false;
    if(this.form.valid){
        this.saveNewRule.emit(this.form.value.rule);
    }else{
      this.ruleRequired = true;
      this.submitted = false;
    }
  }

}
