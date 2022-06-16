import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CommunityFlairItemComponent } from './community-flair-item.component';

describe('CommunityFlairItemComponent', () => {
  let component: CommunityFlairItemComponent;
  let fixture: ComponentFixture<CommunityFlairItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CommunityFlairItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CommunityFlairItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
