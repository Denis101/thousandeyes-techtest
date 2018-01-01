import { Injectable } from '@angular/core';
import { Person } from '../model/Person';

@Injectable()
export class PersonService {

  constructor() { }

  public getPerson(id: number): Person {
    return null;
  }

  public getFollowers(id: number): Person[] {
    let people: Person[] = [];
    return people;
  }

  public getFollowing(id: number): Person[] {
    let people: Person[] = [];
    return people;
  }
}
