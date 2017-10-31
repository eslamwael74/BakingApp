package com.inq.eslamwael74.bakingapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eslam Wael on 10/31/2017.
 */

public class Step {


    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("shortDescription")
    @Expose
    String shortDescription;

    @SerializedName("description")
    @Expose
    String description;

    @SerializedName("videoURL")
    @Expose
    String videoURL;

    @SerializedName("thumbnailURL")
    @Expose
    String thumbnailURL;

    public int getId() {
        return this.id;
    }

    public String getShortDescription() {
        return this.shortDescription;
    }

    public String getDescription() {
        return this.description;
    }

    public String getVideoURL() {
        return this.videoURL;
    }

    public String getThumbnailURL() {
        return this.thumbnailURL;
    }
}
