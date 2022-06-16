import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MembersBanItemComponent } from './members-ban-item.component';

describe('MembersBanItemComponent', () => {
  let component: MembersBanItemComponent;
  let fixture: ComponentFixture<MembersBanItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MembersBanItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MembersBanItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
