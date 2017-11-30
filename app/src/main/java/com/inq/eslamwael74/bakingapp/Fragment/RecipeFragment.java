package com.inq.eslamwael74.bakingapp.Fragment;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inq.eslamwael74.bakingapp.Adapter.IngredientAdapter;
import com.inq.eslamwael74.bakingapp.Adapter.StepsAdapter;
import com.inq.eslamwael74.bakingapp.Model.Ingredient;
import com.inq.eslamwael74.bakingapp.Model.Recipe;
import com.inq.eslamwael74.bakingapp.Model.Step;
import com.inq.eslamwael74.bakingapp.R;
import com.inq.eslamwael74.bakingapp.StepSelect;
import com.inq.eslamwael74.bakingapp.UtilClass;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eslamwael74 on 01/11/17.
 */

public class RecipeFragment extends Fragment {

    Recipe recipe;

    ArrayList<Ingredient> ingredients;
    ArrayList<Step> steps;

    @BindView(R.id.recylerview)
    RecyclerView recyclerView;

    @BindView(R.id.tv_name)
    TextView tvName;

    @BindView(R.id.recylerview_steps)
    RecyclerView recyclerViewSteps;

    @BindView(R.id.scrollView)
    NestedScrollView scrollView;

    IngredientAdapter ingredientAdapter;
    StepsAdapter stepsAdapter;

    Parcelable ingState;
    Parcelable stepsState;


    public static RecipeFragment newInstance(Recipe recipe) {
        RecipeFragment mAppDetailsFragment = new RecipeFragment();
        Bundle args = new Bundle();
        args.putParcelable("recipe", recipe);
        mAppDetailsFragment.setArguments(args);
        return mAppDetailsFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_recipe, container, false);
        ButterKnife.bind(this, view);


        if (savedInstanceState == null) {

            if (!UtilClass.isTablet(getActivity())){

                recipe = getActivity().getIntent().getExtras().getParcelable("recipe");

            }
            else{
                recipe = getArguments().getParcelable("recipe");
            }


            tvName.setText(recipe.getName());

            ingredients = recipe.getIngredients();
            steps = recipe.getSteps();

            getIngredients(recipe.getIngredients());
            getSteps(recipe.getSteps());

        } else {

            recipe = savedInstanceState.getParcelable("recipe");
            tvName.setText(recipe.getName());

            ingredients = (ArrayList<Ingredient>) savedInstanceState.getSerializable("listIng");
            getIngredients(ingredients);

            steps = (ArrayList<Step>) savedInstanceState.getSerializable("listSteps");
            getSteps(steps);


        }


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {

            ingState = savedInstanceState.getParcelable("ing");
            stepsState = savedInstanceState.getParcelable("steps");


            int[] position = savedInstanceState.getIntArray("SCROLL_POSITION");


            if (position != null)
                scrollView.post(() -> scrollView.scrollTo(position[0], position[1]));


        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("listIng",  ingredients);
        outState.putSerializable("listSteps",  steps);

        outState.putParcelable("recipe", recipe);

        outState.putParcelable("ing", recyclerView.getLayoutManager().onSaveInstanceState());
        outState.putParcelable("steps", recyclerViewSteps.getLayoutManager().onSaveInstanceState());

        outState.putIntArray("SCROLL_POSITION",
                new int[]{scrollView.getScrollX(), scrollView.getScrollY()});

    }

    private void getIngredients(ArrayList<Ingredient> ingredients) {

        ingredientAdapter = new IngredientAdapter(getActivity(), ingredients);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(ingredientAdapter);

        recyclerView.setNestedScrollingEnabled(false);

        if (ingState != null) {
            recyclerView.getLayoutManager().onRestoreInstanceState(ingState);
        }

    }

    private void getSteps(ArrayList<Step> steps) {

        stepsAdapter = new StepsAdapter(getActivity(), steps);
        LinearLayoutManager layoutStepManager = new LinearLayoutManager(getContext());
        recyclerViewSteps.setLayoutManager(layoutStepManager);
        recyclerViewSteps.setAdapter(stepsAdapter);

        recyclerViewSteps.setNestedScrollingEnabled(false);


        if (stepsState != null) {
            recyclerViewSteps.getLayoutManager().onRestoreInstanceState(stepsState);
        }
    }
}
