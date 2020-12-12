import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Post} from '../objects/post';
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DataService {


  constructor(private httpClient: HttpClient) {
  }

  public getAllPosts() {

    return this.httpClient.get<Post[]>(environment.apiUrl + '/post/');
  }

  create(post: Post) {
    return this.httpClient.post(environment.apiUrl + '/post/', post);
  }

  get(id: number): Observable<Post> {
    return this.httpClient.get<Post>(environment.apiUrl + '/post/' + id);
  }

  postFile(fileToUpload: File) {
    const endpoint = environment.apiUrl + '/upload-file';
    const formData: FormData = new FormData();
    formData.append('fileKey', fileToUpload, fileToUpload.name);
    return this.httpClient
      .post(endpoint, formData, {headers: {}})
      .subscribe(() => {
        console.log('uploaded');
      }, () => console.log('error'));
  }
}
