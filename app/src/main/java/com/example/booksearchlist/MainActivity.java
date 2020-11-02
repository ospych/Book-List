package com.example.booksearchlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.booksearchlist.BookDatabase.Book;
import com.example.booksearchlist.BookDatabase.SearchBody;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerAdapter adapter;

    JSONPlaceHolderApi placeHolderApi = NetworkService.getInstance().create(JSONPlaceHolderApi.class);

    RecyclerAdapter.OnItemClickListener clickListener = new RecyclerAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(Book book) {
            String url = book.volumeInfo.infoLink;
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void searchButton(View view) {
        TextView results = findViewById(R.id.results);
        results.setVisibility(View.VISIBLE);

        EditText editText = findViewById(R.id.searchBox);
        String query = editText.getText().toString();

        RecyclerView recyclerView = findViewById(R.id.list_recycler);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerAdapter(clickListener);
        recyclerView.setAdapter(adapter);

        String key = "AIzaSyDhnCClfV8V92fimXFYocWCz_ETTE4Y0_k";
        placeHolderApi.getBooks(key, query)
                .enqueue(new Callback<SearchBody>() {
                    @Override
                    public void onResponse(@NotNull Call<SearchBody> call, @NotNull Response<SearchBody> response) {
                        try {
                            if (response.body() != null){
                                adapter.setData(response.body().allBooks);
                            } else {
                                Toast.makeText(MainActivity.this, "Enter book's name", Toast.LENGTH_SHORT).show();
                                TextView noBook = findViewById(R.id.empty_view);
                                noBook.setText("Can't find any book");
                            }
                        } catch (NullPointerException e) {
                            Toast.makeText(MainActivity.this, "Enter the book", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<SearchBody> call, @NotNull Throwable t) {
                        TextView noConnection = findViewById(R.id.empty_view);
                        noConnection.setText(R.string.no_internet);
                    }
                });
        //Use for hide keyboard
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        editText.setText("");
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter = new RecyclerAdapter(clickListener);
        adapter.clear();
    }
}