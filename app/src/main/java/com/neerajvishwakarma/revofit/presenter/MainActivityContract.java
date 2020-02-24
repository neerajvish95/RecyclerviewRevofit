package com.neerajvishwakarma.revofit.presenter;


import com.neerajvishwakarma.revofit.model.RevofitResponseDataModel;

public interface MainActivityContract {
    interface View {
        void showData(RevofitResponseDataModel data);

        void showError(String message);

        void showComplete();

        void showProgress();

        void hideProgress();
    }

    interface Presenter {
        void loadData();
    }
}
