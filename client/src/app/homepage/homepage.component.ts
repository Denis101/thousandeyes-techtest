import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  private username: string;

  constructor() { 
    this.username = localStorage.getItem('username');
  }

  ngOnInit() {
  }

}
