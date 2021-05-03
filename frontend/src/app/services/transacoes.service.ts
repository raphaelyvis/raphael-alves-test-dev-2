import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Transacao } from '../models/transacao';

@Injectable({
  providedIn: 'root'
})
export class TransacoesService {
  
  constructor(private http: HttpClient) { }
  
  getTransacoes(contaId: number): Observable<Transacao[]> {
    let transacoesUrl = `http://localhost:8080/contas/${contaId}/transacoes`;

    return this.http.get<Transacao[]>(transacoesUrl).pipe(retry(2), catchError(this.handleError))
  }

  handleError(error: HttpErrorResponse) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      errorMessage = error.error.message;
    } else {
      errorMessage = `Código do erro: ${error.status}, ` + `mensagem: ${error.message}`;
    }
    console.log(errorMessage);
    return throwError(errorMessage);
  };
}
