import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../service/auth/authentication.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  constructor(
    private router: Router,
    public authenticationService: AuthenticationService) {
    console.log('authenticated', this.authenticationService.authenticated);

  }

  ngOnInit(): void {
  }

  get authenticated(): boolean {
    return this.authenticationService.authenticated;
  }

  get connectedUser() {
    return (this.authenticationService.currentUserValue);
  }

  get fullName() {
    return (this.authenticationService.currentUserValue.name + ' ' + this.connectedUser.lastName);
  }


  logout() {
    this.authenticationService.logout();
  }

  mySpace() {
    return this.router.navigate(['/student']);

  }

}
