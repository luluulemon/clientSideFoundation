import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Hero } from '../model';
import { GetInfoService } from '../services/get-info.service';

@Component({
  selector: 'app-hero-list',
  templateUrl: './hero-list.component.html',
  styleUrls: ['./hero-list.component.css']
})
export class HeroListComponent {

  constructor(private activatedRoute: ActivatedRoute, 
      private getSvc: GetInfoService, private router:Router){  }

  Heroes!: Hero[]
  searchChar: string = ''

  ngOnInit(){
    this.searchChar = this.activatedRoute.snapshot.params['search']
    console.info('check char in list: ', this.searchChar)
    this.getSvc.getHeroes(this.searchChar)
              .then(H => this.Heroes = H)
  }

  getHero(id:number){
    console.info('From hero-list, check id: ', id, this.searchChar)
    this.router.navigate(['/hero', id, this.searchChar])
  }
}
