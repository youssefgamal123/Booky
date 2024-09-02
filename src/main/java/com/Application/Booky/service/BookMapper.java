package com.Application.Booky.service;


import com.Application.Booky.entity.Book;
import com.Application.Booky.entity.BookTransactionHistory;
import com.Application.Booky.entity.User;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {


    public Book toBook(BookRequest request , User user) {


        return new Book(
                request.title(),
                request.authorName(),
                request.Shareable(),
                user

        ) ;




    }

    public BookResponse toBookResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .authorName(book.getAuthorName())
                .isbn(book.getIsbn())
                .synopsis(book.getSynopsis())
                .archived(book.isArchived())
                .shareable(book.isShareable())
                .owner(book.getOwner().getFirstname())
                .build();
    }


    public BorrowedBookResponse toBorrowedBookResponse(BookTransactionHistory history) {
        return BorrowedBookResponse.builder()
                .id(history.getBook().getId())
                .title(history.getBook().getTitle())
                .authorName(history.getBook().getAuthorName())
                .isbn(history.getBook().getIsbn())
                //.returned(history.isReturned())
                .returnApproved(history.isReturnApproved())
                .build();
    }



}
