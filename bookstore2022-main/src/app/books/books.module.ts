import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BookListComponent } from './components/book-list/book-list.component';
import {HttpClientModule} from "@angular/common/http";
import {RouterModule} from "@angular/router";
import { BookDetailsComponent } from './components/book-details/book-details.component';
import { BookReviewComponent } from './components/book-review/book-review.component';
import { ReviewFormComponent } from './components/review-form/review-form.component';
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    BookListComponent,
    BookDetailsComponent,
    BookReviewComponent,
    ReviewFormComponent
  ],
    imports: [
        CommonModule,
        HttpClientModule,
        RouterModule,
        FormsModule
    ]
})
export class BooksModule { }
