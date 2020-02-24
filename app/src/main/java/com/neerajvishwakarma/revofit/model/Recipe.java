package com.neerajvishwakarma.revofit.model;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Recipe {
    @SerializedName("totalrecords")
    public float totalrecords;
    @SerializedName("recipe_count")
    public float recipe_count;
    @SerializedName("data")
    public ArrayList<Data> data = new ArrayList<Data>();


}
