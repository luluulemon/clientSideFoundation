import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-post-id',
  templateUrl: './post-id.component.html',
  styleUrls: ['./post-id.component.css']
})
export class PostIDComponent {

  constructor(private activatedRoute: ActivatedRoute){}

  id: string = ''

  ngOnInit(){
    this.id = this.activatedRoute.snapshot.params['Id']
  }
}
