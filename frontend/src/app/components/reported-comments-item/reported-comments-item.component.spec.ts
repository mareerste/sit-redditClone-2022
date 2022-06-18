import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportedCommentsItemComponent } from './reported-comments-item.component';

describe('ReportedCommentsItemComponent', () => {
  let component: ReportedCommentsItemComponent;
  let fixture: ComponentFixture<ReportedCommentsItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReportedCommentsItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReportedCommentsItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
