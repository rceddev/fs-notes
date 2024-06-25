import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { UserLogin, UserService } from '../services/user.service';

export const userLoggedOnGuard: CanActivateFn = (route, state) => {
  const router: Router = inject(Router);
  return sessionStorage.getItem('loggedOn') == null ? true : router.navigate(['home']);
};
