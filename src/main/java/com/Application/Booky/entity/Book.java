package com.Application.Booky.entity;

import com.Application.Booky.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Book extends BaseEntity {



    private String title;
    private String authorName;
    private String isbn;
    private String synopsis;
    private String bookCover;
    private boolean archived;
    private boolean shareable;








    // User can own many books, a book can be owned by one user
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "book") // One book can have many feedbacks
    private List<feedback> feedbacks; // Ensure correct naming conventions

    @OneToMany(mappedBy = "book") // One book can have many transactions
    private List<BookTransactionHistory> histories; // Ensure correct naming conventions
}
