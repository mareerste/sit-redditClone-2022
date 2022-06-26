import { TestBed, async, inject } from '@angular/core/testing';

import { SuspendedPostGuard } from './suspended-post.guard';

describe('SuspendedPostGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SuspendedPostGuard]
    });
  });

  it('should ...', inject([SuspendedPostGuard], (guard: SuspendedPostGuard) => {
    expect(guard).toBeTruthy();
  }));
});
