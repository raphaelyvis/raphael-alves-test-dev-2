import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Conta } from '../models/conta';

@Injectable({
  providedIn: 'root'
})
export class ContasService {

  contasUrl = 'http://localhost:8080/contas';

  constructor(private http: HttpClient) { }

  getContas(): Observable<Conta[]> {
    return this.http.get<Conta[]>(this.contasUrl).pipe(retry(2), catchError(this.handleError))
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
