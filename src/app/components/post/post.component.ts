import {Component, OnInit} from '@angular/core';
import {DataService} from '../../service/DataService';
import {Post} from '../../objects/post';

@Component({
  selector: 'app-post',
  templateUrl: './post-component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  constructor(private dataService: DataService) {
  }

  posts: Post[];
  loading = true;

  ngOnInit(): void {

    this.dataService.getAllPosts().subscribe((data: any[]) => {
      this.posts = data;
      this.loading = false;
    });
  }

}
