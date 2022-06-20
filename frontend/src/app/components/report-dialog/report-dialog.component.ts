import { Comment } from 'src/app/model/comment';
import { Component, Inject, Input, OnInit } from '@angular/core';
import { ReasonMapping, ReportReason } from 'src/app/model/enumerations/report-reason.enum';
import { Post } from 'src/app/model/post';
import { Report } from 'src/app/model/report';
import { MAT_DIALOG_DATA } from '@angular/material';
import { ReportService } from 'src/app/service/report.service';
import { NotifierService } from 'src/app/service/notifier.service';

@Component({
  selector: 'app-report-dialog',
  templateUrl: './report-dialog.component.html',
  styleUrls: ['./report-dialog.component.css']
})
export class ReportDialogComponent implements OnInit {

  reasonRequired = false;
  selectedReason = null;
  reasons = ReportReason;
  enumKeys = []

  public ReasonMapping = ReasonMapping;
  constructor(@Inject(MAT_DIALOG_DATA) public data:any,
  private reportService:ReportService,
  private notifierService:NotifierService
  ) { 
    this.enumKeys = Object.keys(this.reasons).filter(f => !isNaN(Number(f)));
  }

  select(value:string){
    this.selectedReason = this.reasons[value];
  }

  ngOnInit() {
  }

  onNoClick(): void {
    
  }
  onSubmitReport(){
    this.reasonRequired = false;
    if(this.selectedReason != null){
      var newReport = new Report()
      newReport.reason = this.selectedReason
      if(this.data.post != null)
        newReport.post = this.data.post;
      if(this.data.comment != null)
        newReport.comment = this.data.comment
      console.log(newReport)
      this.reportService.saveReport(newReport).subscribe(data=>{
        this.notifierService.showNotification("Report successfully created!")
      },
      error => {
        this.notifierService.showNotification("You are already report this")
        console.log(error)
        console.log(error['status'])
      });
      
      //close
    }else{
      this.reasonRequired = true;
    }


  }

}
