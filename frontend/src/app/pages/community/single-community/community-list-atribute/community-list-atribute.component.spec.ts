import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CommunityListAtributeComponent } from './community-list-atribute.component';

describe('CommunityListAtributeComponent', () => {
  let component: CommunityListAtributeComponent;
  let fixture: ComponentFixture<CommunityListAtributeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CommunityListAtributeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CommunityListAtributeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
