import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { faEyeSlash, faEye } from '@fortawesome/free-solid-svg-icons';
import { User } from 'src/app/models/user';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { RoutingService } from 'src/app/services/routing.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  registrationForm: FormGroup;
  errorMessage: string = '';
  showPassword:boolean = false;
  showConfirmPassword:boolean=false;
  eyeslashIcon = faEyeSlash;
  eyeIcon = faEye;

  constructor(
    private authService: AuthenticationService,
    private routingService: RoutingService
  ) {
    this.registrationForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [
        Validators.required,
        Validators.pattern('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$'),        
      ]), //This regex enforces at least 8 character with one uppercase, one lowercase and one number.
      confirmPassword: new FormControl('', [
        Validators.required,
        Validators.pattern('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$'),
      ]),
    });
  }

  ngOnInit(): void {}

  onSubmit() {
    if (
      this.registrationForm.valid &&
      this.registrationForm.value.password ===
        this.registrationForm.value.confirmPassword
    ) {
      this.authService.registerUser(this.createUser()).subscribe(
        (response) => {
          this.authService.storeUserInformation(response);
          this.routingService.openDashboard();
        },
        (error: { status: number; error: string }) => {
          if (error.status === 409) {
            setTimeout(() => {
              alert(error.error);
            }, 1000);
          }
        }
      );
    } else {
      setTimeout(() => {
        alert('Your password does not match');
      }, 1000);
    }
  }

  createUser() {
    let user: User = {
      userId: '',
      email: this.registrationForm.value.email,
      password: this.registrationForm.value.password,
      firstName: '',
      lastName: '',
    };
    return user;
  }

}
