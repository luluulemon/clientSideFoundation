import { Component } from '@angular/core';
import { Task } from './model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'day32workshop';

  Tasks: Task[] = []

  addTask(task: Task){
    this.Tasks.push(task)
  }

  deleteTask(i: number){
    this.Tasks.splice(i,1)
  }
}
