import { HttpClient } from '@angular/common/http';
import { Component, ElementRef, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { firstValueFrom } from 'rxjs';
import { formText, imageKey, ObjIDString } from '../model';
import { SavePostService } from '../services/save-post.service';

@Component({
  selector: 'app-newpost',
  templateUrl: './newpost.component.html',
  styleUrls: ['./newpost.component.css']
})
export class NewpostComponent {

  form!: FormGroup
  @ViewChild('file')
  imageFile!: ElementRef

  imageString: string = ''
  objID: string = ''
  textData: formText = { title:"", text:"", id:""}
  newID: ObjIDString = { id: "" }

  constructor(private fb:FormBuilder, private http:HttpClient, private saveSvc:SavePostService){}

  ngOnInit():void { this.form = this.createForm() }

  createForm(): FormGroup {
    return this.fb.group( {
      picture: this.fb.control(''),
      title: this.fb.control<string>(''),
      text: this.fb.control<string>('')
    })
  }

  addPost(){

    this.textData.title = this.form.value['title']
    this.textData.text = this.form.value['text']

    const formData = new FormData(); 
    formData.set('name', this.form.get('picture')?.value);  // use ? in case form.get() is null
    formData.set('file', this.imageFile.nativeElement.files[0]);  // check this.imageFile

    this.saveSvc.savePic(this.textData)
                .then( (v) => 
                {
                    this.newID = v as ObjIDString
                    formData.set('id', this.newID.id)
                    firstValueFrom( 
                      this.http.post('http://localhost:8080/newPost', formData)
                    ).then((v) => {   
                        const a = v as imageKey
                        console.info(v)
                          this.imageString = `https://lufirstbucket.sgp1.digitaloceanspaces.com/myobjects/${a['key']}`          
                      }) 
                      .catch((error) => { console.info('error in POST:', error) }) 
                })
                .catch((error) => { console.info(error)})
                

    // firstValueFrom(this.http.post("http://localhost:8080/newPostText", this.textData))
    //           .then((v) => {  this.newID = v as ObjIDString
    //                           this.objID = newID.id
    //                           console.info('inside then check obj:', this.objID)   })
    //           .catch(error => { console.info('error: ', error)  } )  

    

    // send image
    // firstValueFrom( 
    //   this.http.post('http://localhost:8080/newPost', formData)
    // ).then((v) => {   
    //     const a = v as imageKey
    //     console.info(v)
    //       this.imageString = `https://lufirstbucket.sgp1.digitaloceanspaces.com/myobjects/${a['key']}`          
    //   }) 
    //   .catch((error) => { console.info('error in POST:', error) }) 
    
  }
}
