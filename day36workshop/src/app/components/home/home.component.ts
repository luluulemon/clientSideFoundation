
import { Component, HostListener } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  form!: FormGroup
  allCities: string[] = []

  constructor(private fb: FormBuilder, private router:Router){}

  ngOnInit(){
    this.createForm()

    const citiesJSON = localStorage.getItem("cities")
    if(citiesJSON)
    { this.allCities = JSON.parse(citiesJSON) }
  }

  createForm(){
    this.form = this.fb.group({
      city: this.fb.control<string>('', Validators.required)
    })
  }

  addCity(){  
    console.info('City: ', this.form.value.city)
    const newCity = this.form.value.city.toLowerCase()
    // check for duplicate
    if( !this.allCities.find((city)=> city==newCity ) )
    {   this.allCities.push(newCity)      }
    this.createForm()
  }

  deleteCity(index:number){
    this.allCities.splice(index, 1)
  }

  ngOnDestroy(){
    console.info('ngDestroy is called')
    // Save when navigating away from component
    localStorage.setItem("cities", JSON.stringify(this.allCities) )
  }

  @HostListener('window:beforeunload') 
  saveCities() {
    // save before page Exit
    localStorage.setItem("cities", JSON.stringify(this.allCities) )
  }

}
