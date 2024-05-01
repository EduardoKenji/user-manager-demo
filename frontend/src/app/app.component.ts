import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from './user';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  users: User[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.http.get<User[]>(
      "http://localhost:8080/users"
    ).subscribe(data => this.users = data);
  }

  appendData(newUser: any): void {
    this.users.push(newUser);
  }
}
