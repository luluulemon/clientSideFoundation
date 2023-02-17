import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeroListComponent } from './components/hero-list.component';
import { HeroComponent } from './components/hero.component';

import { RouterModule, Routes } from '@angular/router';
import { SearchComponent } from './components/search.component'
import { HttpClientModule } from '@angular/common/http';
import { CommentComponent } from './components/comment.component'
import { Comment } from '@angular/compiler';

const appRoutes: Routes = [
  { path: 'list/:search', component: HeroListComponent },
  { path: '', component: SearchComponent},
  { path: 'hero/:id/:searchChar', component: HeroComponent },
  { path: 'hero/:id', component: HeroComponent },
  { path: 'comments/:id/:name/:searchChar', component: CommentComponent }
]

@NgModule({
  declarations: [
    AppComponent,
    HeroListComponent,
    HeroComponent,
    SearchComponent,
    CommentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    RouterModule.forRoot(appRoutes, {useHash: true} ),
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
