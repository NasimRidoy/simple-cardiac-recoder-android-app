package com.example.cardioassistant;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    Context context;
    ArrayList<Record> list;
    ArrayList<String> keys;


    public MyAdapter(Context context, ArrayList<Record> list , ArrayList<String>keys) {
        this.context = context;
        this.list = list;
        this.keys = keys;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View recordView = LayoutInflater.from(context).inflate(R.layout.record_item,parent,false);
        return new MyViewHolder(recordView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Record record = list.get(position);
        holder.record_date_text.setText("Date : "+record.getDataMeasured());
        holder.record_time_text.setText("Time : "+record.getTimeMeasured());
        holder.record_comment_text.setText("Comment : "+record.getComment());
        holder.record_heart_rate_text.setText("Heart Rate : "+String.valueOf(record.getHeartRate()));
        holder.record_diastole_number.setText(String.valueOf(record.getDiastolicPressure()));
        holder.record_systole_number.setText(String.valueOf(record.getSystolicPressure()));

        if (record.getSystolicPressure()<=120 && record.getDiastolicPressure()<=80){
            holder.layout.setBackgroundResource(R.color.wheat);

            holder.record_systole_text.setTextColor(Color.parseColor("#526D82"));
            holder.record_diastole_text.setTextColor(Color.parseColor("#526D82"));
            holder.record_diastole_number.setTextColor(Color.parseColor("#526D82"));
            holder.record_systole_number.setTextColor(Color.parseColor("#526D82"));
        }
        else if (record.getSystolicPressure()<=129 && record.getDiastolicPressure()<=80){
            holder.layout.setBackgroundResource(R.color.yellowish);

            holder.record_systole_text.setTextColor(Color.parseColor("#526D82"));
            holder.record_diastole_text.setTextColor(Color.parseColor("#526D82"));
            holder.record_diastole_number.setTextColor(Color.parseColor("#526D82"));
            holder.record_systole_number.setTextColor(Color.parseColor("#526D82"));
        }
        else if (record.getSystolicPressure()<=139 || record.getDiastolicPressure()<=89){
            holder.layout.setBackgroundResource(R.color.orangish);

            holder.record_systole_text.setTextColor(Color.parseColor("#FFFFFF"));
            holder.record_diastole_text.setTextColor(Color.parseColor("#FFFFFF"));
            holder.record_diastole_number.setTextColor(Color.parseColor("#FFFFFF"));
            holder.record_systole_number.setTextColor(Color.parseColor("#FFFFFF"));
        }
        else if (record.getSystolicPressure()<=180 && record.getDiastolicPressure()<=120){
            holder.layout.setBackgroundResource(R.color.redish);

            holder.record_systole_text.setTextColor(Color.parseColor("#FFFFFF"));
            holder.record_diastole_text.setTextColor(Color.parseColor("#FFFFFF"));
            holder.record_diastole_number.setTextColor(Color.parseColor("#FFFFFF"));
            holder.record_systole_number.setTextColor(Color.parseColor("#FFFFFF"));
        }
        else{
            holder.layout.setBackgroundResource(R.color.meronish);

            holder.record_systole_text.setTextColor(Color.parseColor("#FFFFFF"));
            holder.record_diastole_text.setTextColor(Color.parseColor("#FFFFFF"));
            holder.record_diastole_number.setTextColor(Color.parseColor("#FFFFFF"));
            holder.record_systole_number.setTextColor(Color.parseColor("#FFFFFF"));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent UpdateIntent = new Intent(context,UpdateActivity.class);
                UpdateIntent.putExtra("key",keys.get(position));
                context.startActivity(UpdateIntent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        View recordColorView;
        TextView record_date_text,record_time_text, record_systole_text,record_diastole_text,record_systole_number,
                record_diastole_number,record_comment_text,record_heart_rate_text;
        MaterialCardView cardView;
        LinearLayout layout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.record_card_view);
            layout = itemView.findViewById(R.id.card);
            record_date_text = itemView.findViewById(R.id.record_date_text);
            record_time_text = itemView.findViewById(R.id.record_time_text);
            record_systole_text = itemView.findViewById(R.id.record_systole_text);
            record_diastole_text = itemView.findViewById(R.id.record_diastole_text);
            record_systole_number = itemView.findViewById(R.id.record_systole_number);
            record_diastole_number = itemView.findViewById(R.id.record_diastole_number);
            record_comment_text = itemView.findViewById(R.id.record_comment_text);
            record_heart_rate_text = itemView.findViewById(R.id.record_heart_rate_text);
        }
    }
}
