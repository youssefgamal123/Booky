package com.Application.Booky.entity;


import com.Application.Booky.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class feedback extends BaseEntity {


    private Double Score;
    private String Comment;


    @ManyToOne // many feedbacks to one book
    @JoinColumn(name = "book_id")
    private Book book;





}
