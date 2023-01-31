import { Component, Input, Output } from '@angular/core';
import { Subject } from 'rxjs';
import { Task } from '../model';

@Component({
  selector: 'app-to-do-list',
  templateUrl: './to-do-list.component.html',
  styleUrls: ['./to-do-list.component.css']
})
export class ToDoListComponent {

  @Input() TasksList!: Task[]
  @Output() onDeleteTask = new Subject<number>()

  deleteTask(i: number){
    console.info("to delete this: " , i)
    // output to main
    this.onDeleteTask.next(i)
  }
}
