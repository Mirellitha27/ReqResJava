package com.iwebsapp.reqresjava.ui.home.view;

import android.widget.ImageView;

import com.iwebsapp.reqresjava.model.home.Datum;

import java.util.List;

public interface HomeView {
    void setDataAdapter(List<Datum> data);
    void showImage(String urlImg, ImageView imageView);
    void showDetail(String idUser);
}
