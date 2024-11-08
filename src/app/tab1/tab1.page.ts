import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-tab1',
  templateUrl: 'tab1.page.html',
  styleUrls: ['tab1.page.scss']
})
export class Tab1Page implements OnInit {

  token: string | null = null;

  constructor() {}

  ngOnInit() {
    this.token = localStorage.getItem('jwtToken'); // Recupera el token de localStorage
    console.log('Token JWT:', this.token); // Muestra el token en la consola
  }
}