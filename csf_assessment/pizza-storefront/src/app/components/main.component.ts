import { HttpClient } from '@angular/common/http';
import { Component, ComponentFactoryResolver, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { lastValueFrom } from 'rxjs';
import { Order } from '../models';

const SIZES: string[] = [
  "Personal - 6 inches",
  "Regular - 9 inches",
  "Large - 12 inches",
  "Extra Large - 15 inches"
]

const PizzaToppings: string[] = [
    'chicken', 'seafood', 'beef', 'vegetables',
    'cheese', 'arugula', 'pineapple'
]

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  pizzaSize = SIZES[0]
  form!:FormGroup
  newOrder!: Order

  constructor(private fb:FormBuilder, private http: HttpClient, private router:Router) {}

  ngOnInit(): void {
    this.createForm()
  }

  updateSize(size: string) {
    this.pizzaSize = SIZES[parseInt(size)]
  }

  createForm(){
    this.form = this.fb.group({
      name: this.fb.control<string>('', Validators.required),
      email: this.fb.control<string>('', Validators.required),
      pizzaSize: this.fb.control<string>('', Validators.required),
      base: this.fb.control<string>('', Validators.required),
      sauce: this.fb.control<string>('', Validators.required),
      toppings: this.fb.array([false, false, false, false, false, false, false], Validators.required),
      comments: this.fb.control<string>('')
    })
  }

  createOrder(){
    const chosenToppings: string[] = []
    for(let t=0; t< this.form.value.toppings.length; t++){
      if(this.form.value.toppings[t]==true)
      { chosenToppings.push(PizzaToppings[t])}
    }
    this.newOrder = this.form.value
    this.newOrder.toppings = chosenToppings
    console.info('Check order output: ', this.newOrder)

    lastValueFrom( this.http.post('api/order', this.newOrder) )
                .then( v =>
                  lastValueFrom( this.http.get(`api/order/${this.newOrder.email}/all`))
                      .then( v => { this.router.navigate(['orders'])  })
                      .catch(error => console.error('error in get orders: ', error))
                   )
                .catch(error => console.error('error: ', error))
  }

  getOrders(){

  }

}
