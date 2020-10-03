package com.example.booksearchlist;


import com.example.booksearchlist.BookDatabase.Book;
import com.example.booksearchlist.BookDatabase.SearchBody;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JSONPlaceHolderApi {
    @GET("volumes")
    Call<SearchBody> getBooks(
            @Query("key") String key,
            @Query("q") String q);
}

