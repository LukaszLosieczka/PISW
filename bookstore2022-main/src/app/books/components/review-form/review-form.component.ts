import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Review} from "../../model/review";
import {ReviewService} from "../../services/review.service";
import {ReviewDto} from "../../model/reviewDto";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'bs-review-form',
  templateUrl: './review-form.component.html',
  styleUrls: ['./review-form.component.scss']
})
export class ReviewFormComponent{
  review: ReviewDto;
  // @ts-ignore
  @Input() bookId: number;
  @Output("updateReviews") updateReviews: EventEmitter<any> = new EventEmitter();

  constructor(private reviewService: ReviewService) {
    this.review = {
      forBook: -1,
      title: '',
      description: '',
      rate: 5,
    };
  }
  onSubmit(form: NgForm) {
    if(form.valid) {
      this.review.forBook = this.bookId;
      this.reviewService.saveReview(this.review).subscribe(error => console.log(error));
      this.updateReviews.emit();
      form.resetForm()
    }
  }

}
