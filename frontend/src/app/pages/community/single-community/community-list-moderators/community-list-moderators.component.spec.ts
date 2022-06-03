import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CommunityListModeratorsComponent } from './community-list-moderators.component';

describe('CommunityListModeratorsComponent', () => {
  let component: CommunityListModeratorsComponent;
  let fixture: ComponentFixture<CommunityListModeratorsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CommunityListModeratorsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CommunityListModeratorsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
