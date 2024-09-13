import { Component, ViewChild } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';
import { MatMenu } from '@angular/material/menu';

@Component({
  selector: 'app-app-header',
  templateUrl: './app-header.component.html',
  styleUrls: ['./app-header.component.css']
})
export class AppHeaderComponent {
  @ViewChild('sidenav') sidenav!: MatSidenav;
  @ViewChild('genreMenu') genreMenu!: MatMenu;
}
