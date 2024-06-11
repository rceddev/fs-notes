import { Component, Input } from '@angular/core';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatButtonModule} from '@angular/material/button';
import {MatDividerModule} from '@angular/material/divider';
import {MatCardModule} from '@angular/material/card';
import {MatIconModule} from '@angular/material/icon';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DeleteComponent } from './dialogs/delete/delete.component';
import { UpdateComponent } from './dialogs/update/update.component';
import {MatSnackBar} from '@angular/material/snack-bar';

const Colors: string[] = [
  '#FFCDD2', //RED
  '#F8BBD0', //PINK
  '#F8BBD0', //PURPLE
  '#D1C4E9', //DEEP_PURPLE
  '#D7CCC8', //BROWN
  '#FFCCBC', //DEEP_ORANGE
  '#B2EBF2', //CYAN
  '#B3E5FC'  //LIGTH_BLUE
]


@Component({
  selector: 'app-note',
  standalone: true,
  imports: [
    MatCardModule,
    MatDividerModule, 
    MatButtonModule, 
    MatProgressBarModule, 
    MatIconModule,
  ],
  templateUrl: './note.component.html',
  styleUrl: './note.component.css'
})
export class NoteComponent {
  
  constructor(public dialog: MatDialog, private _snackBar: MatSnackBar){}
  
  @Input() title: string = "Title";
  @Input() content: string = "he Shiba Inu is the smallest of the six original and distinct spitz breeds of dog from Japan. A small, agile dog that copes very well with mountainous terrain, the Shiba Inu was originally bred for hunting.";
  isHidden: boolean = true;
  noteColor: string = this.getRandomColor();
  active: boolean=true;

  getRandomColor() {
    const index= Math.floor(Math.random() * Colors.length);
    return Colors[index]; 
  }

  onDeleteOpenDialog() {
    const dialogDeleteRef = this.dialog.open(DeleteComponent);

    dialogDeleteRef.afterClosed().subscribe(result => {
      if (result) {
        //TODO: Add http request to delete note
        this.active = false;
        this.openSnackBar("Noted deleted succesfully");
      }else{

      }
    });
  }

  onUpdateOpenDialog(): void {
    const dialogUpdateRef = this.dialog.open(UpdateComponent, {
      data: {title: this.title, content: this.content},
    });

    dialogUpdateRef.afterClosed().subscribe(result => {
      if (result != undefined) {
        //TODO: Add http request to Update note
        this.title = result.title;
        this.content = result.content;
        this.isHidden = false;
        this.openSnackBar("Noted updated succesfully");
      }
    });
  }

  openSnackBar(message:string){
    this._snackBar.open(message, undefined, {
      duration: 3000
    });
  }
}
