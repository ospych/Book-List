package com.example.booksearchlist.BookDatabase;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchBody {
    @SerializedName("items")
    public List<Book> allBooks;
}
