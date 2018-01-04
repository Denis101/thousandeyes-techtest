import { Component, OnInit, Input } from '@angular/core';
import { Login } from '../model/Login';
import { AuthService } from '../service/auth.service';
import { PersonService } from '../service/person.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  @Input() login: Login = new Login();

  private returnUrl: string;

  constructor(
    private authService: AuthService,
    private personService: PersonService
  ) { }

  ngOnInit() {}

  submitLogin() {
    this.authService.login(this.login);
    this.personService.setCredentials(this.login.username, this.login.password);
  }

}
