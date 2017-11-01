package com.inq.eslamwael74.bakingapp.Adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inq.eslamwael74.bakingapp.Model.Recipe;
import com.inq.eslamwael74.bakingapp.Model.Step;
import com.inq.eslamwael74.bakingapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eslamwael74 on 01/11/17.
 */

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.MyViewHolder> {

    FragmentActivity fragmentActivity;

    ArrayList<Step> steps;


    public StepsAdapter(FragmentActivity fragmentActivity, ArrayList<Step> steps) {
        this.fragmentActivity = fragmentActivity;
        this.steps = steps;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(fragmentActivity)
                .inflate(R.layout.step_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepsAdapter.MyViewHolder holder, int position) {

        holder.tvDesc.setText(steps.get(position).getDescription());



    }

    @Override
    public int getItemCount() {
        return steps.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvName)
        TextView tvDesc;


        @BindView(R.id.card)
        CardView cardView;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }

}
