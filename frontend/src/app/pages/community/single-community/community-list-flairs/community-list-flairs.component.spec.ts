import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CommunityListFlairsComponent } from './community-list-flairs.component';

describe('CommunityListFlairsComponent', () => {
  let component: CommunityListFlairsComponent;
  let fixture: ComponentFixture<CommunityListFlairsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CommunityListFlairsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CommunityListFlairsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
