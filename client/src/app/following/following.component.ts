import { Component, OnInit } from '@angular/core';
import { Person } from '../model/Person';
import { PersonService } from '../service/person.service';

@Component({
  selector: 'app-following',
  templateUrl: './following.component.html',
  styleUrls: ['./following.component.css']
})
export class FollowingComponent implements OnInit {
  following: Person[];

  constructor(private personService: PersonService) { }

  ngOnInit() {
    this.getFollowing();
  }

  getFollowing(): void {
    this.personService.getFollowing(parseInt(localStorage.getItem('id')))
      .subscribe(f => this.following = f);
  }
}
