import { Component, Output, EventEmitter } from '@angular/core';
import { MatToolbarModule }  from '@angular/material/toolbar'; 
import { MatIconModule }  from '@angular/material/icon';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [ MatToolbarModule, MatIconModule ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  @Output() toggleOptionsEvent = new EventEmitter();

  toggleOptions() {
    console.log("works!!");
    this.toggleOptionsEvent.emit();
  }

}
