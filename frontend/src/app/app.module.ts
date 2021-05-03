import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListContasComponent } from './pages/list-contas/list-contas.component';
import { ListTransacoesComponent } from './pages/list-transacoes/list-transacoes.component';


@NgModule({
  declarations: [
    AppComponent,
    ListContasComponent,
    ListTransacoesComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    RouterModule.forRoot([
      { path: "", component: ListContasComponent },
      { path: "transacoes/:contaId", component: ListTransacoesComponent }
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
