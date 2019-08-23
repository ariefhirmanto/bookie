package com.ariefhirmanto.bookie;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;

public class DetailActivity extends AppCompatActivity {
    private TextView tvTitle, tvAuthor, tvDescription;
    private ImageView imgPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar3);

        tvTitle = findViewById(R.id.tv_title);
        tvAuthor = findViewById(R.id.tv_author);
        tvDescription = findViewById(R.id.tv_description);
        imgPhoto = findViewById(R.id.img_photo);

        String title = getIntent().getStringExtra(CardViewBookAdapter.TITLE);
        String author = getIntent().getStringExtra(CardViewBookAdapter.AUTHOR);
        String description = getIntent().getStringExtra(CardViewBookAdapter.DESCRIPTION);
        String photo = getIntent().getStringExtra(CardViewBookAdapter.PHOTO);

        tvTitle.setText(title);
        tvAuthor.setText(author);
        tvDescription.setText(description);

        new DownloadImageFromInternet(imgPhoto)
                .execute(photo);

        findViewById(R.id.icon_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.buy_book).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = getIntent().getStringExtra(CardViewBookAdapter.URL);
                Intent externalLink = new Intent(android.content.Intent.ACTION_VIEW);
                externalLink.setData(Uri.parse(url));
                startActivity(externalLink);
                finish();
            }
        });
    }

    private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public DownloadImageFromInternet(ImageView imageView) {
            this.imageView = imageView;
        }

        protected Bitmap doInBackground(String... urls) {
            String imageURL = urls[0];
            Bitmap bimage = null;
            try {
                InputStream in = new java.net.URL(imageURL).openStream();
                bimage = BitmapFactory.decodeStream(in);

            } catch (Exception e) {
                Log.e("Error Message", e.getMessage());
                e.printStackTrace();
            }
            return bimage;
        }

        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }
}
