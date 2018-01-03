import { Component, OnInit } from '@angular/core';
import { Person } from '../model/Person';
import { PersonService } from '../service/person.service';

@Component({
  selector: 'app-followers',
  templateUrl: './followers.component.html',
  styleUrls: ['./followers.component.css']
})
export class FollowersComponent implements OnInit {
  followers: Person[];

  constructor(private personService: PersonService) { }

  ngOnInit() {
    this.getFollowers();
  }

  getFollowers(): void {
    this.personService.getFollowers(parseInt(localStorage.getItem('id')))
      .subscribe(f => this.followers = f);
  }
}
