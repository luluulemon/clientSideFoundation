
import { Component, HostListener } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Dexie } from 'dexie';
import { City } from 'src/app/model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent extends Dexie {

  form!: FormGroup
  allCities: City[] = []

  constructor(private fb: FormBuilder, private router:Router){
    super('CitiesDB')         // DB name
    this.version(1).stores({
      Cities: 'city'              // collection Name: Primary Key
    })
    this.dbCities = this.table('Cities')
  }

  dbCities!: Dexie.Table<City, String>

  ngOnInit(){
    this.createForm()
    this.dbCities.toArray().then(c => {
                                        console.info('check city array: ', c)
                                        this.allCities = c})
                          .catch(error => console.error('error: ', error))
    // const citiesJSON = localStorage.getItem("cities")
    // if(citiesJSON)
    // { this.allCities = JSON.parse(citiesJSON) }
  }

  createForm(){
    this.form = this.fb.group({
      city: this.fb.control<string>('', Validators.required)
    })
  }

  addCity(){  
    console.info('City: ', this.form.value.city)
    let newC: City = { city: this.form.value.city.toLowerCase() }

    console.info(newC)
    // check for duplicate
    if( !this.allCities.find((city)=> city.city==newC.city ) )
    {   this.allCities.push(newC)
        this.dbCities.add(newC)          }
    this.createForm()
  }

  deleteCity(index:number){
    this.allCities.splice(index, 1)
  }

  ngOnDestroy(){
    console.info('ngDestroy is called')
    // // Save when navigating away from component
    // localStorage.setItem("cities", JSON.stringify(this.allCities) )

  }

  @HostListener('window:beforeunload') 
  saveCities() {
    // save before page Exit
    localStorage.setItem("cities", JSON.stringify(this.allCities) )
  }



}
