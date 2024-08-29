package com.Application.Booky.service;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record BookRequest(

        Integer id,

        @NotNull(message = "100")
        @NotEmpty(message = "100")
        String title,

        @NotNull(message = "101")
        @NotEmpty(message = "101")
        String authorName,


        Boolean Shareable



        )


{





}
