import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { User } from 'src/app/models/user';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { RoutingService } from 'src/app/services/routing.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  errorMessage: string = "";

  constructor(private authService: AuthenticationService, private routingService: RoutingService) { 
    this.loginForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required, Validators.pattern("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$")])
    })
  }

  ngOnInit(): void {
  }

  onSubmit() {
    if(this.loginForm.valid) {
      this.authService.loginUser(this.createUser()).subscribe(response => {
        this.authService.storeUserInformation(response);
        this.routingService.openDashboard();
      },
      (error: { status: number; error: string; }) => {
        if (error.status === 404) {
          setTimeout(() => { alert(error.error) }, 1000);
        }
      })
    } else {
      setTimeout(() => { alert('Your password does not match') }, 1000);
    }
  }

  createUser() {
    let user: User = {
      userId: "",
      email: this.loginForm.value.email,
      password: this.loginForm.value.password,
      firstName: "",
      lastName: ""
    };
    return user;
  }
}
