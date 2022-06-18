import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CommunityRuleItemComponent } from './community-rule-item.component';

describe('CommunityRuleItemComponent', () => {
  let component: CommunityRuleItemComponent;
  let fixture: ComponentFixture<CommunityRuleItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CommunityRuleItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CommunityRuleItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
