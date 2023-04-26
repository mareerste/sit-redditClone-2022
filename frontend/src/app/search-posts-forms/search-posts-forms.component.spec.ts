import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchPostsFormsComponent } from './search-posts-forms.component';

describe('SearchPostsFormsComponent', () => {
  let component: SearchPostsFormsComponent;
  let fixture: ComponentFixture<SearchPostsFormsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchPostsFormsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchPostsFormsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
