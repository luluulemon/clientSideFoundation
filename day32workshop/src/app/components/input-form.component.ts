import { Component, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { Task } from '../model';

@Component({
  selector: 'app-input-form',
  templateUrl: './input-form.component.html',
  styleUrls: ['./input-form.component.css']
})
export class InputFormComponent implements OnInit {

  @Output()
  onAddTask = new Subject<Task>()

  form!: FormGroup
  todayDate: Date = new Date()
  validDate: boolean = false

  constructor(private fb: FormBuilder){}

  ngOnInit(){
    this.form = this.createForm()
  }

  createForm(): FormGroup{
    return this.fb.group( {
      description: this.fb.control<string>('', [Validators.required, Validators.minLength(5)]),
      priority: this.fb.control<string>('', [Validators.required]),
      dueDate: this.fb.control<string>("", [Validators.required])
    } )
  }

  addTask(){
    const task = this.form.value
    console.info(task)
    this.onAddTask.next(task)
    this.form = this.createForm()
  }

  checkDate(){
    const today = this.todayDate.setDate(this.todayDate.getDate()-1)
    const selected = Date.parse(this.form.value['dueDate'])

    this.validDate = selected > today 
    console.info("check date input", this.form.value["dueDate"])
    console.info("check today Date", today)
    console.info("check validDate", this.validDate)
  }
}
