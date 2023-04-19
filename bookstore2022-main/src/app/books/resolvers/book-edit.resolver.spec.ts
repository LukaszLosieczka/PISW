import { TestBed } from '@angular/core/testing';

import { BookEditResolver } from './book-edit.resolver';
import {BooksService} from "../../../../../../../../Desktop/Semestr 8/Projekt i implementacja systemów webowych/src/app/books/services/books.service";

describe('BookEditResolver', () => {
  let resolver: BookEditResolver;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        { provide: BooksService, useValue: {} }
      ]
    });
    resolver = TestBed.inject(BookEditResolver);
  });

  it('should be created', () => {
    expect(resolver).toBeTruthy();
  });
});
