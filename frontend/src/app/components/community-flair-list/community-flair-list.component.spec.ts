import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CommunityFlairListComponent } from './community-flair-list.component';

describe('CommunityFlairListComponent', () => {
  let component: CommunityFlairListComponent;
  let fixture: ComponentFixture<CommunityFlairListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CommunityFlairListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CommunityFlairListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
