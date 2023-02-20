import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SellerPost } from '../model';
import { PostItemService } from '../services/post-item.service';

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.css']
})
export class ConfirmationComponent {

  constructor(private activatedRoute:ActivatedRoute, 
            private postSvc:PostItemService, private router: Router){}

  sellerItem!:SellerPost | null
  image!:FormData
  imageId: string = ''

  ngOnInit(){
    
    this.sellerItem = this.postSvc.item
    console.info('Check sellerItem: ', this.sellerItem)

    // const itemString = localStorage.getItem("item")
    // if(itemString)
    // { 
    //   this.sellerItem = JSON.parse(itemString)  
    //   console.info('Check confirmation: ', this.sellerItem)}

  }

  confirmPost(id:string){
    this.postSvc.confirm(id)
              .then(v => 
                      { console.info(v)
                        this.router.navigate(['postid', this.sellerItem?.id])
                      }
                    )
              .catch(error =>{
                  alert("Did not manage to save!")
                  console.error('error: ', error)})

    
  }
}
