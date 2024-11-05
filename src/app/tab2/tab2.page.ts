import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-tab2',
  templateUrl: './tab2.page.html',
  styleUrls: ['./tab2.page.scss'],
})
export class Tab2Page implements OnInit {
  center: google.maps.LatLngLiteral = { lat: 43.27192290021221, lng: -2.948695787239455 };
  zoom = 15;
  markerOptions: google.maps.MarkerOptions = { position: this.center, title: 'Almi Bilbao' };

  constructor() { }

  ngOnInit() { }
}