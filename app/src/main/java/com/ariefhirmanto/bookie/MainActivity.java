package com.ariefhirmanto.bookie;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvData;
    private ArrayList<Book> list = new ArrayList<>();
    private Button btnFindBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar);

        findViewById(R.id.profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
                onStop();
            }
        });

        btnFindBook = findViewById(R.id.find_book);
        btnFindBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent externalLink = new Intent(android.content.Intent.ACTION_VIEW);
                externalLink.setData(Uri.parse("https://www.amazon.com/books-used-books-textbooks/b?ie=UTF8&node=283155"));
                startActivity(externalLink);
                finish();
            }
        });

        rvData = findViewById(R.id.rv_data);
        rvData.setHasFixedSize(true);

        BooksData booksData = new BooksData(this);
        list.addAll(booksData.getListData());
        showRecyclerView();
    }

    private void showRecyclerView() {
        rvData.setLayoutManager(new LinearLayoutManager(this));
        CardViewBookAdapter cardViewBookAdapter = new CardViewBookAdapter(list, this);
        rvData.setAdapter(cardViewBookAdapter);
    }
}
