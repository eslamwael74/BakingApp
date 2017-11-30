package com.inq.eslamwael74.bakingapp.Adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.inq.eslamwael74.bakingapp.Fragment.RecipeDetailsFragment;
import com.inq.eslamwael74.bakingapp.Fragment.RecipeFragment;
import com.inq.eslamwael74.bakingapp.Model.Recipe;
import com.inq.eslamwael74.bakingapp.Model.Step;
import com.inq.eslamwael74.bakingapp.R;
import com.inq.eslamwael74.bakingapp.StepSelect;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eslamwael74 on 01/11/17.
 */

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.MyViewHolder>  {

    FragmentActivity fragmentActivity;

    ArrayList<Step> steps;

    private StepSelect stepSelect;


    public StepsAdapter(FragmentActivity fragmentActivity, ArrayList<Step> steps) {
        this.fragmentActivity = fragmentActivity;
        this.steps = steps;
        this.stepSelect = (StepSelect) fragmentActivity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(fragmentActivity)
                .inflate(R.layout.step_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepsAdapter.MyViewHolder holder, int position) {

        holder.tvDesc.setText(steps.get(position).getShortDescription());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                getFragmentAppDetails(steps,steps.get(position).getId());
                stepSelect.onStepSelect(steps.get(position).getId());
//                Toast.makeText(fragmentActivity, "" + steps.get(position).getId(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getFragmentAppDetails(ArrayList<Step> steps,int id) {

        RecipeDetailsFragment recipeDetailsFragment = RecipeDetailsFragment.newInstance(steps,id);
        FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_list, recipeDetailsFragment);
        transaction.addToBackStack(null);
        transaction.commit();


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
