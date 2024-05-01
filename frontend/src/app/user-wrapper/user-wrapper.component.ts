import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { User } from './../user';
import { UserEditComponent } from './../user-edit/user-edit.component';
import { UserDisplayComponent } from './../user-display/user-display.component';

@Component({
  selector: 'app-user-wrapper',
  standalone: true,
  imports: [CommonModule, UserEditComponent, UserDisplayComponent],
  templateUrl: './user-wrapper.component.html',
  styleUrl: './user-wrapper.component.css'
})
export class UserWrapperComponent {
  @Input() user: User = new User(0, "", "", "", "");
  @Output() deleteUserEvent = new EventEmitter();
  updatable: boolean = false;

  handleUpdateClick(): void {
    this.updatable = true;
  }

  handleSaveUpdated(user: User): void {
    this.updatable = false;
    this.user = user;
  }
}
