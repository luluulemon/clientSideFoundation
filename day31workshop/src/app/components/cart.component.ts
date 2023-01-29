import { Component, Input } from '@angular/core';
import { heroCount } from '../Hero';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent {

  @Input() heroInCart!: heroCount[]

  
}
