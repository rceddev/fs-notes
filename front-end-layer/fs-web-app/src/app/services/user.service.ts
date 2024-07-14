import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, map, Observable, tap, throwError } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { SnackBarHandlerService } from './snack-bar-handler.service';

export interface UserToken{
  token:string
}

export interface UserRegistered{
  user: {firstName: string,lastName: string, id:number,email: string },
  token: string
}

export interface UserLogin{
  password: string,
  email: string
}

export interface UserRegister{
  firstName: string,
  lastName: string,
  password: string,
  email: string
}

const WRONG_PASS_CODE = "401_WP";
const EMAIL_NOT_FOUND_CODE = "401_ENF";
const FS_BAD_REQUEST = "4XX_BR";
const USER_ALREADY_EXISTS = "409_UE"

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
        return throwError(()=> new Error('Something wrong happend trying to log in user'));
      })
    );
  }

  register(body: UserRegister): Observable<UserRegistered>{
    return this.http.post<UserRegistered>(environment.urlHost + environment.pathRegister, body).pipe(
      tap( (response) => {
        this.isUserLogedIn.next(true);
        sessionStorage.setItem('token', response.token);
        sessionStorage.setItem('loggedOn', 'true');
        sessionStorage.setItem('firstName', response.user.firstName);
        sessionStorage.setItem('lastName', response.user.lastName);
        sessionStorage.setItem('email', response.user.email);
        sessionStorage.setItem('id', String(response.user.id));
        //TODO: Check if id its necessary, if not, remove for security porpuse
      }),

      catchError((errorResponse)=> {
        this.errorHandler(errorResponse);
        return throwError(()=> new Error('Something wrong happend trying to register user'));
      })
    )
  }

  logout():void{
    this.isUserLogedIn.next(false);
    sessionStorage.clear();
  }

  private errorHandler(errorResponse: HttpErrorResponse){



    if (errorResponse.status >= 400 && errorResponse.status <= 499) {
      if (errorResponse.error.code) {
        switch (errorResponse.error.code) {
          case WRONG_PASS_CODE:
            this.snackBarService.openSnackBar(String(errorResponse.error.message));
            break;
          case EMAIL_NOT_FOUND_CODE:
            this.snackBarService.openSnackBar(String(errorResponse.error.message));
            break;
          case FS_BAD_REQUEST:
            this.snackBarService.openSnackBar(String(errorResponse.error.message));
            break;
          case USER_ALREADY_EXISTS:
            this.snackBarService.openSnackBar(String(errorResponse.error.message));
            break;
          default:
            this.snackBarService.openSnackBar(String("Somthing goes wrong"));
            break;
        }
      }else{
        //TODO: Error no throw by microservices
      }
    }
    if (errorResponse.status >= 500 && errorResponse.status <= 599) {
      
    }
    
    
  }

}
