import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { MainComponent } from './components/main.component';
import { OrdersComponent } from './components/orders.component';

import { Routes, RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http'
import { PizzaService } from './pizza.service';
import { TestComponent } from './components/test.component';

const appRoutes: Routes = [ 
  { path: '', component: MainComponent },
  { path: 'orders/:email', component: OrdersComponent },
  { path: 'orders', component:OrdersComponent },
  { path: '**', redirectTo: '/', pathMatch: 'full'}
]

@NgModule({
  declarations: [
    AppComponent, MainComponent, OrdersComponent, TestComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    RouterModule.forRoot(appRoutes, {useHash:true}),
    HttpClientModule
  ],

  providers: [ PizzaService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
