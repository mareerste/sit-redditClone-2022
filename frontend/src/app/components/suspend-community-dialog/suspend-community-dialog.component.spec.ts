import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SuspendCommunityDialogComponent } from './suspend-community-dialog.component';

describe('SuspendCommunityDialogComponent', () => {
  let component: SuspendCommunityDialogComponent;
  let fixture: ComponentFixture<SuspendCommunityDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SuspendCommunityDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SuspendCommunityDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
