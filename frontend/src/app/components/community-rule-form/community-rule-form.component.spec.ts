import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CommunityRuleFormComponent } from './community-rule-form.component';

describe('CommunityRuleFormComponent', () => {
  let component: CommunityRuleFormComponent;
  let fixture: ComponentFixture<CommunityRuleFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CommunityRuleFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CommunityRuleFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
