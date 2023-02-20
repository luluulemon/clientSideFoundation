import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SellerformComponent } from './components/sellerform.component';
import { ConfirmationComponent } from './components/confirmation.component';
import { PostIDComponent } from './components/post-id.component';
import { ReactiveFormsModule } from '@angular/forms';

import { Routes, RouterModule } from '@angular/router'
import { HttpClientModule } from '@angular/common/http'

const appRoutes:Routes = [ 
  { path: '', component: SellerformComponent },
  { path: 'confirmation/:imageId', component: ConfirmationComponent }, 
  { path: 'confirmation', component: ConfirmationComponent },
  { path: 'postid', component: PostIDComponent },
  { path: 'postid/:Id', component: PostIDComponent },
  { path: '**', redirectTo: '/', pathMatch: 'full' }
]

@NgModule({
  declarations: [
    AppComponent,
    SellerformComponent,
    ConfirmationComponent,
    PostIDComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes, {useHash:true})
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
