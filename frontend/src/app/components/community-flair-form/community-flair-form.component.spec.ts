import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CommunityFlairFormComponent } from './community-flair-form.component';

describe('CommunityFlairFormComponent', () => {
  let component: CommunityFlairFormComponent;
  let fixture: ComponentFixture<CommunityFlairFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CommunityFlairFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CommunityFlairFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
