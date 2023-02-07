import { HttpParams } from '@angular/common/http';
import { Component } from '@angular/core';
import { gameSummary } from '../model';
import { GameService } from '../services/GameService';

@Component({
  selector: 'app-master',
  templateUrl: './master.component.html',
  styleUrls: ['./master.component.css']
})
export class MasterComponent {

  games: gameSummary[] = []
  limit: number = 10
  offset: number = 0

  constructor(private gameSvc: GameService){}

  ngOnInit(): void {
    this.gameSvc.getGames(this.limit, this.offset)
            .then(gameList => this.games = gameList)
            .catch(error => { console.error(error)  } )
  }

  nextPage( ){
    this.offset += this.limit
    console.info("check offset: ", this.offset)
    this.gameSvc.getGames(this.limit, this.offset)
    .then(gameList => this.games = gameList)
    .catch(error => { console.error(error)  } )
  }

  previousPage(){
    this.offset -= this.limit
    console.info("check offset: ", this.offset)
    this.gameSvc.getGames(this.limit, this.offset)
    .then(gameList => this.games = gameList)
    .catch(error => { console.error(error)  } )
  }
}
