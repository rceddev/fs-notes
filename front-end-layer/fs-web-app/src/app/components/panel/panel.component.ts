import { Component } from '@angular/core';
import { NoteComponent } from "../note/note.component";
import {MatIconModule} from '@angular/material/icon';
import {MatDividerModule} from '@angular/material/divider';
import {MatButtonModule} from '@angular/material/button';
import { NoteService } from '../../services/note-service.service';
import { MatDialog } from '@angular/material/dialog';
import { CreateComponent } from '../note/dialogs/create/create.component';
import {MatSnackBar} from '@angular/material/snack-bar';


@Component({
    selector: 'app-panel',
    standalone: true,
    templateUrl: './panel.component.html',
    styleUrl: './panel.component.css',
    imports: [NoteComponent, MatIconModule, MatButtonModule]
})
export class PanelComponent {

    constructor(
        private noteService: NoteService, 
        public dialog: MatDialog,
        private _snackBar: MatSnackBar) { }
    
    notes : {id:number, title: string, content:string}[] = this.noteService.getNotes();
    
    createNote() {
        const dialogCreateRef = this.dialog.open(CreateComponent);

        dialogCreateRef.afterClosed().subscribe(result => {
            if (result) {
                this.noteService.createNote(5, result.title, result.content);
                this.openSnackBar("Noted created succesfully");
            }else{

            }
        });
    }

    openSnackBar(message:string){
        this._snackBar.open(message, undefined, {
            duration: 3000
        });
    }
}
