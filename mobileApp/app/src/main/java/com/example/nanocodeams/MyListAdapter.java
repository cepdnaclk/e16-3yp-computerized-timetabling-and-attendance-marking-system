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
            String tmpSch = schedule.get(position).toString().trim();
            tmpSch = tmpSch.substring(1,tmpSch.length()-1);
            String[] schParts = tmpSch.split(",",5);
            for(int i=0 ; i<5 ; i++) schParts[i] = schParts[i].substring(1,schParts[i].length()-1);
            String txt = schParts[0] +"\n" + schParts[1] +"\n" +schParts[2] +"\n" +schParts[3] +"\n" +schParts[4];

            holder.courseCode.setText(schParts[3]);
            holder.location.setText(schParts[4]);
            holder.startTime.setText(schParts[0]);
            holder.endTime.setText(schParts[1]);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    @Override
    public int getItemCount() {
        return schedule.length();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView courseCode;
        public TextView location;
        public TextView startTime;
        public TextView endTime;


        public ViewHolder(View itemView) {
            super(itemView);

            this.courseCode = itemView.findViewById(R.id.courseCode);
            this.location = itemView.findViewById(R.id.location);
            this.startTime = itemView.findViewById(R.id.startTime);
            this.endTime = itemView.findViewById(R.id.endTime);

        }
    }

}
