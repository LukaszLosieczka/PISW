import { Injectable } from '@angular/core';
import {
  Router, Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';
import { Observable, of } from 'rxjs';
import {Review} from "../model/review";
import {ReviewService} from "../services/review.service";

@Injectable({
  providedIn: 'root'
})
export class BookReviewResolver implements Resolve<Review[]> {

  constructor(private readonly reviewService: ReviewService) {
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Review[]> {
    const bookId = route.paramMap.get('id')!;
    return this.reviewService.getAllReviewsForBook(bookId);
  }
}
