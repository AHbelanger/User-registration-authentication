import { Component, OnInit } from '@angular/core';
import { faHouseChimney } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  buildingIcon = faHouseChimney;
  
  constructor() { }

  ngOnInit(): void {
  }

}
