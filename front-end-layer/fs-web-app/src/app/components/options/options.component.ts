import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import {MatListModule} from '@angular/material/list';
import { MatIconModule }  from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';

@Component({
  selector: 'app-options',
  standalone: true,
  imports: [RouterLink, MatListModule, MatIconModule, MatButtonModule],
  templateUrl: './options.component.html',
  styleUrl: './options.component.css'
})
export class OptionsComponent {
  idRoutes : any = {
    'home': false,
    'panel': true,
    'user': false
  }

  lastActiveOption:string = 'panel'; 

  itemSelected(itemName: string) {
    this.idRoutes[itemName]=true;
    this.idRoutes[this.lastActiveOption]=false;
    this.lastActiveOption = itemName;
  }

}

