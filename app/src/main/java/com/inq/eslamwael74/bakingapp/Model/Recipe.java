package com.inq.eslamwael74.bakingapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Eslam Wael on 10/31/2017.
 */

public class Recipe implements Parcelable {

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

    protected Recipe(Parcel in) {
        id = in.readInt();
        name = in.readString();
        servings = in.readInt();
        image = in.readString();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeInt(servings);
        parcel.writeString(image);
    }
}
