import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookListComponent } from "./books/components/book-list/book-list.component";
import { BookListResolver } from "./books/resolvers/book-list.resolver";
import {BookDetailsComponent} from "./books/components/book-details/book-details.component";
import {BookDetailsResolver} from "./books/resolvers/book-details.resolver";
import {BookReviewResolver} from "./books/resolvers/book-review.resolver";
import {BookEditComponent} from "./books/components/book-edit/book-edit.component";
import {BookEditResolver} from "./books/resolvers/book-edit.resolver";

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: '/books'
  },
  {
    path: 'books',
    component: BookListComponent,
    resolve: {
      books: BookListResolver
    }
  },
  {
    path: 'books/:id/reviews',
    component: BookDetailsComponent,
    resolve: {
      book: BookDetailsResolver,
      reviews: BookReviewResolver
    }
  },
  {
    path: 'books/:id/edit',
    pathMatch: 'full',
    component: BookEditComponent,
    resolve: {
      book: BookEditResolver
    }
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
