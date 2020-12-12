import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../service/auth/authentication.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  authenticated: boolean;

  constructor(
    private router: Router,
    public authenticationService: AuthenticationService) {
    console.log('authenticated', this.authenticationService.authenticated);

  }

  ngOnInit(): void {
    this.authenticated = this.authenticationService.authenticated;
  }

  logout() {
    this.authenticationService.logout();
    this.authenticated = false;
  }

  mySpace() {

    return this.router.navigate(['/student']);

  }

}
