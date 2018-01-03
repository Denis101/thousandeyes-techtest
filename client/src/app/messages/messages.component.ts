import { Component, OnInit } from '@angular/core';
import { Message } from '../model/Message';
import { PersonService } from '../service/person.service';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {
  messages: Message[];

  constructor(private personService: PersonService) { }

  ngOnInit() {
    this.getMessages();
  }

  getMessages(): void {
    this.personService.getMessages(parseInt(localStorage.getItem('id')))
      .subscribe(m => this.messages = m);
  }

}
