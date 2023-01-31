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

  constructor(private fb: FormBuilder){}

  ngOnInit(){
    this.form = this.createForm()
  }

  createForm(): FormGroup{
    return this.fb.group( {
      description: this.fb.control<string>('', [Validators.required, Validators.minLength(5)]),
      priority: this.fb.control<string>('', [Validators.required]),
      dueDate: this.fb.control<Date>(new Date, [Validators.required])
    } )
  }

  addTask(){
    const task = this.form.value
    console.info(task)
    this.onAddTask.next(task)
  }
}
