import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {DataService} from '../../../service/DataService';
import {Cours} from '../../../objects/cours';

@Component({
  selector: 'app-show-post',
  templateUrl: './show-post.component.html',
  styleUrls: ['./show-post.component.css']
})
export class ShowPostComponent implements OnInit {

  post: Cours;

  constructor(private route: ActivatedRoute, private service: DataService) {

    this.route.params.subscribe(params =>
      service.get(params.id).subscribe(data => {
        this.post = data;
        console.log(data);
      }));

  }

  ngOnInit(): void {
  }

}
