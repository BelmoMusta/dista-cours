import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';

@Component({
  selector: 'app-tag',
  encapsulation: ViewEncapsulation.ShadowDom,
  templateUrl: './tag.component.html',
  styleUrls: ['./tag.component.css']
})
export class TagComponent implements OnInit {

  @Input()
  tagName: string;


  constructor() {
  }

  ngOnInit(): void {
  }

}
