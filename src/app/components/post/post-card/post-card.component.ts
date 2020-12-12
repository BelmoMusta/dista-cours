import {Component, Input, OnInit} from '@angular/core';
import {Cours} from '../../../objects/cours';

@Component({
  selector: 'app-post-card',
  templateUrl: './post-card.component.html',
  styleUrls: ['./post-card.component.css']
})
export class PostCardComponent implements OnInit {

  @Input()
  post: Cours;

  constructor() {
  }

  ngOnInit(): void {
  }

}
