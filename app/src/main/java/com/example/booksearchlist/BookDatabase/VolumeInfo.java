package com.example.booksearchlist.BookDatabase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VolumeInfo {

    @SerializedName("title")
    @Expose
    public String title;

    @SerializedName("subtitle")
    @Expose
    public String subtitle;

    @SerializedName("authors")
    @Expose
    public List<String> author;

    @SerializedName("averageRating")
    @Expose
    public String averageRating;

    @SerializedName("publishedDate")
    @Expose
    public String publishedDate;

    @SerializedName("pageCount")
    @Expose
    public String pageCount;

    @SerializedName("imageLinks")
    @Expose
    public ImageLinks imageLink;

    @SerializedName("infoLink")
    @Expose
    public String infoLink;

}
