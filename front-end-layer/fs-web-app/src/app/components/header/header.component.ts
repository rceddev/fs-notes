import { Component, Output, EventEmitter } from '@angular/core';
import { MatToolbarModule }  from '@angular/material/toolbar'; 
import { MatIconModule }  from '@angular/material/icon';
import {MatMenuModule} from '@angular/material/menu';
import {MatButtonModule} from '@angular/material/button';
import { NoteService } from '../../services/note-service.service';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CreateComponent } from '../note/dialogs/create/create.component';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { LogoutComponent } from './dialogs/logout/logout.component';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [ 
    MatToolbarModule, 
    MatIconModule, 
    MatMenuModule, 
    MatButtonModule 
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {

  constructor(
    private noteService: NoteService, 
    public dialog: MatDialog,
    private _snackBar: MatSnackBar,
    private router: Router,
    private userService: UserService) { }

  @Output() toggleOptionsEvent = new EventEmitter();

  isUserLoggedON = false;

  toggleOptions() {
    this.toggleOptionsEvent.emit();
  }
 
  addNote() {
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

  navigate(route: string) {
    this.router.navigateByUrl(route);
  }

  logout() {
    const dialogSignOut = this.dialog.open(LogoutComponent);

    dialogSignOut.afterClosed().subscribe(result=> {
      if (result) {
        this.userService.logout();
        this.navigate('/home')
      }
    });
  }

  ngOnInit(){
    this.userService.isUserLogedIn.subscribe(
      {
        next: (userLoggedON) => {
          this.isUserLoggedON = userLoggedON;
        }
      }
    );
    this.isUserLoggedON = sessionStorage.getItem('loggedOn') != null ? true : false;
  }
}
