import { TestBed, inject } from '@angular/core/testing';

import { PdService } from './pd.service';

describe('PdService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PdService]
    });
  });

  it('should be created', inject([PdService], (service: PdService) => {
    expect(service).toBeTruthy();
  }));
});
