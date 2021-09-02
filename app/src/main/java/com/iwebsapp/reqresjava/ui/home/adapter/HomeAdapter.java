package com.iwebsapp.reqresjava.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iwebsapp.reqresjava.R;
import com.iwebsapp.reqresjava.model.home.Datum;
import com.iwebsapp.reqresjava.ui.home.presenter.HomePresenter;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
    private final List<Datum> modelList;
    private final Context context;
    private final HomePresenter presenter;

    public HomeAdapter(List<Datum> modelList, Context context, HomePresenter presenter) {
        this.modelList = modelList;
        this.context = context;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Datum model = modelList.get(position);
        holder.id.setText(String.valueOf(model.getId()));
        holder.email.setText(model.getEmail());
        holder.name.setText(model.getFirst_name() + " " + model.getLast_name());
        presenter.showImage(model.getAvatar(), holder.imgAvatar);
        holder.itemView.setOnClickListener(view -> presenter.showDetail(String.valueOf(model.getId())));
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id, email, name;
        ImageView imgAvatar;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.textViewId);
            email = itemView.findViewById(R.id.textViewEmail);
            name = itemView.findViewById(R.id.textViewName);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
        }
    }
}
