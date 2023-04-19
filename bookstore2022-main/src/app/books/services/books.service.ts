import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Book } from '../model/book';

const booksApiPrefix = '/api/books';

@Injectable({
  providedIn: 'root'
})
export class BooksService {

  constructor(private readonly http: HttpClient) { }

  getAllBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(booksApiPrefix);
  }

  findBookById(id: String): Observable<Book>{
    const url = `${booksApiPrefix}/${id}`;
    return this.http.get<Book>(url);
  }

  editBook(id: number, book: Book): Observable<Book> {
    return this.http.put<Book>(`${booksApiPrefix}/${id}`, book);
  }
}
