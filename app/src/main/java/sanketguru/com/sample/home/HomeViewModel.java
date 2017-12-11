package sanketguru.com.sample.home;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import sanketguru.com.sample.test.CityResponse;
import sanketguru.com.sample.test.RetrofitHelper;
import timber.log.Timber;

/**
 * Created by Sanket Gurav on 12/11/2017.
 */

/**
 * This will be view model of Home fragment
 */
public class HomeViewModel extends ViewModel {
    RetrofitHelper retrofitFactory = new RetrofitHelper();
    public MutableLiveData<Object> mSectionData = new MutableLiveData<>();

    public void getSectionList() {


        Observable<CityResponse> call = retrofitFactory.getCityService().queryGeonamesO(30, 30, 12d, 12d, "En");
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {mSectionData.setValue(response.getGeonames());
                            Timber.v("tag : %s", response.getGeonames()); },
                        throwable -> {
                            Timber.v("tag : %s", throwable.toString());
                            throwable.printStackTrace();
                        });
    }
}
