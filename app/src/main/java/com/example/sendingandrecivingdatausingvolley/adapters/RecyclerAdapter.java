package com.example.sendingandrecivingdatausingvolley.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sendingandrecivingdatausingvolley.R;
import com.example.sendingandrecivingdatausingvolley.dataProviders.FetchDataProvider;

import java.util.List;

/**
 * Created by Anonymous on 1/9/2018.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    List<FetchDataProvider> dataProviders;
    Context context;

    public RecyclerAdapter(List<FetchDataProvider> dataProviders, Context context) {
        this.dataProviders = dataProviders;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_item_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.showData.setText(dataProviders.get(position).getDate());
        holder.showTime.setText(dataProviders.get(position).getTime());
        holder.showDetails.setText(dataProviders.get(position).getDetails());

    }

    @Override
    public int getItemCount() {
        return dataProviders.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView showData, showTime, showDetails;

        public MyViewHolder(View itemView) {
            super(itemView);

            showData = (TextView) itemView.findViewById(R.id.showDate);
            showTime = (TextView) itemView.findViewById(R.id.showTime);
            showDetails = (TextView) itemView.findViewById(R.id.showDetails);
        }
    }
}
