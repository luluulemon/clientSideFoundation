import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { formText, ObjIDString } from '../model';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SavePostService {

  constructor(private http: HttpClient) { }

  savePic(text: formText): Promise<ObjIDString>{
    return firstValueFrom(this.http.post<ObjIDString>("http://localhost:8080/newPostText", text))

  }
}
