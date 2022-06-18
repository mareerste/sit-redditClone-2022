import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportedCommentsDetailsComponent } from './reported-comments-details.component';

describe('ReportedCommentsDetailsComponent', () => {
  let component: ReportedCommentsDetailsComponent;
  let fixture: ComponentFixture<ReportedCommentsDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReportedCommentsDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReportedCommentsDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
