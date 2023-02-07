import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscribable, Subscription } from 'rxjs';
import { Game } from '../model';
import { GameService } from '../services/GameService';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent {
  
  game!: Game
  param$!: Subscription

  constructor(private activatedRoute: ActivatedRoute, private gameSvc: GameService){}

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(
      params => 
      { const param = params['gameId'] 
        this.gameSvc.getGameById(param)
                    .then(g => this.game = g)
                    .catch(error => console.error("Some error here: ",error))
      }
    )
  }
}
