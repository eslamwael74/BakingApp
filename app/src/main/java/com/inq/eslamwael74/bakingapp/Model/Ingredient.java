package com.inq.eslamwael74.bakingapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eslam Wael on 10/31/2017.
 */

public class Ingredient {


    @SerializedName("quantity")
    @Expose
    int quantity;

    @SerializedName("measure")
    @Expose
    String measure;

    @SerializedName("ingredient")
    @Expose
    String ingredient;

    public int getQuantity() {
        return this.quantity;
    }

    public String getMeasure() {
        return this.measure;
    }

    public String getIngredient() {
        return this.ingredient;
    }
}
