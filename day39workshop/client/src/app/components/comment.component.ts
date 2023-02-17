import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Comments, Hero } from '../model';
import { PostCommentService } from '../services/post-comment.service';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent {

  form!: FormGroup
  heroId!: number
  heroName!: string

  constructor(private fb:FormBuilder, private commentSvc: PostCommentService,
    private activatedRoute:ActivatedRoute, private router:Router){}

  ngOnInit(){
    this.createForm()
    this.heroId = this.activatedRoute.snapshot.params['id']
    this.heroName = this.activatedRoute.snapshot.params['name']
  }

  createForm(){
    this.form = this.fb.group({
      comments: this.fb.control<string>('', Validators.required)
    })
  }

  postComment(){
    console.info(this.form.value.comments)

    const comment: Comments = { id : Number(this.heroId), comments: this.form.value.comments }
  
    console.info('Check comment obj: ', comment)
    this.commentSvc.postComment(comment)
    this.router.navigate(['/hero', this.heroId])
  }
}
