import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { lastValueFrom } from "rxjs";
import { Contact } from "../models/contact";

@Injectable({
    providedIn: 'root'
})

export class syncRepoService{

    constructor(private httpSvc: HttpClient){}

    syncWithDB(contactsList: Contact[]){

        lastValueFrom( this.httpSvc.post("/syncContacts", contactsList) )
                    .then(v => console.info(v))
                    .catch(error => console.error('error: ', error))
        console.info("inside Service")
    }


    fetchFromDB():Promise<Contact[]>{

        return lastValueFrom( this.httpSvc.get<Contact[]>("/fetchAll") )
    }

    

}