import { CommunityES } from './../../model/communityES';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from './../../service/user.service';
import { ConfigService } from './../../service/config.service';
import { Post } from './../../model/post';
import { ActivatedRoute } from '@angular/router';
import { PostService } from './../../service/post.service';
import { BehaviorSubject, Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service';
import { switchMap } from 'rxjs/operators';
import { CommunityService } from 'src/app/service/community.service';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

  whoamIResponse = {};
  posts: Post[];
  refreshPosts = new BehaviorSubject<boolean>(true);

  // es search
  searchType: string;
  searching = false;
  searchResult: CommunityES[];

  searchByNameForm: FormGroup;
  communityNameRequired = false;

  searchByDescriptionForm: FormGroup;
  communityDescriptionRequired = false;

  searchByRuleForm: FormGroup;
  communityRuleRequired = false;

  searchByPostRangeForm: FormGroup;
  searchByKarmaRangeForm: FormGroup;

  combinedSearchForm: FormGroup;

  constructor(
    private postService: PostService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private config: ConfigService,
    private userService: UserService,
    private auth: AuthService,
    private communityService: CommunityService) { }

  ngOnInit(): void {
    this.postService.getPosts().subscribe(data => {
      this.posts = data
    })
    this.searchByNameForm = this.formBuilder.group({
      communityName: ["", Validators.compose([Validators.required, Validators.minLength(2), Validators.maxLength(64)])],
    });
    this.searchByDescriptionForm = this.formBuilder.group({
      communityDescription: ["", Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(64)])],
    });
    this.searchByRuleForm = this.formBuilder.group({
      communityRule: ["", Validators.compose([Validators.required, Validators.minLength(2), Validators.maxLength(64)])],
    });
    this.searchByPostRangeForm = this.formBuilder.group({
      minPost: [""],
      maxPost: [""]
    });
    this.searchByKarmaRangeForm = this.formBuilder.group({
      minKarma: [""],
      maxKarma: [""]
    });
    this.combinedSearchForm = this.formBuilder.group({
      communityName: ["", Validators.compose([Validators.required, Validators.minLength(2), Validators.maxLength(64)])],
      communityDescription: ["", Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(64)])],
      communityRule: ["", Validators.compose([Validators.required, Validators.minLength(2), Validators.maxLength(64)])],
      minPost: [""],
      maxPost: [""],
      minKarma: [""],
      maxKarma: [""],
      searchType: ["phrase", Validators.required]
    });
  }
  getChange() {
  }

  nameSearchSubmit() {
    this.communityNameRequired = false;
    if (this.searchByNameForm.valid) {
      console.log(this.searchByNameForm.value)
      this.searching = true;
      this.communityService.getCommunityByName(this.searchByNameForm.value.communityName).subscribe(data => {
        this.searchResult = data;
      })
    }
  }

  descriptionSearchSubmit() {
    this.communityDescriptionRequired = false;
    if (this.searchByDescriptionForm.valid) {
      console.log(this.searchByDescriptionForm.value)
      this.searching = true;
      this.communityService.getCommunityByDescription(this.searchByDescriptionForm.value.communityDescription).subscribe(data => {
        this.searchResult = data;
      })
    }
  }

  ruleSearchSubmit() {
    this.communityRuleRequired = false;
    if (this.searchByRuleForm.valid) {
      console.log(this.searchByRuleForm.value)
      this.searching = true;
      this.communityService.getCommunityByRule(this.searchByRuleForm.value.communityRule).subscribe(data => {
        this.searchResult = data;
      })
    }
  }

  postRangeSearchSubmit() {
    if (this.searchByPostRangeForm.value.minPost == '' || this.searchByKarmaRangeForm.value.minPost == null)
      this.searchByPostRangeForm.value.minPost = 0
    if (this.searchByPostRangeForm.value.maxPost == '' || this.searchByKarmaRangeForm.value.maxPost == null)
      this.searchByPostRangeForm.value.maxPost = 10000000

    this.searching = true;
    this.communityService.getCommunityByPostRange(this.searchByPostRangeForm.value.minPost, this.searchByPostRangeForm.value.maxPost).subscribe(data => {
      this.searchResult = data;
    })
  }

  karmaRangeSearchSubmit() {
    if (this.searchByKarmaRangeForm.value.minKarma == '' || this.searchByKarmaRangeForm.value.minKarma == null)
      this.searchByKarmaRangeForm.value.minKarma = 0
    if (this.searchByKarmaRangeForm.value.maxKarma == '' || this.searchByKarmaRangeForm.value.maxKarma == null)
      this.searchByKarmaRangeForm.value.maxKarma = 10000000

    this.searching = true;
    this.communityService.getCommunityByKarma(this.searchByKarmaRangeForm.value.minKarma, this.searchByKarmaRangeForm.value.maxKarma).subscribe(data => {
      this.searchResult = data;
    })
  }

  combinedSearchSubmit() {
    if (this.combinedSearchForm.value.minPost == '' || this.combinedSearchForm.value.minPost == null)
      this.combinedSearchForm.value.minPost = 0
    if (this.combinedSearchForm.value.maxPost == '' || this.combinedSearchForm.value.maxPost == null)
      this.combinedSearchForm.value.maxPost = 10000000
    if (this.combinedSearchForm.value.minKarma == '' || this.combinedSearchForm.value.minKarma == null)
      this.combinedSearchForm.value.minKarma = 0
    if (this.combinedSearchForm.value.maxKarma == '' || this.combinedSearchForm.value.maxKarma == null)
      this.combinedSearchForm.value.maxKarma = 10000000
    console.log(this.combinedSearchForm.value)
    if (this.combinedSearchForm.valid) {
      console.log(this.combinedSearchForm.value)
      this.searching = true;
      var searchType = this.combinedSearchForm.value.searchType;
      var name = this.combinedSearchForm.value.communityName;
      var description = this.combinedSearchForm.value.communityDescription;
      var rule = this.combinedSearchForm.value.communityRule;
      var minPost = this.combinedSearchForm.value.minPost;
      var maxPost = this.combinedSearchForm.value.maxPost;
      var minKarma = this.combinedSearchForm.value.minKarma;
      var maxKarma = this.combinedSearchForm.value.maxKarma;
      this.communityService.getCommunityByCombinedSearch(searchType, name, description, rule, minPost, maxPost, minKarma, maxKarma).subscribe(data => {
        this.searchResult = data;
      })
    }
  }

  stopSearching() {
    this.searching = false;
  }

  makeRequest(path) {
    if (this.config.whoami_url.endsWith(path)) {
      this.userService.getMyInfo()
        .subscribe(res => {
          this.forgeResonseObj(this.whoamIResponse, res, path);
        }, err => {
          this.forgeResonseObj(this.whoamIResponse, err, path);
        });
    } else {
      // this.userService.getAll()
      //   .subscribe(res => {
      //     this.forgeResonseObj(this.allUserResponse, res, path);
      //   }, err => {
      //     this.forgeResonseObj(this.allUserResponse, err, path);
      //   });
    }
  }

  forgeResonseObj(obj, res, path) {
    obj['path'] = path;
    obj['method'] = 'GET';
    if (res.ok === false) {
      // err
      obj['status'] = res.status;
      try {
        obj['body'] = JSON.stringify(JSON.parse(res._body), null, 2);
      } catch (err) {
        console.log(res);
        obj['body'] = res.error.message;
      }
    } else {
      // 200
      obj['status'] = 200;
      obj['body'] = JSON.stringify(res, null, 2);
    }
  }

  searchTypeChanged(e) {
    this.searchType = e.value
  }

  showSearchForm() {
    var show = document.getElementById("showButton");
    var hide = document.getElementById("hideButton");
    var form = document.getElementById("formDiv");
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
