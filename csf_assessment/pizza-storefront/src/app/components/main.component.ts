import { HttpClient } from '@angular/common/http';
import { Component, ComponentFactoryResolver, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { lastValueFrom } from 'rxjs';
import { Order, OrderSummary } from '../models';
import { PizzaService } from '../pizza.service';

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

  constructor(private fb:FormBuilder, private http: HttpClient, 
            private router:Router, private pizzaSvc: PizzaService) {}

  ngOnInit(): void {
    this.createForm()
  }

  updateSize(size: string) {
    this.pizzaSize = SIZES[parseInt(size)]
  }

  createForm(){
    this.form = this.fb.group({
      name: this.fb.control<string>('', Validators.required),
      email: this.fb.control<string>('', [Validators.required, Validators.email]),
      pizzaSize: this.fb.control<number>(0, Validators.required),
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

    this.pizzaSvc.createOrder(this.newOrder)
  }

  getOrders(){
    this.pizzaSvc.getOrders(this.form.value.email)
  }

  noToppings(): boolean{    // For Validity of form, true if there are no toppings
    return this.form.value.toppings.filter( (x:Boolean) => x==true).length==0
  }

}
