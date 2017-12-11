package sanketguru.com.sample.home;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import sanketguru.com.sample.apiservices.ApiResponse;
import sanketguru.com.sample.apiservices.RetrofitHelper;
import timber.log.Timber;

/**
 * Created by Sanket Gurav on 12/11/2017.
 */
/**
 * This will be view model of Home fragment
 * */
public class HomeViewModel extends ViewModel {
    RetrofitHelper retrofitFactory = new RetrofitHelper();
    public MutableLiveData<Object> mSectionData=new MutableLiveData<>();
    public void getSectionList(){


        Observable<ApiResponse<Object>> call = retrofitFactory.getWebService().getDashBoard();
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response ->   mSectionData.setValue(response.getPayload())          ,
                        throwable -> Timber.v("tag : %s", throwable.toString()));
    }
}
