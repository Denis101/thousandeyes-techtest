import { Component, OnInit } from '@angular/core';
import { PersonService } from '../service/person.service';
import { Person } from '../model/Person';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  graph: Person;

  constructor(private personService: PersonService) { }

  ngOnInit() {
    this.personService.getGraph(parseInt(localStorage.getItem('id')))
      .subscribe(g => {
        this.graph = g;
        console.log(this.graph);
      });
  }

  sendMessage() {
    console.log('hello');
    this.personService.addMessage(1, 'Hello world').subscribe(console.log);
  }
}
