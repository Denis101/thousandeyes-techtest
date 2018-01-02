import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { catchError } from 'rxjs/operators';

import { Person } from '../model/Person';
import { Message } from '../model/Message';

@Injectable()
export class PersonService {

  private readonly CONTENT_TYPE_HEADER: string = "Content-Type";
  private readonly AUTHORIZATION_HEADER: string = 'Authorization';

  private readonly APPLICATION_JSON: string = "application/json";

  private readonly PERSON_URL: string = 'http://localhost:8080/v1/person'; 

  private readonly headers: HttpHeaders;

  constructor(private readonly http: HttpClient) {
    this.headers = new HttpHeaders();
    this.headers.append(this.CONTENT_TYPE_HEADER, this.APPLICATION_JSON);
  }

  public setCredentials(username: string, password: string) {
    if (!username || username.length === 0 || !password || password.length === 0) {
      return;
    }

    const creds: string = `Basic ${btoa(`${username}:${password}`)}`;

    if (this.headers.has(this.AUTHORIZATION_HEADER)) {
      this.headers.set(this.AUTHORIZATION_HEADER, creds);
      return;
    }

    this.headers.append(this.AUTHORIZATION_HEADER, creds);
  }

  public getPerson(id: number): Observable<Person> {
    return this.http.get<Person>(`${this.PERSON_URL}/${id}`, {
      headers: this.headers
    }).pipe(
      catchError(this.handleError<Person>(`getPerson id=${id}`))
    );
  }
 
  public getMessages(id: number): Observable<Message[]> {
    return this.http.get<Message[]>(`${this.PERSON_URL}/${id}/messages`, {
      headers: this.headers
    }).pipe(
      catchError(this.handleError<Message[]>(`getMessages id=${id}`))
    );
  }

  public getFollowers(id: number): Observable<Person[]> {
    return this.http.get<Person[]>(`${this.PERSON_URL}/${id}/followers`, {
      headers: this.headers
    }).pipe(
      catchError(this.handleError<Person[]>(`getFollowers id=${id}`))
    );
  }

  public getFollowing(id: number): Observable<Person[]> {
    return this.http.get<Person[]>(`${this.PERSON_URL}/${id}/following`, {
      headers: this.headers
    }).pipe(
      catchError(this.handleError<Person[]>(`getFollowing id=${id}`))
    );
  }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead
 
      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
