package com.example.nanocodeams;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ListAdaptor extends RecyclerView.Adapter<ListAdaptor.ViewHolder> {

    String [] course_ID;
    String [] courses;
    Context con;

    public ListAdaptor( Context con,String[] course_ID, String[] courses) {
        this.course_ID = course_ID;
        this.courses = courses;
        this.con = con;
    }

    @NonNull
    @NotNull
    @Override
    public ListAdaptor.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.col_view,parent,false);
        return new ListAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ListAdaptor.ViewHolder holder, int position) {
        holder.textView.setText(course_ID[position]);
        holder.textView_1.setText(courses[position]);
    }

    @Override
    public int getItemCount() {
        return course_ID.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        TextView textView_1;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view_col);
            textView_1 = itemView.findViewById(R.id.text_view_col_1);
        }
    }
}
