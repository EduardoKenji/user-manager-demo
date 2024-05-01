import { Component, Output, EventEmitter, ViewChild } from '@angular/core';
import { NgForm, FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { User } from './../user';

import { MatInputModule } from '@angular/material/input'
import { MatFormFieldModule } from '@angular/material/form-field';

import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-user-input',
  standalone: true,
  imports: [FormsModule, MatInputModule, MatFormFieldModule,
            MatIconModule, MatDividerModule, MatButtonModule],
  templateUrl: './user-input.component.html',
  styleUrl: './user-input.component.css'
})
export class UserInputComponent {

  @ViewChild("userForm") userForm!: NgForm;
  @Output() newDataEvent = new EventEmitter();

  constructor(private http: HttpClient) {}

  onSubmit(): void {
    this.http.post<User>(
      "http://localhost:8080/users",
      this.userForm.value
    ).subscribe(data => this.newDataEvent.emit(data));
  }
}
