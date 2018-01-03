import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Login } from '../model/Login';

@Injectable()
export class AuthService implements CanActivate {
    private returnUrl: string;

    constructor(private router: Router) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        if (localStorage.getItem('id')) {
            return true;
        }

        this.returnUrl;
        this.router.navigate(['/login'], { queryParams: { returnUrl: state.url }});
        return false;
    }

    login(loginModel: Login) {
        localStorage.setItem('id', loginModel.id.toString());
        localStorage.setItem('username', loginModel.username);
        localStorage.setItem('password', loginModel.password);
        this.router.navigate([this.returnUrl || '/']);
    }
}