import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { RoutingService } from 'src/app/services/routing.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-singleuser',
  templateUrl: './singleuser.component.html',
  styleUrls: ['./singleuser.component.css']
})
export class SingleuserComponent implements OnInit {
  user: User = new User;

  constructor(private userService: UserService, private router: Router, private routingService: RoutingService) {
    this.userService.getSingleUser(this.getUserId()).subscribe(user => {
      this.user = user;
    })
   }

  ngOnInit(): void {
  }

  getUserId(){
    return this.router.url.split('/')[2];
  }

  public openDashboard(){
    this.routingService.openDashboard();
  }
  

}
