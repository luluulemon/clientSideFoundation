import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { HttpClientModule } from '@angular/common/http'
import { Routes, RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NewpostComponent } from './components/newpost.component';
import { MainpageComponent } from './components/mainpage.component'

const appRoutes: Routes = [ { path: '', component: MainpageComponent },
                           { path: 'addPost', component: NewpostComponent }, 
                           { path: '**', redirectTo: '/', pathMatch: 'full' }] 

@NgModule({
  declarations: [
    AppComponent,
    NewpostComponent,
    MainpageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes, {useHash: true} )]
  ,
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
