import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CommunityUpdatePostComponent } from './community-update-post.component';

describe('CommunityUpdatePostComponent', () => {
  let component: CommunityUpdatePostComponent;
  let fixture: ComponentFixture<CommunityUpdatePostComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CommunityUpdatePostComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CommunityUpdatePostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
