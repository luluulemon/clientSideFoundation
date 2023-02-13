import { Component } from '@angular/core';
import { map } from 'rxjs';
import { formText } from '../model';
import { RetrievePostService } from '../services/retrieve-post.service';

@Component({
  selector: 'app-mainpage',
  templateUrl: './mainpage.component.html',
  styleUrls: ['./mainpage.component.css']
})
export class MainpageComponent {

  allTexts: formText[] = []


  constructor(private retrieveSvc: RetrievePostService){}


  ngOnInit(): void {
    console.info('Does this fire off: OnInit')
    this.retrieveSvc.RetrievePic()
                .then(v => {this.allTexts = v })
                .catch(error => console.info('error: ', error))   
  }

  createUrl(id:string): String{ return "https://lufirstbucket.sgp1.digitaloceanspaces.com/myobjects/".concat(id)   }

  like(id:string){
    this.retrieveSvc.addLikes(id)
                .then(v => this.allTexts = v)
                .catch(error => console.info('error: ', error))
  }

  dislike(id:string){
    this.retrieveSvc.addDislikes(id)
              .then(v => this.allTexts = v)
              .catch(error => console.info('error: ', error))
  }

}
