import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Transacao } from 'src/app/models/transacao';
import { TransacoesService } from 'src/app/services/transacoes.service';

@Component({
  selector: 'app-list-transacoes',
  templateUrl: './list-transacoes.component.html',
  styleUrls: ['./list-transacoes.component.css']
})
export class ListTransacoesComponent implements OnInit {

  transacao = {} as Transacao;
  transacoes: Transacao[];

  constructor(private route: ActivatedRoute, private transacoesService: TransacoesService) { }

  ngOnInit() {
    this.getTransacoes();
  }

  getTransacoes() {
    const routeParams = this.route.snapshot.paramMap;
    const contaIdFromRoute = Number(routeParams.get("contaId"));

    this.transacoesService.getTransacoes(contaIdFromRoute).subscribe((transacoes: Transacao[]) => {
      this.setSaldoAnterior(transacoes);
      this.transacoes = transacoes;
    });
  }


  private setSaldoAnterior(transacoes: Transacao[]) {
    
    for (var i = 0; i < transacoes.length; i++) {
      
      const transacaoAnterior = transacoes[i - 1];
      
      if (i == 0) {
        transacoes[i].saldoAnterior = 0;
      } else {
        transacoes[i].saldoAnterior = transacaoAnterior.valorTransacao + transacaoAnterior.saldoAnterior;
      }
    }
  }
}
