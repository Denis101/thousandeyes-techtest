import { Component, OnInit } from '@angular/core';
import { PersonService } from './service/person.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit  {

  private readonly USERNAME: string = 'username';
  private readonly PASSWORD: string = 'password';

  private title: string = 'ThousandEyes Messenger';

  constructor(private personService: PersonService) { }

  ngOnInit() {
    this.personService.setCredentials(
      localStorage.getItem(this.USERNAME),
      localStorage.getItem(this.PASSWORD)
    );
  }
}
