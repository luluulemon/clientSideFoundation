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

    addLikes(id: string): Promise<formText[]>{
      return firstValueFrom(this.http.get<formText[]>(`http://localhost:8080/likes/${id}`))
    }

    addDislikes(id: string): Promise<formText[]>{
      console.info('calling the addDislikes Repo')
      return firstValueFrom(this.http.get<formText[]>(`http://localhost:8080/dislikes/${id}`))
    }
  }