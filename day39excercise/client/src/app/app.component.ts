import { Component } from '@angular/core';
import { formText } from './model';
import { RetrievePostService } from './services/retrieve-post.service';
import {DomSanitizer} from '@angular/platform-browser';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'client';

  allTexts: formText[] = []

  constructor(private retrieveSvc: RetrievePostService){}


  ngOnInit(): void {
    this.retrieveSvc.RetrievePic()
                .then(v => {this.allTexts = v })
                .catch(error => console.info('error: ', error))

    
  }

  createUrl(id:string): String{ return "https://lufirstbucket.sgp1.digitaloceanspaces.com/myobjects/".concat(id)   }
}
