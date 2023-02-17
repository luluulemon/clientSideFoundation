import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Comments, Hero } from '../model';
import { GetInfoService } from '../services/get-info.service';
import { PostCommentService } from '../services/post-comment.service';

@Component({
  selector: 'app-hero',
  templateUrl: './hero.component.html',
  styleUrls: ['./hero.component.css']
})
export class HeroComponent {

  constructor(private router:Router, private commentSvc:PostCommentService,
      private activatedRoute:ActivatedRoute, private getSvc:GetInfoService){ }

  hero!: Hero
  commentsList: Comments[] = []
  searchChar: string = ''

  ngOnInit(){
    const id = this.activatedRoute.snapshot.params['id']
    this.searchChar = this.activatedRoute.snapshot.params['searchChar']
    this.getSvc.getHero(id)
              .then(h => {
                this.hero = h
                console.info('Check Hero: ', this.hero)

                console.info('check id:', id)
                this.commentSvc.getComment(this.hero.id)
                .then(c => this.commentsList = c)
                .catch(error => console.error('error: ', error))
              } )
              .catch(error => console.error('error: ', error))


  }

  addComment(hero:Hero){
    console.info('Check hero obj in hero component: ', hero)
    this.router.navigate(['/comments', hero.id, hero.name])
  }
}
