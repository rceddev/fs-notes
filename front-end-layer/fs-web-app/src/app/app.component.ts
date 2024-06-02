import { Component } from '@angular/core';
import { RouterOutlet, RouterLink } from '@angular/router';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import { HeaderComponent } from "./components/header/header.component";

@Component({
    selector: 'app-root',
    standalone: true,
    templateUrl: './app.component.html',
    styleUrl: './app.component.css',
    imports: [
        RouterOutlet,
        MatSlideToggleModule,
        RouterLink,
        HeaderComponent
    ]
})
export class AppComponent {
  title = 'fs-web-app';
}
