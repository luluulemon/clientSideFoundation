import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { MainComponent } from './components/main.component';
import { OrdersComponent } from './components/orders.component';

import { Routes, RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http'

const appRoutes: Routes = [ 
  { path: '', component: MainComponent },
  { path: 'orders/:email', component: OrdersComponent },
  { path: '**', redirectTo: '/', pathMatch: 'full'},
]

@NgModule({
  declarations: [
    AppComponent, MainComponent, OrdersComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    RouterModule.forRoot(appRoutes, {useHash:true}),
    HttpClientModule
  ],

  providers: [ ],
  bootstrap: [AppComponent]
})
export class AppModule { }
