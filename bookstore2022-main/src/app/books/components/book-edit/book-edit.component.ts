import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BookEditFormComponent } from './book-edit-form/book-edit-form.component';
import {Book} from "../../model/book";

@Component({
  selector: 'bs-book-edit',
  templateUrl: './book-edit.component.html',
  styleUrls: ['./book-edit.component.scss']
})
export class BookEditComponent implements OnInit {

  readonly book: Book;

  @ViewChild(BookEditFormComponent)
    // @ts-ignore
  bookEditFormComponent: BookEditFormComponent;

  constructor(private readonly activatedRoute: ActivatedRoute) {
    this.book = this.activatedRoute.snapshot.data['book'];
  }

  ngOnInit(): void {
  }

}
