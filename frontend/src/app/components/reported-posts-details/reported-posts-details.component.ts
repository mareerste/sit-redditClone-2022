import { Report } from './../../model/report';
import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ImageService } from 'src/app/service/image.service';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-reported-posts-details',
  templateUrl: './reported-posts-details.component.html',
  styleUrls: ['./reported-posts-details.component.css']
})
export class ReportedPostsDetailsComponent implements OnInit {

  @Input()
  report:Report
  image;
  imageUser;
  constructor(
    private router:Router,
    private imageService: ImageService,
    private sanitizer:DomSanitizer,
  ) { }

  ngOnInit() {
    if (this.report.post.imagePath != null) {

      this.imageService.getImage3(this.report.post.imagePath).subscribe(data => {
        var unsafeImageUrl = URL.createObjectURL(data);
        this.image = this.sanitizer.bypassSecurityTrustUrl(unsafeImageUrl);
      })
    }
    if (this.report.post.user.avatar != null) {

      this.imageService.getImage3(this.report.post.user.avatar).subscribe(data => {
        var unsafeImageUrl = URL.createObjectURL(data);
        this.imageUser = this.sanitizer.bypassSecurityTrustUrl(unsafeImageUrl);
      })
    }
  }

  showPost() {
    this.router.navigate(
      ['post', this.report.post.id]
    )
  }
}
