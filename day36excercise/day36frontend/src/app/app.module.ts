import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MasterComponent } from './components/master.component';
import { DetailsComponent } from './components/details.component';
import { RouterModule, Routes } from '@angular/router' 
import { GameService } from './services/GameService';

const appRoutes: Routes = [
  { path: "", component: MasterComponent },
  { path: 'game/:gameId', component: DetailsComponent },
  { path: '**', redirectTo: '/', pathMatch: 'full'},
]


@NgModule({
  declarations: [
    AppComponent,
    MasterComponent,
    DetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes, { useHash : true })
  ],
  providers: [GameService],
  bootstrap: [AppComponent]
})
export class AppModule { }
