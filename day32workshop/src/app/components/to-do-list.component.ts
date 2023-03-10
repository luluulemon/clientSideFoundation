import { Component, Input, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { Task } from '../model';

@Component({
  selector: 'app-to-do-list',
  templateUrl: './to-do-list.component.html',
  styleUrls: ['./to-do-list.component.css']
})
export class ToDoListComponent {

  form!: FormGroup 
  constructor(private fb: FormBuilder){}
  todayDate: Date = new Date


  @Input() TasksList!: Task[]
  @Input () editIndex!: boolean[]

  @Output() onDeleteTask = new Subject<number>()
  @Output() onEdit = new Subject<number>()
  @Output() onCancelEdit = new Subject<number>()
  @Output() onSaveEdit = new Subject<Task>()
  @Output() onCompleteTask = new Subject<number>()

  private createForm(task:Task): FormGroup {
    return this.fb.group( {
      description: this.fb.control<string>(task.description, [Validators.required]),
      priority: this.fb.control<string>(task.priority, [Validators.required]),
      dueDate: this.fb.control<Date>(task.dueDate, [Validators.required])
    })
  }

  deleteTask(i: number){
    console.info("to delete this: " , i)
    // output to main
    this.onDeleteTask.next(i)
  }

  editTask(i: number){
    console.info("to edit this: ", i)
    this.onEdit.next(i)
    this.form = this.createForm(this.TasksList[i])
  }

  cancelEdit(i:number){ this.onCancelEdit.next(i) }

  processEdit(i:number){  
    
    const editedTask = this.form.value as Task
    editedTask['editIndex'] = i
    console.info(this.form.value) 
    this.onCancelEdit.next(i)
    this.onSaveEdit.next(editedTask)
  }

  completeTask(i: number){ this.onCompleteTask.next(i) }
}
