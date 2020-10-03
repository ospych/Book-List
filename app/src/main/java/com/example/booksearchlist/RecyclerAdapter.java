package com.example.booksearchlist;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.booksearchlist.BookDatabase.Book;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    public static List<Book> allBook;
    private static OnItemClickListener onItemClickListener;
    private static final String TAG = "myLog";

    public interface OnItemClickListener {
        void onItemClick(Book book);
    }


    public RecyclerAdapter(OnItemClickListener listener) {
        onItemClickListener = listener;
        allBook = new ArrayList<>();
    }

    @NonNull
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_book, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        try {
            Book book = allBook.get(position);
            holder.title.setText(book.volumeInfo.title);

            if (book.volumeInfo.author.size() > 1){
                holder.author.setText("Authors: " + book.volumeInfo.author.get(0) +", " + book.volumeInfo.author.get(1));
            } else {
                holder.author.setText("Authors: " + book.volumeInfo.author.get(0));
            }

            if (book.volumeInfo.subtitle == null || book.volumeInfo.subtitle.isEmpty()){
                holder.subtitle.setVisibility(View.GONE);
            } else {
                holder.subtitle.setText(book.volumeInfo.subtitle);
            }

            holder.date.setText("Publication date: " + book.volumeInfo.publishedDate);

            if(book.volumeInfo.averageRating != null) {
                holder.rating.setText(" Rating: " + book.volumeInfo.averageRating);
            }
            else holder.rating.setText("N/A");

            holder.page.setText( "Pages: " + book.volumeInfo.pageCount);

            String imageLink = book.volumeInfo.imageLink.smallThumbnail;
            Uri imageUri = Uri.parse(imageLink);
            Log.d(TAG, imageLink);
            Glide
                    .with(holder.image.getContext())
                    .load(imageUri)
                    .into(holder.image);

            holder.bind(allBook.get(position), onItemClickListener);

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return allBook.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView subtitle;
        TextView author;
        TextView date;
        TextView page;
        TextView rating;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            subtitle = itemView.findViewById(R.id.subtitle);
            author = itemView.findViewById(R.id.author);
            date = itemView.findViewById(R.id.date);
            page = itemView.findViewById(R.id.pages);
            rating = itemView.findViewById(R.id.rating);

        }

        public void bind(final Book book, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(book);
                }
            });
        }
    }

    public void clear() {
        allBook.clear();
        notifyDataSetChanged();

    }

    public void setData(List<Book> books) {
        allBook.addAll(books);
        notifyDataSetChanged();
    }

}
