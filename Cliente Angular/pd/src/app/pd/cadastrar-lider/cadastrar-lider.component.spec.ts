import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastrarLiderComponent } from './cadastrar-lider.component';

describe('CadastrarLiderComponent', () => {
  let component: CadastrarLiderComponent;
  let fixture: ComponentFixture<CadastrarLiderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CadastrarLiderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CadastrarLiderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
