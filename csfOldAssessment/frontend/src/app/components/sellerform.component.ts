import { HttpHeaders } from '@angular/common/http';
import { Component, ElementRef, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SellerPost } from '../model';
import { PostItemService } from '../services/post-item.service';

@Component({
  selector: 'app-sellerform',
  templateUrl: './sellerform.component.html',
  styleUrls: ['./sellerform.component.css']
})
export class SellerformComponent {

  @ViewChild('uploadFile')  // accessing file input
  imageFile!: ElementRef

  constructor(private fb: FormBuilder, private router:Router, private postSvc:PostItemService){}

  form!: FormGroup
  sellingItem!: SellerPost

  ngOnInit(){
    this.createForm()
  }

  createForm(){
    this.form = this.fb.group({
      name: this.fb.control<string>('', Validators.required),
      email: this.fb.control<string>('', Validators.required),
      phone: this.fb.control(''),
      title: this.fb.control<string>('', Validators.required),
      description: this.fb.control<string>('', Validators.required),
      image: this.fb.control('', Validators.required)
    })
  }

  postItem(){
    this.sellingItem = this.form.value
    console.info('check post: ', this.sellingItem)

    const formData = new FormData()
    //formData.set('name', this.form.value.image)
    formData.set('image', this.imageFile.nativeElement.files[0])
    formData.set('email', this.form.value.email)
    formData.set('phone', this.form.value.phone)
    formData.set('title', this.form.value.title)
    formData.set('description', this.form.value.description)
    formData.set('name', this.form.value.name)



    this.postSvc.postImage(formData)
                .then(v => {   
                  console.info('check post: ', v)
                  const JSONstring = JSON.stringify(this.sellingItem)
                  localStorage.setItem("item", JSONstring)})
                .catch(error => console.error('error: ', error))
    
    this.router.navigate(['/confirmation'])

  }


}
