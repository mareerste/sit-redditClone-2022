import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchCommunitiesFormsComponent } from './search-communities-forms.component';

describe('SearchCommunitiesFormsComponent', () => {
  let component: SearchCommunitiesFormsComponent;
  let fixture: ComponentFixture<SearchCommunitiesFormsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchCommunitiesFormsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchCommunitiesFormsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
