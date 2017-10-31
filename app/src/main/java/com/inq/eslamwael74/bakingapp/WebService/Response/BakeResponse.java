package com.inq.eslamwael74.bakingapp.WebService.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.inq.eslamwael74.bakingapp.Model.Recipe;

import java.util.ArrayList;

/**
 * Created by Eslam Wael on 10/31/2017.
 */

public class BakeResponse {

    @SerializedName("[")
    @Expose
    ArrayList<Recipe> recipes;

    public ArrayList<Recipe> getRecipes() {
        return this.recipes;
    }
}
