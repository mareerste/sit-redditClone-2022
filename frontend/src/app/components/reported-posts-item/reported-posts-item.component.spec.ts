import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportedPostsItemComponent } from './reported-posts-item.component';

describe('ReportedPostsItemComponent', () => {
  let component: ReportedPostsItemComponent;
  let fixture: ComponentFixture<ReportedPostsItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReportedPostsItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReportedPostsItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
