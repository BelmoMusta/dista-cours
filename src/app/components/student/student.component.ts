import {Component, OnInit} from '@angular/core';
import {first} from 'rxjs/operators';
import {DataService} from '../../service/DataService';
import {Cours} from '../../objects/cours';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.scss']
})
export class StudentComponent implements OnInit {
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
