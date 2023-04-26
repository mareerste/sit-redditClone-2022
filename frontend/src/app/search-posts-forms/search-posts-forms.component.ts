import { PostService } from './../service/post.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { PostES } from '../model/PostES';

@Component({
  selector: 'app-search-posts-forms',
  templateUrl: './search-posts-forms.component.html',
  styleUrls: ['./search-posts-forms.component.css']
})
export class SearchPostsFormsComponent implements OnInit {

  searchType: string;
  searching = false;
  searchResult: PostES[];

  searchByTitleForm: FormGroup;
  postTitleRequired = false;

  searchByTextForm: FormGroup;
  postTextRequired = false;

  searchByFlairForm: FormGroup;
  postFlairRequired = false;

  searchByKarmaRangeForm: FormGroup;
  searchByCommentRangeForm: FormGroup;

  combinedSearchForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private postService: PostService
  ) { }

  ngOnInit() {
    this.searchByTitleForm = this.formBuilder.group({
      postTitle: ["", Validators.compose([Validators.required, Validators.minLength(2), Validators.maxLength(64)])],
    });
    this.searchByTextForm = this.formBuilder.group({
      postText: ["", Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(64)])],
    });
    this.searchByFlairForm = this.formBuilder.group({
      postFlair: ["", Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(64)])],
    });
    this.searchByKarmaRangeForm = this.formBuilder.group({
      minKarma: [""],
      maxKarma: [""]
    });
    this.searchByCommentRangeForm = this.formBuilder.group({
      minComment: [""],
      maxComment: [""]
    });
    this.combinedSearchForm = this.formBuilder.group({
      postTitle: ["", Validators.compose([Validators.required, Validators.minLength(2), Validators.maxLength(64)])],
      postText: ["", Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(64)])],
      postFlair: ["", Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(64)])],
      minKarma: [""],
      maxKarma: [""],
      minComment: [""],
      maxComment: [""],
      searchType: ["phrase", Validators.required]
    });
  }

  titleSearchSubmit() {
    this.postTitleRequired = false;
    if (this.searchByTitleForm.valid) {
      console.log(this.searchByTitleForm.value)
      this.searching = true;
      this.postService.getPostsByTitle(this.searchByTitleForm.value.postTitle).subscribe(data => {
        this.searchResult = data;
      })
    }
  }

  textSearchSubmit() {
    this.postTextRequired = false;
    if (this.searchByTextForm.valid) {
      console.log(this.searchByTextForm.value)
      this.searching = true;
      this.postService.getPostsByText(this.searchByTextForm.value.postText).subscribe(data => {
        this.searchResult = data;
      })
    }
  }

  flairSearchSubmit(){
    this.postFlairRequired = false;
    if (this.searchByFlairForm.valid) {
      console.log(this.searchByFlairForm.value)
      this.searching = true;
      this.postService.getPostsByFlair(this.searchByFlairForm.value.postFlair).subscribe(data => {
        this.searchResult = data;
      })
    }
  }

  karmaRangeSearchSubmit() {
    if (this.searchByKarmaRangeForm.value.minKarma == '' || this.searchByKarmaRangeForm.value.minKarma == null)
      this.searchByKarmaRangeForm.value.minKarma = 0
    if (this.searchByKarmaRangeForm.value.maxKarma == '' || this.searchByKarmaRangeForm.value.maxKarma == null)
      this.searchByKarmaRangeForm.value.maxKarma = 10000000

    this.searching = true;
    this.postService.getPostsByKarma(this.searchByKarmaRangeForm.value.minKarma,this.searchByKarmaRangeForm.value.maxKarma).subscribe(data => {
      this.searchResult = data;
    })
  }

  commentRangeSearchSubmit() {
    if (this.searchByCommentRangeForm.value.minComment == '' || this.searchByCommentRangeForm.value.minComment == null)
      this.searchByCommentRangeForm.value.minComment = 0
    if (this.searchByCommentRangeForm.value.maxComment == '' || this.searchByCommentRangeForm.value.maxComment == null)
      this.searchByCommentRangeForm.value.maxComment = 10000000

    this.searching = true;
    this.postService.getPostsByComments(this.searchByCommentRangeForm.value.minComment,this.searchByCommentRangeForm.value.maxComment).subscribe(data => {
      this.searchResult = data;
    })
  }

  combinedSearchSubmit() {
    if (this.combinedSearchForm.value.minKarma == '' || this.combinedSearchForm.value.minKarma == null)
      this.combinedSearchForm.value.minKarma = 0
    if (this.combinedSearchForm.value.maxKarma == '' || this.combinedSearchForm.value.maxKarma == null)
      this.combinedSearchForm.value.maxKarma = 10000000
    if (this.combinedSearchForm.value.minComment == '' || this.combinedSearchForm.value.minComment == null)
      this.combinedSearchForm.value.minComment = 0
    if (this.combinedSearchForm.value.maxComment == '' || this.combinedSearchForm.value.maxComment == null)
      this.combinedSearchForm.value.maxComment = 10000000
    console.log(this.combinedSearchForm.value)
    if (this.combinedSearchForm.valid) {
      console.log(this.combinedSearchForm.value)
      this.searching = true;
      var searchType = this.combinedSearchForm.value.searchType;
      var title = this.combinedSearchForm.value.postTitle;
      var text = this.combinedSearchForm.value.postText;
      var flair = this.combinedSearchForm.value.postFlair;
      var minKarma = this.combinedSearchForm.value.minKarma;
      var maxKarma = this.combinedSearchForm.value.maxKarma;
      var minComment = this.combinedSearchForm.value.minComment;
      var maxComment = this.combinedSearchForm.value.maxComment;
      this.postService.getPostsByCombinedSearch(searchType,title, text, flair, minKarma, maxKarma, minComment, maxComment).subscribe(data => {
        console.log(data)
        this.searchResult = data;
      })
    }
  }

  stopSearching() {
    this.searching = false;
  }

  searchTypeChanged(e) {
    this.searchType = e.value
  }

  showSearchForm() {
    var show = document.getElementById("showButtonPosts");
    var hide = document.getElementById("hideButtonPosts");
    var form = document.getElementById("formDivPosts");
    console.log("function called");
    if (show.style.display === "none") {
      console.log("none")
      show.style.display = "block";
      form.style.display = "none";
      hide.style.display = "none";
    } else {
      console.log("block")
      hide.style.display = "block";
      form.style.display = "block";
      show.style.display = "none";
    }
  }

}
