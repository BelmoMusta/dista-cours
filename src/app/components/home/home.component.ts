import {Component, OnInit} from '@angular/core';
import {first} from 'rxjs/operators';
import {DataService} from '../../service/DataService';
import {Cours} from '../../objects/cours';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  loading = false;
  posts: Cours[];


  constructor(private dataService: DataService) {
  }

  ngOnInit(): void {
    this.loading = true;
    this.dataService.getAllPosts()
      .pipe(first())
      .subscribe(posts => {
        this.loading = false;
        this.posts = posts;
      });
  }

}
