import { Component } from '@angular/core';
import { AuthService } from '../service/auth.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-tabs',
  templateUrl: 'tabs.page.html',
  styleUrls: ['tabs.page.scss']
})
export class TabsPage {

  constructor(private authService:AuthService) {}

  isLoggedIn(): boolean {
    return !!localStorage.getItem('jwtToken');
  }
}
