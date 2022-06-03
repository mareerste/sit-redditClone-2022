import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SingleCommunityComponent } from './single-community.component';

describe('SingleCommunityComponent', () => {
  let component: SingleCommunityComponent;
  let fixture: ComponentFixture<SingleCommunityComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SingleCommunityComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SingleCommunityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
