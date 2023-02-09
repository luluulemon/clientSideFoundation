import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import Dexie from 'dexie';
import { lastValueFrom } from 'rxjs';
import { Contact } from '../models/contact';


@Injectable({
  providedIn: 'root'
})
export class ContactRepoService extends Dexie {

  contact!: Dexie.Table<Contact, string>

  constructor( private http:HttpClient) { 
    super('ContactsDB')
    this.version(1).stores({
      contact: 'email'
    })
    this.contact = this.table('contact')
  }

  addContact(contact:Contact): Promise<string>{
    return this.contact.add(contact, contact.email)
  }

  getAllContacts(): Promise<Contact[]>{
    return this.contact.toArray()
  }

  deleteAllContacts(): Promise<void>{
    return this.contact.clear()
  }

  


}
