import { TestBed, async, inject } from '@angular/core/testing';

import { SuspendedCommunityGuardGuard } from './suspended-community-guard.guard';

describe('SuspendedCommunityGuardGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SuspendedCommunityGuardGuard]
    });
  });

  it('should ...', inject([SuspendedCommunityGuardGuard], (guard: SuspendedCommunityGuardGuard) => {
    expect(guard).toBeTruthy();
  }));
});
