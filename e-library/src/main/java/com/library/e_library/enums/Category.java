package com.library.e_library.enums;

public enum Category{
    FICTION("Fiction"),
    NON_FICTION("Non_Fiction");

    private final String category;
    Category(String category)
    {
        this.category=category;
    }
}