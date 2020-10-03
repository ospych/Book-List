package com.example.booksearchlist.BookDatabase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Book {
    @SerializedName("volumeInfo")
    @Expose
    public VolumeInfo volumeInfo;
}
