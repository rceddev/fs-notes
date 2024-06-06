import { Component } from '@angular/core';
import { RouterOutlet, RouterLink } from '@angular/router';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatSidenavModule} from '@angular/material/sidenav';
import { HeaderComponent } from "./components/header/header.component";
import { OptionsComponent } from "./components/options/options.component";

@Component({
    selector: 'app-root',
    standalone: true,
    templateUrl: './app.component.html',
    styleUrl: './app.component.css',
    imports: [
        RouterOutlet,
        MatSlideToggleModule,
        RouterLink,
        HeaderComponent,
        MatSidenavModule,
        OptionsComponent
    ]
})
export class AppComponent {
  

  title = 'fs-web-app';
  sideNaveOpened: boolean = false;

  toogleSideNav() {
    console.log("also works!!" + this.sideNaveOpened)
    this.sideNaveOpened = !this.sideNaveOpened;
  }

  closingSidNav() {
    console.log("Closing sideNave")
    if (this.sideNaveOpened != false)
      this.sideNaveOpened = false; 
  }
}
