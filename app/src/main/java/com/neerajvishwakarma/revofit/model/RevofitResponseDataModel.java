package com.neerajvishwakarma.revofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RevofitResponseDataModel {

    @SerializedName("response")
    public Response response;
    @SerializedName("main_content")
    public Main_content main_content;


}
