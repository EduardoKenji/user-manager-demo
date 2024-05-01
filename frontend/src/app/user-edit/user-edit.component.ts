import { Component, Input, Output, EventEmitter } from '@angular/core';
import { NgForm, FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { User } from './../user';

import { MatInputModule } from '@angular/material/input'
import { MatFormFieldModule } from '@angular/material/form-field';

import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-user-edit',
  standalone: true,
  imports: [FormsModule, MatInputModule, MatFormFieldModule,
  MatIconModule, MatDividerModule, MatButtonModule],
  templateUrl: './user-edit.component.html',
  styleUrl: './user-edit.component.css'
})
export class UserEditComponent {

  @Input() user: User = new User(0, "", "", "", "");

  @Output() updateUserEvent = new EventEmitter();

  constructor(private http: HttpClient) {}

  onSubmit(): void {
    this.http.put(
      "http://localhost:8080/users/" + this.user.id,
      this.user
    ).subscribe(data => {this.updateUserEvent.emit(data)});
  }
}
