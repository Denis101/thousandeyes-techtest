import { Component, OnInit } from '@angular/core';
import { PersonService } from './service/person.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit  {

  private readonly ID: string = 'id';
  private readonly USERNAME: string = 'username';
  private readonly PASSWORD: string = 'password';

  private title: string = 'ThousandEyes Messenger';
  private username: string;
  private messageCount: number;
  private followingCount: number;
  private followerCount: number;

  constructor(private personService: PersonService) { }

  ngOnInit() {
    this.username = localStorage.getItem(this.USERNAME);

    this.personService.setCredentials(
      this.username,
      localStorage.getItem(this.PASSWORD)
    );

    const id: number = parseInt(localStorage.getItem(this.ID));

    this.personService.getMessages(id).subscribe(f => this.messageCount = f.length);
    this.personService.getFollowing(id).subscribe(f => this.followingCount = f.length);
    this.personService.getFollowers(id).subscribe(f => this.followerCount = f.length);
  }
}
