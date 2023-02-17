import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {

  form!: FormGroup

  constructor(private fb: FormBuilder, private router:Router){}

  ngOnInit(){
    this.createForm()
  }

  createForm(){
    this.form = this.fb.group({
      charSearch: this.fb.control<string>('', Validators.required)
    })
  }

  searchCharacter(){
    const char = this.form.value.charSearch as string
    console.info('check input: ', char)
    this.router.navigate(['/list', char])
  }
}
