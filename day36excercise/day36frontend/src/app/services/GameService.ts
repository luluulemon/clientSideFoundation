import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Game, gameSummary } from "../model";
import { firstValueFrom, lastValueFrom } from "rxjs"

@Injectable()
export class GameService{

    constructor(private http: HttpClient){}

    getGames(limit:number, offset: number): Promise<gameSummary[]>{
        const params: HttpParams = new HttpParams()
            .set("limit", limit)
            .set("offset", offset)

        return firstValueFrom( this.http.get<gameSummary[]>('api/games', { params: params } ) )
    }



    getGameById(id: number): Promise<Game>{
        return lastValueFrom( this.http.get<Game>(`api/game/${id}`) )

    }


    
}