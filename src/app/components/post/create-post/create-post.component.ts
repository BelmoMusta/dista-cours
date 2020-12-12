import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {DataService} from '../../../service/DataService';
import {Router} from '@angular/router';
import {Cours} from '../../../objects/cours';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css']
})
export class CreatePostComponent implements OnInit {
  fileToUpload: File = null;
  createPostForm: FormGroup;
  loading = false;
  post: Cours;
  name = new FormControl('');

  constructor(private formBuilder: FormBuilder, private dataService: DataService, private router: Router) {
  }

  ngOnInit(): void {
    this.post = new Cours();

    this.createPostForm = this.formBuilder.group({
      post: this.formBuilder.group({
        name: [' ', Validators.required]
      })
    });
  }

  onSubmit() {
    if (this.createPostForm.invalid) {
      return;
    }
    this.post = this.createPostForm.value.post;
    this.dataService.create(this.post).subscribe((data: Cours) => {
      this.router.navigate(['/posts']);
    });

  }

  handleFileInput(files: FileList) {
    alert('file ready to upload');
    const file = this.fileToUpload = files.item(0);
    this.dataService.postFile(file);
  }

}
