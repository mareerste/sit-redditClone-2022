import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchCommunitiesComponent } from './search-communities.component';

describe('SearchCommunitiesComponent', () => {
  let component: SearchCommunitiesComponent;
  let fixture: ComponentFixture<SearchCommunitiesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchCommunitiesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchCommunitiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
