import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportedPostsDetailsComponent } from './reported-posts-details.component';

describe('ReportedPostsDetailsComponent', () => {
  let component: ReportedPostsDetailsComponent;
  let fixture: ComponentFixture<ReportedPostsDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReportedPostsDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReportedPostsDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
