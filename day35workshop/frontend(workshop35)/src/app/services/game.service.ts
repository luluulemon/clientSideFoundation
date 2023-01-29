import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Game } from '../models/Game';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  constructor(private httpClient:HttpClient) { }
  URL = "http://localhost:8080/games-ng"
  URL_public = "https://abstracted-weight-production.up.railway.app/games-ng"

  getGames(limit: number, offset:number): Observable<Game[]>{
    const params = new HttpParams().set("limit", limit).set("offset", offset);

    const headers= new HttpHeaders()
    .set('content-type', 'application/json')
    .set('Access-Control-Allow-Origin', '*');

    //return this.httpClient.get<Game[]>(this.URL, { params: params, headers:headers })
    return this.httpClient.get<Game[]>(this.URL_public, { params: params,  })
  }

}
