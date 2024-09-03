package com.Application.Booky.controller;


import com.Application.Booky.common.PageResponse;
import com.Application.Booky.service.BookRequest;
import com.Application.Booky.service.BookResponse;
import com.Application.Booky.service.BookService;
import com.Application.Booky.service.BorrowedBookResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@Tag(name="Book")

public class BookController {


    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }


    @PostMapping("/")
    public ResponseEntity<Integer> saveBook(

            @Valid @RequestBody BookRequest request,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(service.save(request, connectedUser));



    }

    // find book by id

    @GetMapping("/{book-id}")
    public ResponseEntity<BookResponse> findBookById(@PathVariable("book-id") Integer bookId)
    {
        return ResponseEntity.ok(service.findbyId(bookId));
    }


    // return all books ,with paging functionality
    @GetMapping
    public ResponseEntity<PageResponse<BookResponse>> findAllBooks(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(service.findAllBooks(page, size, connectedUser));
    }



    // find all the borrowed books by the user


    @GetMapping("/borrowed")
    public ResponseEntity<PageResponse<BorrowedBookResponse>> findAllBorrowedBooks(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(service.findAllBorrowedBooks(page, size, connectedUser));
    }


    @GetMapping("/returned")
    public ResponseEntity<PageResponse<BorrowedBookResponse>> findAllReturnedBooks(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(service.findAllReturnedBooks(page, size, connectedUser));
    }


    // update shareable status

    @PatchMapping("/shareable/{book-id}")
    public ResponseEntity<Integer> updateShareableStatus(
            @PathVariable("book-id") Integer bookId,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(service.updateShareableStatus(bookId, connectedUser));
    }


    // update archived status

    @PatchMapping("/archived/{book-id}")
    public ResponseEntity<Integer> updateArchivedtatus(
            @PathVariable("book-id") Integer bookId,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(service.updateArchivedStatus(bookId, connectedUser));
    }


    // borrow a book

    @PostMapping("borrow/{book-id}")
    public ResponseEntity<Integer> borrowBook(
            @PathVariable("book-id") Integer bookId,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(service.borrowBook(bookId, connectedUser));
    }



    // return a book

    @PatchMapping("borrow/return/{book-id}")
    public ResponseEntity<Integer> returnBorrowBook(
            @PathVariable("book-id") Integer bookId,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(service.returnBorrowedBook(bookId, connectedUser));

    }

         // debug
    @PatchMapping("borrow/return/approve/{book-id}")
    public ResponseEntity<Integer> approveReturnBorrowBook(
            @PathVariable("book-id") Integer bookId,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(service.approveReturnBorrowedBook(bookId, connectedUser));
    }




}
