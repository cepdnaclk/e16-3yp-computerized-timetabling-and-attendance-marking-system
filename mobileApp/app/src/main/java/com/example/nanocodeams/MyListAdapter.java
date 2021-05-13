package com.example.nanocodeams;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{

    private JSONArray schedule;

    // RecyclerView recyclerView;
    public MyListAdapter(JSONArray schedule) {
        this.schedule = schedule;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View singleSch = layoutInflater.inflate(R.layout.schedule, parent, false);
        ViewHolder viewHolder = new ViewHolder(singleSch);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try {
            holder.textView.setText(schedule.get(position).toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    @Override
    public int getItemCount() {
        return schedule.length();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;


        public ViewHolder(View itemView) {
            super(itemView);

            this.textView = itemView.findViewById(R.id.simpleSch);

        }
    }

}
