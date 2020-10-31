import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.css'],
})
export class ReportsComponent implements OnInit {
  rtype: string = '';
  src: string = '';
  constructor() {}

  ngOnInit(): void {}
  generateReport() {
    this.src = 'http://localhost:8090/roomcrudwar/reportView/' + this.rtype;
  }
}
