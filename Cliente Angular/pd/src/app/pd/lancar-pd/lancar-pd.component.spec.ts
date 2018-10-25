import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LancarPdComponent } from './lancar-pd.component';

describe('LancarPdComponent', () => {
  let component: LancarPdComponent;
  let fixture: ComponentFixture<LancarPdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LancarPdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LancarPdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
