package com.inq.eslamwael74.bakingapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Eslam Wael on 10/31/2017.
 */

public class Recipe {

    @SerializedName("id")
    @Expose
    private
    int id;

    @SerializedName("name")
    @Expose
    private
    String name;

    @SerializedName("ingredients")
    @Expose
    private
    ArrayList<Ingredient> ingredients;

    @SerializedName("steps")
    @Expose
    private
    ArrayList<Step> steps;

    @SerializedName("servings")
    @Expose
    private
    int servings;

    @SerializedName("image")
    @Expose
    private
    String image;

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public ArrayList<Step> getSteps() {
        return this.steps;
    }

    public int getServings() {
        return this.servings;
    }

    public String getImage() {
        return this.image;
    }
}
