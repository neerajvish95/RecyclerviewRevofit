package com.neerajvishwakarma.revofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class List {
    @SerializedName("title")
    public String title;
    @SerializedName("count")
    public float count;
    @SerializedName("data")
    public ArrayList<Data> data = new ArrayList<Data>();


}
