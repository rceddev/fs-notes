import { Component } from '@angular/core';
import { NoteComponent } from "../note/note.component";

@Component({
    selector: 'app-panel',
    standalone: true,
    templateUrl: './panel.component.html',
    styleUrl: './panel.component.css',
    imports: [NoteComponent]
})
export class PanelComponent {

}
