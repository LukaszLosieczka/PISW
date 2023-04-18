import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Review} from "../model/review";

const reviewsApiPrefix = '/api/reviews';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  constructor(private readonly http: HttpClient) { }

  getAllReviewsForBook(id: String): Observable<Review[]>{
    const url = `${reviewsApiPrefix}?forBook=${id}`;
    return this.http.get<Review[]>(url);
  }
}
