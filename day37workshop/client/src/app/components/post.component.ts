import { HttpClient } from '@angular/common/http';
import { Component, ElementRef, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { lastValueFrom } from 'rxjs';
import { uploadResponse } from '../model';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent {

  form!: FormGroup
  @ViewChild('uploadFile')
  imageFile!: ElementRef
  
  postResponse: string = ''

  constructor(private fb: FormBuilder, private httpSvc:HttpClient){}

  ngOnInit(){
    this.form = this.createForm()
  }

  createForm(): FormGroup{
    return this.fb.group({
      image: this.fb.control(''),
      comments: this.fb.control<string>('')
    })
  }

  uploadPost(){
    const formData = new FormData()
    formData.set('name', this.form.value.image)
    formData.set('image', this.imageFile.nativeElement.files[0])
    formData.set('comments', this.form.value.comments)

    lastValueFrom(
      this.httpSvc.post<any>('/api/post', formData)
    ).then(v => { console.info(v)
                  this.postResponse = v.id
                  //let response:uploadResponse = { id: v.id  }
                }
    )
    .catch(error => console.error('error: ', error))

    this.form = this.createForm()
  }


}
