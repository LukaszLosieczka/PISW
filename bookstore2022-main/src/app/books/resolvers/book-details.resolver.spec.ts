import { TestBed } from '@angular/core/testing';

import { BookDetailsResolver } from './book-details.resolver';

describe('BookDetailsResolver', () => {
  let resolver: BookDetailsResolver;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    resolver = TestBed.inject(BookDetailsResolver);
  });

  it('should be created', () => {
    expect(resolver).toBeTruthy();
  });
});
