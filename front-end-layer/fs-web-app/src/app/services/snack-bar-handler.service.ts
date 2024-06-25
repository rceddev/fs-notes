import { Injectable } from '@angular/core';
import {MatSnackBar} from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class SnackBarHandlerService {

  constructor(private _snackBar: MatSnackBar) { }

  openSnackBarWithAction(message: string, action: string) {
    this._snackBar.open(message, action);
  }
  openSnackBar(message: string) {
    console.log("abriendo snackBar");
    this._snackBar.open(message, undefined,{
      duration: 3000
    });
  }
}
