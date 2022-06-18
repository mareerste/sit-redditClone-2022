import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CommunityRuleListComponent } from './community-rule-list.component';

describe('CommunityRuleListComponent', () => {
  let component: CommunityRuleListComponent;
  let fixture: ComponentFixture<CommunityRuleListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CommunityRuleListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CommunityRuleListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
