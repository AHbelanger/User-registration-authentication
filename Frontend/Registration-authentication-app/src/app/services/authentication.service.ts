import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { map, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private baseUrl = environment.authenticationURL

  constructor(private httpClient: HttpClient) { }

  registerUser(user: User): Observable<User> {
    return this.httpClient.post<User>(`${this.baseUrl}/register`, user);
  }

  loginUser(user: User): Observable<User> {
    return this.httpClient.post<User>(`${this.baseUrl}/login`, user);
  }

  storeUserInformation(response: any) {
    localStorage.setItem("token", response.token);
    localStorage.setItem("userId", response.token);
  }

  getToken() {
    return localStorage.getItem("token");
  }

  isUserAuthenticated(token:any): Promise<boolean> {
    return this.httpClient.post(`${this.baseUrl}/isAuthenticated` , {}, 
    {headers: new HttpHeaders().set('Authorization', `Bearer ${token}`)})
    .pipe(map((res:any) => { return(res['isAuthenticated']);
  })).toPromise()
 
}

  validateToken() {
    let token = this.getToken();
    if (!token) {
      token = '';
    }
    let validateTokenUrl = `${this.baseUrl}/validateToken`;
    let headers = {
      headers: new HttpHeaders({
        'token': token
      })
    };
    return this.httpClient.post<boolean>(validateTokenUrl, null, headers);
  }
}
