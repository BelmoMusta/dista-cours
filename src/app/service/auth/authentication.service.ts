import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';
import {map} from 'rxjs/operators';

import {environment} from 'src/environments/environment';
import {User} from 'src/app/objects/user';
import {Router} from '@angular/router';

@Injectable({providedIn: 'any'})

export class AuthenticationService {
  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;
  public authenticated = false;

  constructor(private http: HttpClient, private router: Router) {
    this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public;

  get currentUserValue(): User {
    return this.currentUserSubject.value;
  }

  login(username: string, password: string) {
    return this.http.post <any>(`${environment.apiUrl}/api/authenticate`, {username, password})
      .pipe(map(user => {
        // store user details and basic auth credentials in local storage to keep user logged in between page refreshes
        user.authdata = window.btoa(username + ':' + password);
        localStorage.setItem('currentUser', JSON.stringify(user));
        console.log('user', user);
        this.currentUserSubject.next(user);
        this.authenticated = true;
        return user;
      }));
  }

  logout() {
    this.http.post <any>(`${environment.apiUrl}/api/logout`, {})
      .subscribe(content => {
        localStorage.removeItem('currentUser');
        this.currentUserSubject.next(null);
        this.authenticated = false;
        return this.router.navigate(['/login']);
      }, error => {

      });

  }

  register(username: string, password) {
    return this.http.post <any>(`${environment.apiUrl}/api/user/register`, {email: username, password})
      .pipe(map(content => {
        return true;
      }));

  }

  activate(code: string) {
    return this.http.get <any>(`${environment.apiUrl}/api/user/activate/${code}`)
      .pipe(map(content => {
        return true;
      }));

  }
}
