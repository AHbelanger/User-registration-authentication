import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { RoutingService } from 'src/app/services/routing.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  users: User[] = [];
  
  constructor(private userService: UserService, private routingService: RoutingService) {
    this.userService.getAllUsers().subscribe(response => {
      this.users = response;
    })
   }

  ngOnInit(): void {
  }

  openUserFormRegistration(){
    this.routingService.openUserFormRegistration();
  }

}
