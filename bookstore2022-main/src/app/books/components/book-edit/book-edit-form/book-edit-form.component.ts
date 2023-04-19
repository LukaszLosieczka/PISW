import { Component, OnInit, Input } from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms'
import { Router } from '@angular/router';
import {Book} from "../../../model/book";
import {BooksService} from "../../../services/books.service";

@Component({
  selector: 'bs-book-edit-form',
  templateUrl: './book-edit-form.component.html',
  styleUrls: ['./book-edit-form.component.scss']
})
export class BookEditFormComponent implements OnInit {

  form : any;
  submitted = false;
  // @ts-ignore
  @Input() book : Book;

  constructor(private router : Router, private booksService: BooksService){}

  ngOnInit(): void {
    this.form = new FormGroup({
      title: new FormControl(this.book.title,[Validators.required, Validators.maxLength(50)]),
      author: new FormControl(this.book.author,[Validators.required, Validators.maxLength(50)]),
      description: new FormControl(this.book.description,[Validators.maxLength(1000)]),
      year: new FormControl(this.book.year,[Validators.required, Validators.min(1000), Validators.max(2023)])
    })
  }

  get f() { return this.form.controls; }

  onSubmit() {
    this.submitted = true;
    if (this.form.invalid) {
        return;
    }
    this.booksService.editBook(this.book.id, this.book).subscribe(error => console.log(error));

    this.goBack();
  }

  goBack() {
    this.router.navigate(['/books']);
  }

}
