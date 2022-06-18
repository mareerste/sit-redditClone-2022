import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportedCommentsListComponent } from './reported-comments-list.component';

describe('ReportedCommentsListComponent', () => {
  let component: ReportedCommentsListComponent;
  let fixture: ComponentFixture<ReportedCommentsListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReportedCommentsListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReportedCommentsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
