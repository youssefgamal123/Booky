package com.Application.Booky.service;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponse {


    private  Integer id;
    private  String title;
    private  String authorName;
    // take care
    private  String isbn;

    private  String owner;
    private  String synopsis;;
    private byte[] cover;
    private boolean archived;
    private boolean shareable;



}
