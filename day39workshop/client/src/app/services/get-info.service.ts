import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Hero } from '../model';
import { lastValueFrom } from 'rxjs'

@Injectable({
  providedIn: 'root'
})
export class GetInfoService {

  constructor(private http:HttpClient) { }

  getHeroes(char: String): Promise<Hero[]>{
    return lastValueFrom( this.http.get<Hero[]>(`/api/characters/${char}`) )
  }

  getHero(id:number): Promise<Hero>{
    return lastValueFrom( this.http.get<Hero>(`/api/character/${id}`))
  }
}
