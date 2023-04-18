import { Component, OnInit } from '@angular/core';
import {Book} from "../../model/book";
import {ActivatedRoute} from "@angular/router";
import {Review} from "../../model/review";

@Component({
  selector: 'bs-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.scss']
})
export class BookDetailsComponent{

  readonly book: Book;
  readonly reviews: Review[]

  constructor(private activatedRoute: ActivatedRoute) {
    this.book = this.activatedRoute.snapshot.data['book'];
    this.reviews = this.activatedRoute.snapshot.data['reviews'];
  }

}
