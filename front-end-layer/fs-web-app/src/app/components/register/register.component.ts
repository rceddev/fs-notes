import { Component } from '@angular/core';
import {
  MatDialogTitle,
  MatDialogContent,
  MatDialogActions,
  MatDialogClose,
} from '@angular/material/dialog';
import {FormControl, FormGroup, FormsModule, ValidatorFn, Validators} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatButtonModule} from '@angular/material/button';
import { ReactiveFormsModule } from '@angular/forms';
import {MatCardModule} from '@angular/material/card';
import {MatDividerModule} from '@angular/material/divider';
import {
  AbstractControl,
  NG_VALIDATORS,
  ValidationErrors,
  Validator,
} from '@angular/forms';
import { UserRegister, UserService } from '../../services/user.service';
import { Router } from '@angular/router';


export const passMatch: ValidatorFn = (control: AbstractControl): ValidationErrors | null => {
  const pass = control.get('passWordFormControl');
  const repass = control.get('repeatPassWordFormControl');
  console.log('values '+pass?.value + '  values1 '+repass?.value + 'resultado='+ (pass?.value != repass?.value))
  return pass!=null && repass!=null && pass.value != repass.value ? {passNotMatched: true} : null;
};
@Component({
  selector: 'app-register',
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
    MatDividerModule,
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  constructor(private userService: UserService, private router: Router ){}

  registerFormGroup = new FormGroup({
    emailFormControl : new FormControl('', [Validators.required, Validators.email]),
    passWordFormControl : new FormControl('', [Validators.required]),
    repeatPassWordFormControl : new FormControl('', [Validators.required]),
    nameFormControl : new FormControl('', [Validators.required]),
    lastNameFormControl : new FormControl('', [Validators.required]),
  }, {validators: passMatch});

  handleSubmit() {
    console.log("Email:" + this.registerFormGroup.value.emailFormControl + ", password:" + this.registerFormGroup.value.passWordFormControl)

    let registerBody : UserRegister = {
      firstName: this.registerFormGroup.value.nameFormControl!,
      lastName: this.registerFormGroup.value.lastNameFormControl!,
      password: this.registerFormGroup.value.passWordFormControl!,
      email: this.registerFormGroup.value.emailFormControl!
    }

    this.userService.register(registerBody).subscribe({
      next: (res) => {
        console.log(res);
      },
      complete: () => {
        this.router.navigate(['']);
      }
    });

  }

  get emailFormControl(){
    return this.registerFormGroup.get('emailFormControl');
  }

  get passWordFormControl(){
    return this.registerFormGroup.get('passWordFormControl');
  }

  get repeatPassWordFormControl(){
    return this.registerFormGroup.get('repeatPassWordFormControl');
  }

  get nameFormControl(){
    return this.registerFormGroup.get('nameFormControl');
  }
  get lastNameFormControl(){
    return this.registerFormGroup.get('lastNameFormControl');
  }
}
