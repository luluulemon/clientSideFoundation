import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { lastValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GetWeatherService {

  constructor(private httpclient: HttpClient) { }

  endpoint: string = 'https://api.openweathermap.org/data/2.5/weather'

  getWeather(city:string): Promise<any>{
    const requestParams: HttpParams = new HttpParams()
    .set("appid", "9d1e07f51f78f435aa874512582af939")
    .set('q', city)

    return lastValueFrom( this.httpclient.get<Promise<any>>(this.endpoint, { params: requestParams } ) )
  }
}
