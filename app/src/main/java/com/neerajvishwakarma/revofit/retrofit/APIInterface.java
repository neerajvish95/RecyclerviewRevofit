package com.neerajvishwakarma.revofit.retrofit;

import com.neerajvishwakarma.revofit.model.RevofitResponseDataModel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface APIInterface {



    @GET("eat_exam_response.json")
    Observable<RevofitResponseDataModel> getDataModel(@Query("limit") String limit);
}
