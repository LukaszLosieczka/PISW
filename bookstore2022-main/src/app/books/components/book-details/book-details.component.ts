import {Component, EventEmitter, OnInit} from '@angular/core';
import {Book} from "../../model/book";
import {ActivatedRoute} from "@angular/router";
import {Review} from "../../model/review";
import {ReviewService} from "../../services/review.service";

@Component({
  selector: 'bs-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.scss'],
})
export class BookDetailsComponent{

  readonly book: Book;
  reviews: Review[]

  constructor(private activatedRoute: ActivatedRoute, private reviewService:ReviewService) {
    this.book = this.activatedRoute.snapshot.data['book'];
    this.reviews = this.activatedRoute.snapshot.data['reviews'];
    this.reviews.reverse()
  }

  updateReviews(){
    this.reviewService.getAllReviewsForBook(this.book.id.toString())
      .subscribe(data => {this.reviews = data; this.reviews.reverse()})
  }

}
