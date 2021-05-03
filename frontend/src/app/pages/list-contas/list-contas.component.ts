import { Component, OnInit } from '@angular/core';
import { Conta } from 'src/app/models/conta';
import { ContasService } from 'src/app/services/contas.service';

@Component({
  selector: 'app-list-contas',
  templateUrl: './list-contas.component.html',
  styleUrls: ['./list-contas.component.css']
})
export class ListContasComponent implements OnInit {

  contas: Conta[];

  constructor(private contasService: ContasService) {}

  ngOnInit() {
    this.getContas();
  }

  getContas() {
    this.contasService.getContas().subscribe((contas: Conta[]) => {
      this.contas = contas;
    });
  }

}
