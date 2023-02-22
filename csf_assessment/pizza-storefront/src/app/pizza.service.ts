// Implement the methods in PizzaService for Task 3
// Add appropriate parameter and return type 


import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { lastValueFrom } from "rxjs";
import { Order, OrderSummary } from "./models";

@Injectable()
export class PizzaService {

  constructor(private http:HttpClient, private router:Router) { }

  orderSums: OrderSummary[] = []

  // POST /api/order
  // Add any required parameters or return type
  createOrder(newOrder: Order) { 
    lastValueFrom( this.http.post('api/order', newOrder) )
    .then( v => this.getOrders(newOrder.email))
    .catch(error => console.error('error in post: ', error)) 
  }

  // GET /api/order/<email>/all
  // Add any required parameters or return type
  getOrders(email:string) { 
    lastValueFrom( this.http.get<OrderSummary[]>(`api/order/${email}/all`))
    .then( v => { console.info("check orderSums: ", v)
                  this.orderSums = v
                  this.router.navigate(['orders', email])  })
    .catch(error => {
                        console.error('error in get orders: ', error)
                        alert(error.message)
                    } )
  }

}
