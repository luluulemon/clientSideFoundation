import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Contact } from '../models/contact';
import { ContactRepoService } from '../services/contact-repo.service';
import { syncRepoService } from '../services/syncrepo.service';

@Component({
  selector: 'app-contact-form',
  templateUrl: './contact-form.component.html',
  styleUrls: ['./contact-form.component.css']
})
export class ContactFormComponent {

  form!: FormGroup
  contactsList: Contact[] = []
  DBList: Contact[] = []

  constructor(private fb: FormBuilder, 
    private repoSvc: ContactRepoService, private syncSvc:syncRepoService){}

  ngOnInit():void {
    this.form = this.fb.group({
      name: this.fb.control<string>(''),
      email: this.fb.control<string>('')
    })

    this.repoSvc.getAllContacts()
            .then(v => this.contactsList=v)
            .catch( error => console.error('error :',error))
  }

  addContact(){
    const newContact: Contact = this.form.value as Contact
    console.info('check form value : ', newContact)
    this.repoSvc.addContact(newContact)
                .then( v => console.info('Add contact : ', v))
                .catch( error => console.error('error :',error))

    this.ngOnInit()
  }

  syncContacts(){
    this.syncSvc.syncWithDB(this.contactsList)
    console.info('contactsList: ', this.contactsList)

    this.repoSvc.deleteAllContacts()
            .then( v => console.info('Delete contacts : ', v))
            .catch( error => console.error('error :',error))
    
    this.ngOnInit()
  }

  fetchAll(){
    this.syncSvc.fetchFromDB()
              .then( contacts => this.DBList = contacts)
              .catch(error => console.error('error: ', error))
  }
}
