import { MatDialog } from '@angular/material';
import { ReactionService } from './../../../service/reaction.service';
import { Comment } from './../../../model/comment';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NotifierService } from 'src/app/service/notifier.service';
import { AuthService } from 'src/app/service';
import { ReactionType } from 'src/app/model/enumerations/reaction-type.enum';
import { CommentService } from 'src/app/service/comment.service';
import { ReportDialogComponent } from '../../report-dialog/report-dialog.component';

@Component({
  selector: 'app-comment-item',
  templateUrl: './comment-item.component.html',
  styleUrls: ['./comment-item.component.css']
})
export class CommentItemComponent implements OnInit {

  @Input()
  comment:Comment;
  @Input()
  showComments:boolean;
  showComment:boolean = true;
  editComment:boolean = false;
  textRequired= false;
  @Input()
  isBanned:boolean;
  karma:number = 0;
  form: FormGroup;
  formEditComment: FormGroup;
  @Output()
  clickedEventEmit = new EventEmitter<ReactionType>()
  @Output()
  clickedEventEmitDelete = new EventEmitter<Comment>();
  // @Output()
  // CommentEventEmit = new EventEmitter<Comment>()

  constructor(
    private reactionService:ReactionService,
    private formBuilder: FormBuilder,
    private notifierService: NotifierService,
    private auth:AuthService,
    private commentService: CommentService,
    private dialog:MatDialog,
    ) { }

  ngOnInit(): void {
    this.loadKarma()
    this.form = this.formBuilder.group({
      type: ['', Validators.compose([Validators.required])],
      comment: ['', Validators.compose([])]
    });

    this.formEditComment = this.formBuilder.group({
      text: ['', Validators.compose([Validators.required])],
    });
  }

  onSubmitUp() {
    if (this.isLoggedIn()) {
      this.form.value.type = "UPVOTE"
      this.form.value.comment = this.comment.id
    
      this.reactionService.sendReaction(this.form.value)
        .subscribe(data => {
          this.loadKarma()
        },
          error => {
            this.notifierService.showNotification("You are already up vote this comment")
            console.log(error)
            console.log(error['status'])
            console.log('Create reaction error');
          });
    } else {
      this.notifierService.showNotification("You need to login first")
    }
  }

  openReportDialog(): void {
    this.dialog.open(ReportDialogComponent,{data:{comment:this.comment}})    
  }

  onSubmitDown() {
    if (this.isLoggedIn()) {
      this.form.value.type = "DOWNVOTE"
      this.form.value.comment = this.comment.id
      this.reactionService.sendReaction(this.form.value)
        .subscribe(data => {
          this.loadKarma()
          console.log(data)
        },
          error => {
            this.notifierService.showNotification("You are already down vote this comment")
            console.log(error)
            console.log(error['status'])
            console.log('Create reaction error');
          });
    } else {
      this.notifierService.showNotification("You need to login first")
    }

  }

  isLoggedIn() {
    return this.auth.getCurrentUser();
  }

  onSubmit(){
    if(this.formEditComment.valid){
      this.comment.text = this.formEditComment.value.text
      this.commentService.updateComment(this.comment).subscribe(() =>
      {
        this.editCommentShow()
      })
    }
    

  }

  deleteComment() {
    if(this.isLoggedIn() == null){
      this.notifierService.showNotification("You need to login first")
      return
    }

    if(this.isLoggedIn().username == this.comment.user.username){
      this.commentService.deleteComment(this.comment.id).subscribe(data => {
        this.clickedEventEmitDelete.emit(this.comment);
      });
    }else{
      this.notifierService.showNotification("You can delete only your comment")
    }
    
  }

  isMyComment(){
    if(this.auth.isLoggedIn()){
    if(this.auth.getCurrentUser().username == this.comment.user.username)
      return true;
    else false;
    }else{
      return false
    }
  }

  loadKarma(){
    this.reactionService.getKarmaForComment(this.comment.id).subscribe(Data => {
      this.karma = Data
    })
  }

  editCommentShow(){
    this.showComment = !this.showComment;
    this.editComment = !this.editComment;
  }


}
;