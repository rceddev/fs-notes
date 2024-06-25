import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { userLoggedOnGuard } from './user-logged-on.guard';

describe('userLoggedOnGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => userLoggedOnGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
