import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BookListComponent } from './components/book-list/book-list.component';
import {HttpClientModule} from "@angular/common/http";
import {RouterModule} from "@angular/router";
import { BookDetailsComponent } from './components/book-details/book-details.component';
import { BookReviewComponent } from './components/book-review/book-review.component';

@NgModule({
  declarations: [
    BookListComponent,
    BookDetailsComponent,
    BookReviewComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    RouterModule
  ]
})
export class BooksModule { }
