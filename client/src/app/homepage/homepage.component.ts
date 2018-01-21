import { Component } from '@angular/core';
import { PersonService } from '../service/person.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent {
  constructor(private personService: PersonService) { }

  sendMessage() {
    this.personService.addMessage(1, 'Hello world').subscribe(console.log);
  }
}
