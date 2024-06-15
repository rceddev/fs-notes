import { Component, Inject } from '@angular/core';
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

export interface NewNote{
  id:number, title: string, content:string
}

@Component({
  selector: 'app-create',
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
    ReactiveFormsModule
  ],
  templateUrl: './create.component.html',
  styleUrl: './create.component.css'
})
export class CreateComponent {

  constructor(
    public dialogRef: MatDialogRef<CreateComponent>
  ) {}
  
  data : NewNote = { id: -1, title:'', content:''};
  noteFormGroup = new FormGroup({
    titleControlForm : new FormControl('', [Validators.required]),
    contentControlForm : new FormControl('', [Validators.required])
  });
  
  onCancelClicked() {
    this.dialogRef.close();
  }

  get titleControlForm(){
    return this.noteFormGroup.get('titleControlForm');
  }

  get contentControlForm(){
    return this.noteFormGroup.get('contentControlForm');
  }

}
