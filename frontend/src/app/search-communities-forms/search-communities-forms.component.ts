import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CommunityES } from '../model/communityES';
import { CommunityService } from '../service/community.service';

@Component({
  selector: 'app-search-communities-forms',
  templateUrl: './search-communities-forms.component.html',
  styleUrls: ['./search-communities-forms.component.css']
})
export class SearchCommunitiesFormsComponent implements OnInit {

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
    private formBuilder: FormBuilder,
    private communityService: CommunityService
  ) { }

  ngOnInit() {
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
