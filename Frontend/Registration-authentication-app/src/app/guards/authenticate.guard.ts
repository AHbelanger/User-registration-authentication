import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticationService } from '../services/authentication.service';
import { RoutingService } from '../services/routing.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticateGuard implements CanActivate {
  
  constructor(private authService: AuthenticationService, private routingService: RoutingService) {

  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
     
      let tok=this.authService.getToken();
      const boolresult=this.authService.isUserAuthenticated(tok);
   
        return boolresult.then
        (  
            (res:any)=>
            {
             if (!res)
             {
               this.routingService.openDashboard();
             }
             return res;
            }

            )
            .catch
            (
              (err)=>
              {
                this.routingService.openLogin();
                return false;
              }
            )
    

  
 }
}
