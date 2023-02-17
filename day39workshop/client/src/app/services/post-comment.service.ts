import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Comments, Hero } from '../model';
import { lastValueFrom } from 'rxjs'

@Injectable({
  providedIn: 'root'
})
export class PostCommentService {

  constructor(private http:HttpClient) { }

  postComment(comment: Comments): Promise<Comments[]>{
    return lastValueFrom( this.http.post<Comments[]>(`/api/character/${comment.id}`, comment) )

  }

  getComment(id: number): Promise<Comments[]>{
    return lastValueFrom( this.http.get<Comments[]>(`api/character/${id}/comments`) )

  }
}