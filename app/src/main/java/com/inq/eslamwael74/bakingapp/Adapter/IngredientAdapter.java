package com.inq.eslamwael74.bakingapp.Adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inq.eslamwael74.bakingapp.Model.Ingredient;
import com.inq.eslamwael74.bakingapp.Model.Recipe;
import com.inq.eslamwael74.bakingapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eslamwael74 on 01/11/17.
 */

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.MyViewHolder> {

    FragmentActivity fragmentActivity;

    ArrayList<Ingredient> ingredients;


    public IngredientAdapter(FragmentActivity fragmentActivity, ArrayList<Ingredient> ingredients) {
        this.fragmentActivity = fragmentActivity;
        this.ingredients = ingredients;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(fragmentActivity)
                .inflate(R.layout.detail_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IngredientAdapter.MyViewHolder holder, int position) {

        holder.tvQuantity.setText(ingredients.get(position).getQuantity());

        holder.tvMeasure.setText(ingredients.get(position).getMeasure());

        holder.tvIngredient.setText(ingredients.get(position).getIngredient());

    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_quantity)
        TextView tvQuantity;

        @BindView(R.id.tv_measure)
        TextView tvMeasure;

        @BindView(R.id.tv_ingredient)
        TextView tvIngredient;

        @BindView(R.id.card)
        CardView cardView;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }

}
