import { Component, Output } from '@angular/core';
import { Subject } from 'rxjs';
import { Hero } from '../Hero';

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.css']
})
export class InventoryComponent {

  @Output()
  onAddHero = new Subject<string>();

  @Output() onReduceHero = new Subject<string>();

  heroes: Hero[] = [ { name:'spiderman', image: './assets/inventoryImages/spiderman.jpeg' }, 
                      {name: 'superman', image:'./assets/inventoryImages/superman.jpeg'},
                      {name:'flash', image:'./assets/inventoryImages/flash.jpeg'},
                      {name:  'wonderwoman', image:'./assets/inventoryImages/wonderwoman.jpeg'}]

  addTo(name: string){
    this.onAddHero.next(name)
  }

  reduceFrom(name: string){
    this.onReduceHero.next(name)
  }
}
