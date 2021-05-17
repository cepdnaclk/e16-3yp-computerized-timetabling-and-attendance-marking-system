package com.example.nanocodeams;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MainAdoptor extends RecyclerView.Adapter<MainAdoptor.ViewHolder> {

    ArrayList<MainModel> mainModels;
    Context context;

    public MainAdoptor(ArrayList<MainModel> mainModels, Context context) {
        this.mainModels = mainModels;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

        holder.textView.setText(mainModels.get(position).getSub());
        holder.textView_1.setText(mainModels.get(position).getTime());
        holder.textView_2.setText(mainModels.get(position).getRoom());

    }

    @Override
    public int getItemCount() {
        return mainModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView,textView_1,textView_2;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view_1);
            textView_1 = itemView.findViewById(R.id.text_view_2);
            textView_2 = itemView.findViewById(R.id.text_view_3);
        }
    }
}
