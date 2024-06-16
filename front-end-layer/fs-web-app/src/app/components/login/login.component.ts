import { Component } from '@angular/core';
import {
  MatDialog,
  MAT_DIALOG_DATA,
  MatDialogRef,
  MatDialogTitle,
  MatDialogContent,
  MatDialogActions,
  MatDialogClose,
} from '@angular/material/dialog';
import {FormControl, FormGroup, FormsModule, Validators} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatButtonModule} from '@angular/material/button';
import { ReactiveFormsModule } from '@angular/forms';
import {MatCardModule} from '@angular/material/card';
import {MatDividerModule} from '@angular/material/divider';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatButtonModule,
    MatDialogTitle,
    MatDialogContent,
    MatDialogActions,
    MatDialogClose,
    ReactiveFormsModule,
    MatCardModule,
    MatDividerModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginFormGroup = new FormGroup({
    emailFormControl : new FormControl('', [Validators.required, Validators.email]),
    passWordFormControl : new FormControl('', [Validators.required])
  });

  handleSubmit() {
    console.log("Email:" + this.loginFormGroup.value.emailFormControl + ", password:" + this.loginFormGroup.value.passWordFormControl)
  }

  get emailFormControl(){
    return this.loginFormGroup.get('emailFormControl');
  }

  get passWordFormControl(){
    return this.loginFormGroup.get('passWordFormControl');
  }

}
