import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RoutingService {

  constructor(private router : Router ) { }

  openLogin(){
    this.router.navigate(['login']);
  }

  openDashboard(){
    this.router.navigate(['dashboard']);
  }

  openRegister(){
    this.router.navigate(['register']);
  }

  openUserFormRegistration(){
    this.router.navigate(['registerForm']);
  }

}
