import { Injectable } from '@angular/core';
import {   Resolve,
    RouterStateSnapshot,
    ActivatedRouteSnapshot } from '@angular/router';
import { Observable } from 'rxjs';
import {Book} from "../model/book";
import {BooksService} from "../services/books.service";



@Injectable({providedIn: 'root'})
export class BookEditResolver implements Resolve<Book> {
    constructor(private readonly booksService: BooksService){

    }
    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Book> {
        return this.booksService.findBookById(route.params['id']);
    }
}
