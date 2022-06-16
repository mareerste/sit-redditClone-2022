import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MembersBanListComponent } from './members-ban-list.component';

describe('MembersBanListComponent', () => {
  let component: MembersBanListComponent;
  let fixture: ComponentFixture<MembersBanListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MembersBanListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MembersBanListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
