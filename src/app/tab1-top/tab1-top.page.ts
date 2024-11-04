import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tab1-top',
  templateUrl: './tab1-top.page.html',
  styleUrls: ['./tab1-top.page.scss'],
})
export class Tab1TopPage implements OnInit {

  constructor(private router:Router) { }

  ngOnInit() {
  }

  dialogCarro() {
    
  }

}
