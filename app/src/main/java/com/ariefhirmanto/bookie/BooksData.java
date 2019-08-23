package com.ariefhirmanto.bookie;

import android.content.Context;

import java.util.ArrayList;

public class BooksData {
    private Context context;
    public static String[] titleData;
    public static String[] authorData;
    public static String[] descriptionData;
    public static String[] photoData;
    public static String[] urlData;

    public BooksData(Context context) {
        this.context = context;
        titleData = context.getResources().getStringArray(R.array.title);
        authorData = context.getResources().getStringArray(R.array.author);
        descriptionData = context.getResources().getStringArray(R.array.description);
        photoData = context.getResources().getStringArray(R.array.photo);
        urlData = context.getResources().getStringArray(R.array.url);
    }



    public static ArrayList<Book> getListData() {
        ArrayList<Book> list = new ArrayList<>();
        for (int data = 0; data < titleData.length; data++) {
            Book book = new Book();

            book.setTitle(titleData[data]);
            book.setAuthor(authorData[data]);
            book.setDescription(descriptionData[data]);
            book.setPhoto(photoData[data]);
            book.setURL(urlData[data]);

            list.add(book);
        }

        return list;
    }
}
