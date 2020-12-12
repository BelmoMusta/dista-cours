import {Component} from '@angular/core';
import {User} from './objects/user';
import {Router} from '@angular/router';
import {AuthenticationService} from './service/auth/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'DISTA_COURS';
  currentUser: User;

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService) {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  }
}
