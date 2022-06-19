import { TestBed, async, inject } from '@angular/core/testing';

import { LoggedInAuthGuardGuard } from './logged-in-auth-guard.guard';

describe('LoggedInAuthGuardGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [LoggedInAuthGuardGuard]
    });
  });

  it('should ...', inject([LoggedInAuthGuardGuard], (guard: LoggedInAuthGuardGuard) => {
    expect(guard).toBeTruthy();
  }));
});
