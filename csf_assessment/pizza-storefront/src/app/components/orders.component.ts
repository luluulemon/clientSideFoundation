import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { OrderSummary } from '../models';
import { PizzaService } from '../pizza.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent {

  constructor(private pizzaSvc: PizzaService, private activatedRoute: ActivatedRoute){}

  orderSums: OrderSummary[] = []
  orderEmail!: string

  ngOnInit(){
    this.orderSums = this.pizzaSvc.orderSums
    console.info('Check orderSums: ', this.orderSums)
    this.orderEmail = this.activatedRoute.snapshot.params['email']
  }
}
