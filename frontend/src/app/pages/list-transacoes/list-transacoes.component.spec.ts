import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListTransacoesComponent } from './list-transacoes.component';

describe('ListTransacoesComponent', () => {
  let component: ListTransacoesComponent;
  let fixture: ComponentFixture<ListTransacoesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListTransacoesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListTransacoesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
