import { Component } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user.model';

@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.css']
})
export class UserRegisterComponent {

  user: User = { username: '', password: '' };

  constructor(private userService: UserService) { }

  registerUser(): void {
    this.userService.registerUser(this.user).subscribe(() => {
      alert('User registered successfully!');
    });
  }
}
