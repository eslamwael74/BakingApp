package com.inq.eslamwael74.bakingapp.Adapter;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.inq.eslamwael74.bakingapp.Activity.RecipeActivity;
import com.inq.eslamwael74.bakingapp.Model.Recipe;
import com.inq.eslamwael74.bakingapp.Model.Step;
import com.inq.eslamwael74.bakingapp.R;
import com.inq.eslamwael74.bakingapp.Fragment.RecipeFragment;
import com.inq.eslamwael74.bakingapp.UtilClass;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Eslam Wael on 10/31/2017.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {

    FragmentActivity fragmentActivity;
    ArrayList<Recipe> recipes;

    public MainAdapter(FragmentActivity fragmentActivity, ArrayList<Recipe> recipes) {
        this.fragmentActivity = fragmentActivity;
        this.recipes = recipes;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(fragmentActivity)
                .inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        if (!recipes.get(position).getImage().equals("")){
            Picasso.with(fragmentActivity)
                    .load(recipes.get(position).getImage())
                    .placeholder(R.drawable.android_placeholder)
                    .error(R.drawable.android_placeholder)
                    .into(holder.imageView);
        }


        holder.textView.setText(recipes.get(position).getName());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRecipeActivity(recipes.get(position), recipes.get(position).getSteps(), recipes.get(position).getSteps().get(position).getId());
//                getFragmentAppDetails(recipes.get(position));
//                Toast.makeText(fragmentActivity, "HelloFromMyWorld!!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getRecipeActivity(Recipe recipe, ArrayList<Step> steps, int id) {

//        if (!UtilClass.isTablet(fragmentActivity)) {

            Intent intent = new Intent(fragmentActivity, RecipeActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable("recipe", recipe);
            bundle.putParcelableArrayList("steps", steps);
            bundle.putInt("id", id);
            intent.putExtras(bundle);
            fragmentActivity.startActivity(intent);

//        }
//        else{
//            getFragmentAppDetails(recipe);
//        }
    }

    private void getFragmentAppDetails(Recipe recipe) {

        RecipeFragment recipeFragment = RecipeFragment.newInstance(recipe);
        FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, recipeFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvName)
        TextView textView;

        @BindView(R.id.card)
        CardView cardView;

        @BindView(R.id.img)
        ImageView imageView;



        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}
