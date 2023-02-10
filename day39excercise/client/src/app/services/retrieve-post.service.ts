import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { formText } from "../model";
import { firstValueFrom } from 'rxjs';

@Injectable({
    providedIn: 'root'
  })
  export class RetrievePostService {
  
    constructor(private http: HttpClient) { }
  
    RetrievePic(): Promise<formText[]>{
      return firstValueFrom(this.http.get<formText[]>("http://localhost:8080/getPosts"))
    }
  }