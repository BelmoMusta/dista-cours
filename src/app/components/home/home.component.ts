import {Component, OnInit} from '@angular/core';
import {first} from 'rxjs/operators';
import {DataService} from '../../service/DataService';
import {Post} from '../../objects/post';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  loading = false;
  posts: Post[];


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
