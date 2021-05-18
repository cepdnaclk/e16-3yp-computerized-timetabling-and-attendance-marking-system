package com.example.nanocodeams;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {

    String remainder[],time[],course[],message[];
    Context context;

    public NotificationAdapter(Context ct, String remainder[],String time[],String course[],String message[]) {
        context = ct;
        this.remainder = remainder;
        this.time = time;
        this.course = course;
        this.message = message;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.notification_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
        holder.remainder.setText(remainder[position]);
        holder.time.setText(time[position]);
        holder.course.setText(course[position]);
        holder.message.setText(message[position]);
    }

    @Override
    public int getItemCount() {
        return remainder.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView remainder,time,course,message;
        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            remainder = itemView.findViewById(R.id.textRemainder);
            time = itemView.findViewById(R.id.textTime);
            course = itemView.findViewById(R.id.textCourse);
            message = itemView.findViewById(R.id.textMessage);
        }
    }
}
