import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BookListComponent } from './components/book-list/book-list.component';
import {HttpClientModule} from "@angular/common/http";
import {RouterModule} from "@angular/router";
import { BookDetailsComponent } from './components/book-details/book-details.component';
import { BookReviewComponent } from './components/book-review/book-review.component';
import { ReviewFormComponent } from './components/review-form/review-form.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {BookEditComponent} from "./components/book-edit/book-edit.component";
import {BookEditFormComponent} from "./components/book-edit/book-edit-form/book-edit-form.component";

@NgModule({
  declarations: [
    BookListComponent,
    BookDetailsComponent,
    BookReviewComponent,
    ReviewFormComponent,
    BookEditComponent,
    BookEditFormComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class BooksModule { }
