package com.inq.eslamwael74.bakingapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eslam Wael on 10/31/2017.
 */

public class Ingredient implements Parcelable {


    @SerializedName("quantity")
    @Expose
    String quantity;

    @SerializedName("measure")
    @Expose
    String measure;

    @SerializedName("ingredient")
    @Expose
    String ingredient;

    protected Ingredient(Parcel in) {
        quantity = in.readString();
        measure = in.readString();
        ingredient = in.readString();
    }

    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

    public String getQuantity() {
        return this.quantity;
    }

    public String getMeasure() {
        return this.measure;
    }

    public String getIngredient() {
        return this.ingredient;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(quantity);
        parcel.writeString(measure);
        parcel.writeString(ingredient);
    }
}
