import { TestBed } from '@angular/core/testing';

import { BookReviewResolver } from './book-review.resolver';

describe('BookReviewResolver', () => {
  let resolver: BookReviewResolver;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    resolver = TestBed.inject(BookReviewResolver);
  });

  it('should be created', () => {
    expect(resolver).toBeTruthy();
  });
});
