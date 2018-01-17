import { Component, OnInit } from '@angular/core';
import { PersonService } from '../service/person.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  constructor(private personService: PersonService) { }

  ngOnInit() {
  }

  sendMessage() {
    console.log('hello');
    this.personService.addMessage(1, 'Hello world');
  }
}
