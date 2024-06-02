import { Routes } from '@angular/router';
import { HeaderComponent } from './components/header/header.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { NoteComponent } from './components/note/note.component';
import { OptionsComponent } from './components/options/options.component';
import { PanelComponent } from './components/panel/panel.component';
import { RegisterComponent } from './components/register/register.component';
import { UserComponent } from './components/user/user.component';

export const routes: Routes = [
    {
        path: 'home',
        title: 'Home Page',
        component: HomeComponent
    },
    {
        path: 'header',
        title: 'header',
        component: HeaderComponent
    },
    {
        path: 'login',
        title: 'login',
        component: LoginComponent    
    },
    {
        path: 'options',
        title: 'Options Page',
        component: OptionsComponent
    },
    {
        path: 'panel',
        title: 'Panel Page',
        component: PanelComponent
    },
    {
        path: 'register',
        title: 'Register Page',
        component: RegisterComponent
    },
    {
        path: 'user',
        title: 'User Page',
        component: UserComponent
    },
    {
        path: 'note',
        title: 'note',
        component: NoteComponent
    }
];
