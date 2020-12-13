import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthenticationService} from '../../service/auth/authentication.service';
import {first} from 'rxjs/operators';
import {Router} from '@angular/router';

@Component({
  selector: 'app-insription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.scss']
})
export class InscriptionComponent implements OnInit {
  loading = false;
  inscriptionForm: FormGroup;
  submitted: false;
  next: string;
  error = '';

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private authenticationService: AuthenticationService) {
  }

  ngOnInit(): void {
    this.inscriptionForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      name: ['', Validators.required],
      lastName: ['', Validators.required]
    });

  }

  onSubmit() {
    this.authenticationService.register(this.f.name.value, this.f.lastName.value, this.f.username.value, this.f.password.value)
      .pipe(first())
      .subscribe(data => {
        this.router.navigate(['/activate']);
      }, error => {
        this.error = error;
        this.loading = false;

      });
  }

  get f() {
    return this.inscriptionForm.controls;
  }
}
