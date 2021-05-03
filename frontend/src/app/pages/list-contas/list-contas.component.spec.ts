import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListContasComponent } from './list-contas.component';

describe('ListContasComponent', () => {
  let component: ListContasComponent;
  let fixture: ComponentFixture<ListContasComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListContasComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListContasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
