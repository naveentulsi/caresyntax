import { Component, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-dyanmic-listing',
  templateUrl: './dyanmic-listing.component.html',
  styleUrls: ['./dyanmic-listing.component.css']
})
export class DyanmicListingComponent implements OnInit {

  @Input() dynamicList: any;
  @Output() selectedValue: any;

  constructor() { }

  ngOnInit() {
  }

}
