import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Weather } from 'src/app/model';
import { GetWeatherService } from 'src/app/services/get-weather.service';

@Component({
  selector: 'app-weather',
  templateUrl: './weather.component.html',
  styleUrls: ['./weather.component.css']
})
export class WeatherComponent {

  constructor(private activatedRoute: ActivatedRoute, private weatherSvc:GetWeatherService){}
  model!: Weather

  ngOnInit(){
    const city = this.activatedRoute.snapshot.params['cityName']
    const number = this.activatedRoute.snapshot.params['randomNum']

    this.weatherSvc.getWeather(city)
              .then(v => {console.info(v)
                let weather: Weather = { city: v.name, description: v.weather[0].description, 
                    maxTemp: v.main.temp_max -273.15, minTemp: v.main.temp_min -273.15, 
                    windSpeed:v.wind.speed, pressure:v.main.pressure, 
                    humidity:v.main.humidity, temp: v.main.temp - 273.15 }

                  this.model = weather
                })
              .catch(error => console.error('error: ', error))
  }
}
