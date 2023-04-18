import {Component, Input, OnInit} from '@angular/core';
import {Review} from "../../model/review";

@Component({
  selector: 'bs-book-review',
  templateUrl: './book-review.component.html',
  styleUrls: ['./book-review.component.scss']
})
export class BookReviewComponent{
  // @ts-ignore
  @Input() review: Review;
}
