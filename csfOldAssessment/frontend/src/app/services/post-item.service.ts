import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { lastValueFrom } from 'rxjs';
import { SellerPost } from '../model';

@Injectable({
  providedIn: 'root'
})
export class PostItemService {

  constructor(private httpSvc: HttpClient) { }

  

  postImage(image:FormData): Promise<SellerPost>{
    const headers = new HttpHeaders().set('Content-Type', 'multipart/form-data');
    return lastValueFrom( this.httpSvc.post<SellerPost>('/api/posting', image, {headers: headers}) )
  }
}
