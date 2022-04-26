import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../models/user';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = environment.usersURL;

  constructor(private httpClient: HttpClient, private authService: AuthenticationService) { }

  getSingleUser(userId: string): Observable<User> {
    let getUserUrl = `${this.baseUrl}/${userId}`;
    let tok=this.authService.getToken();
    return this.httpClient.get<User>(getUserUrl,
      {
        headers: new HttpHeaders().set('Authorization',`Bearer ${tok}`)
      });
  }

  getAllUsers(): Observable<User[]> {
    let getAllUsersUrl = `${this.baseUrl}`;
    let tok=this.authService.getToken();
    return this.httpClient.get<User[]>(getAllUsersUrl,
    {
      headers: new HttpHeaders().set('Authorization',`Bearer ${tok}`)
    });

  }

  



}
