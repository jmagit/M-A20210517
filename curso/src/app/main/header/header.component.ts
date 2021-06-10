import { Component, OnInit } from '@angular/core';
import { NavigationService } from 'src/app/common-services';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(public nav: NavigationService) { }

  ngOnInit() {
  }

}
