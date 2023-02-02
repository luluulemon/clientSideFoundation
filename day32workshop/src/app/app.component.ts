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
  completedTasks: Task[] = []
  edits: boolean[] = []

  ngOnInit(): void{
    const JSONstring = localStorage.getItem("tasks")
    const editsJSONstring = localStorage.getItem("edits")
    const completesJSONstring = localStorage.getItem("completes")
    if(JSONstring)   // if not an empty string
    { this.Tasks = JSON.parse(JSONstring)    }
    if(editsJSONstring){ this.edits = JSON.parse(editsJSONstring) }
    if(completesJSONstring){ this.completedTasks = JSON.parse(completesJSONstring) }
  }

  addTask(task: Task){
    this.Tasks.push(task)
    this.edits.push(false)
    const savedTasks = JSON.stringify(this.Tasks)
    localStorage.setItem("tasks", savedTasks)
    const savedBooleans = JSON.stringify(this.edits)
    localStorage.setItem("edits", savedBooleans)
  }

  deleteTask(i: number){
    this.Tasks.splice(i,1)
  }

  editTask(i: number){  this.edits[i] = true  }
  cancelEdit(i: number){  this.edits[i] = false }
  saveEdit(task: Task){
    const editIndex = task['editIndex'] as number
    this.Tasks[editIndex] = task
    const savedTasks = JSON.stringify(this.Tasks)
    localStorage.setItem("tasks", savedTasks)
  }

  completeTask(i: number){  
    const completed = this.Tasks.splice(i,1) as Task[]
    this.completedTasks.push(completed[0])
    const savedCompletes = JSON.stringify(this.completedTasks)
    localStorage.setItem("completes", savedCompletes)
    const savedTasks = JSON.stringify(this.Tasks)
    localStorage.setItem("tasks", savedTasks)
  }
}

// date validation,