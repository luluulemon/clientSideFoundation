import { Component, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'day35workshop';
  recSizeOption = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15]
  form! : FormGroup
  viewPerPage: number = 10
  testNum: number = 0


  constructor(private fb:FormBuilder){}
  
  ngOnInit(){
    this.form = this.createForm()
  }

  private createForm(): FormGroup {
    return this.fb.group(
      { noOfRec: this.fb.control<number>(10, [Validators.required] ) }
    )
  }

  getNum(){
    console.info("check input", this.form.value)
    this.viewPerPage = this.form.value['noOfRec']
    console.info("Check viewPerPage", this.viewPerPage)
  }


}
