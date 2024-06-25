import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, map, Observable, tap, throwError } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { SnackBarHandlerService } from './snack-bar-handler.service';

export interface UserToken{
  token:string
}

export interface UserLogin{
  password: string,
  email: string
}

const WRONG_PASS_CODE = "401_WP";
const EMAIL_NOT_FOUND_CODE = "401_ENF";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  
  isUserLogedIn: BehaviorSubject<boolean>;

  constructor(
    private http: HttpClient,
    private snackBarService: SnackBarHandlerService) { 
    this.isUserLogedIn = new BehaviorSubject<boolean>(false);
  }

  
  login(body: UserLogin): Observable<UserToken>{
    return this.http.post<UserToken>(environment.urlHost + environment.pathAuthLogin, body).pipe(
      
      tap( (response) => { 
        //TODO: Valdiate how to put token on HttpOnly cookie
        this.isUserLogedIn.next(true);
        sessionStorage.setItem('token', response.token);
        sessionStorage.setItem('loggedOn', 'true');
      }),
      
      catchError((errorResponse)=> {
        this.errorHandler(errorResponse);
        return throwError(()=> new Error('Algo falló. Por favor intente nuevamente.'));
      })
    );
  }

  private errorHandler(errorResponse: HttpErrorResponse){

    if (errorResponse.status == 401) {
      switch (errorResponse.error.code) {
        case WRONG_PASS_CODE:
          console.log(errorResponse.error.message);
          this.snackBarService.openSnackBar("chingatu madre");
          break;
        case EMAIL_NOT_FOUND_CODE:
          this.snackBarService.openSnackBar(String(errorResponse.error.message));
          break;
        default:
          break;
      }
    }
    
    
  }

}
