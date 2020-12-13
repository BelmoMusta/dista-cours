import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthenticationService} from '../../service/auth/authentication.service';
import {first} from 'rxjs/operators';
import {Router} from '@angular/router';

@Component({
  selector: 'app-activation',
  templateUrl: './activation.component.html',
  styleUrls: ['./activation.component.scss']
})
export class ActivationComponent implements OnInit {
  loading = false;
  activationForm: FormGroup;
  submitted: false;
  error = '';

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private authenticationService: AuthenticationService) {
  }

  ngOnInit(): void {
    this.activationForm = this.formBuilder.group({
      activationCode: ['', Validators.required]
    });

  }

  onSubmit() {
    this.authenticationService.activate(this.f.activationCode.value)
      .pipe(first())
      .subscribe(data => {
        console.log('data', data);
        this.router.navigate(['/login']);
      }, error => {
        this.error = error;
        this.loading = false;

      });
  }

  get f() {
    return this.activationForm.controls;
  }
}
