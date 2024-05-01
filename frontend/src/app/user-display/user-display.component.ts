import { Component, Input } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';

import { User } from './../user';

@Component({
  selector: 'app-user-display',
  standalone: true,
  imports: [MatButtonModule, MatCardModule],
  templateUrl: './user-display.component.html',
  styleUrl: './user-display.component.css'
})
export class UserDisplayComponent {
  @Input() user: User = new User(0, "", "", "", "");
}
