import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SellerPost } from '../model';

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.css']
})
export class ConfirmationComponent {

  constructor(private activatedRoute:ActivatedRoute, private http:HttpClient){}

  sellerItem!:SellerPost | null
  image!:FormData
  imageId: string = ''

  ngOnInit(){
    const itemString = localStorage.getItem("item")
    if(itemString)
    { this.sellerItem = JSON.parse(itemString)  }

    //this.imageId = this.activatedRoute.snapshot.params['imageId']

    console.info('post: ', this.sellerItem)
    console.info('post: ', this.imageId)
  }
}
