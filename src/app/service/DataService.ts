import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Cours} from '../objects/cours';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataService {


  constructor(private httpClient: HttpClient) {
  }

  public getAllPosts() {

    return this.httpClient.get<Cours[]>(environment.apiUrl + '/api/cours');
  }

  create(post: Cours) {
    return this.httpClient.post(environment.apiUrl + '/cours/', post);
  }

  get(id: number): Observable<Cours> {
    return this.httpClient.get<Cours>(environment.apiUrl + '/cours/' + id);
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
