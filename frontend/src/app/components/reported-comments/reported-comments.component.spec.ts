import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportedCommentsComponent } from './reported-comments.component';

describe('ReportedCommentsComponent', () => {
  let component: ReportedCommentsComponent;
  let fixture: ComponentFixture<ReportedCommentsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReportedCommentsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReportedCommentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
