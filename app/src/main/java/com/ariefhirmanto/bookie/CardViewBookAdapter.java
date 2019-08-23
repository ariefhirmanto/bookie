package com.ariefhirmanto.bookie;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class CardViewBookAdapter extends RecyclerView.Adapter<CardViewBookAdapter.CardViewHolder> {
    private ArrayList<Book> listBook;
    private Context context;
    public final static String TITLE = "title";
    public final static String AUTHOR = "author";
    public final static String DESCRIPTION = "description";
    public final static String PHOTO = "photo";
    public final static String URL = "url";

    public CardViewBookAdapter(ArrayList<Book> list, Context context) {
        this.context = context;
        this.listBook = list;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_grid_data, viewGroup, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder cardViewHolder, final int i) {
        Book book = listBook.get(i);

        Glide.with(cardViewHolder.itemView.getContext())
                .load(book.getPhoto())
                .apply(new RequestOptions().override(100, 100))
                .into(cardViewHolder.imgPhoto);

        cardViewHolder.tvTitle.setText(book.getTitle());
        cardViewHolder.tvAuthor.setText(book.getAuthor());

        cardViewHolder.btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = listBook.get(i);
                Intent externalLink = new Intent(android.content.Intent.ACTION_VIEW);
                externalLink.setData(Uri.parse(book.getURL()));
                context.startActivity(externalLink);
            }
        });

        cardViewHolder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = listBook.get(i);
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(TITLE, book.getTitle());
                intent.putExtra(AUTHOR, book.getAuthor());
                intent.putExtra(DESCRIPTION, book.getDescription());
                intent.putExtra(PHOTO, book.getPhoto());
                intent.putExtra(URL, book.getURL());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBook.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvTitle, tvAuthor, tvDescription;
        Button btnBuyNow, btnDetail;


        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvAuthor = itemView.findViewById(R.id.tv_item_author);
            btnBuyNow = itemView.findViewById(R.id.btn_buy);
            btnDetail = itemView.findViewById(R.id.btn_detail);
        }
    }
}
