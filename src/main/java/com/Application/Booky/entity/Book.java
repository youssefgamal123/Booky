package com.Application.Booky.entity;

import com.Application.Booky.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Book extends BaseEntity {



    private String title;
    private String authorName;
    private String isbn;
    private String synopsis;
    private String bookCover;
    private boolean archived;
    private boolean shareable;

    public Book(String title, String authorName, boolean shareable,String isbn, String synopsis,String bookCover,boolean archived ,User owner) {
        this.title = title;
        this.authorName = authorName;
        this.shareable = shareable;
        this.owner = owner;
        this.isbn = isbn;
        this.synopsis=synopsis;
        this.bookCover=bookCover;
        this.archived=archived;

    }

    // User can own many books, a book can be owned by one user
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;


}
