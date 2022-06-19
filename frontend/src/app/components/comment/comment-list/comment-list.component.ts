import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Comment } from 'src/app/model/comment';
import { ReactionType } from 'src/app/model/enumerations/reaction-type.enum';
import { Post } from 'src/app/model/post';
import { AuthService, PostService } from 'src/app/service';
import { CommentService } from 'src/app/service/comment.service';

@Component({
  selector: 'app-comment-list',
  templateUrl: './comment-list.component.html',
  styleUrls: ['./comment-list.component.css']
})
export class CommentListComponent implements OnInit {

  @Input()
  comments:any;
  loggedUser;
  @Input()
  parentPost:Post;
  @Input()
  parentComment:Comment;
  @Output()
  newCommentEmitter = new EventEmitter<Comment>();

  //form
  form:FormGroup;
  textRequired = false;
  constructor(
    private auth:AuthService,
    private formBuilder:FormBuilder,
    private commentService: CommentService,
    private postService:PostService,
  ) { }

  ngOnInit(): void {
    this.loggedUser = this.auth.getCurrentUser();
    console.log(this.comments)
    this.form = this.formBuilder.group({
      text: ['', Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(64)])],
    });
  }

  onSubmit(){
    this.textRequired = false;
    if(this.form.valid){
        var newComment:Comment = new Comment();
        newComment.text = this.form.value.commentText
        console.log(this.form.value)
        this.commentService.saveComment(this.form.value).subscribe(data=>{
          // this.newCommentEmitter.emit(data);
          // this.comments.push(data)
          console.log(data)
          this.form.reset()
          if(this.parentComment != null)
            // this.parentComment.childComments.push(data)
            this.updateComment(data)
          if(this.parentPost != null){
            // var comments:Comment[] = this.parentPost.comments
            // comments.push(data)
            // this.parentPost.comments = comments
            this.updatePost(data)
          }
        })
    }else{
      this.textRequired = true;
    }
  }

  getChange(type:ReactionType){
    console.log(type)
    if(type == ReactionType.UPVOTE){
      console.log("UPVOTE")
    }else if(type == ReactionType.DOWNVOTE){
      console.log("DOWNVOTE")
    }
  }

  getDelete(comment: Comment) {
    let index = this.comments.findIndex(c => c.id == comment.id);
    if (index !== -1) {
      this.comments.splice(index, 1);
    }
  }

  updatePost(comment:Comment){
    var comments:Comment[] = this.parentPost.comments
    comments.push(comment)
    this.parentPost.comments = comments
    this.postService.updatePost(this.parentPost).subscribe();
  }

  updateComment(comment:Comment){
    this.parentComment.childComments.push(comment)
    this.commentService.updateComment(this.parentComment).subscribe()
  }
}
