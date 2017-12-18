package sanketguru.com.sample.Search;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import sanketguru.com.sample.test.RetrofitHelper;
import timber.log.Timber;

import static io.reactivex.schedulers.Schedulers.*;

/**
 * Created by Bhavesh on 12-12-2017.
 */

public class SearchViewModel extends ViewModel {
    RetrofitHelper retrofitFactory = new RetrofitHelper();
    public MutableLiveData<List<SerachVacancy>> mSectionData = new MutableLiveData<>();

    public void getSearchList(Observable<String> publishSubject) {

       /* Observable<SearchVacancyResponce> call = retrofitFactory.getSearchService().getSearchResponse("100");
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                          //  mSectionData.setValue(response.getGeonames());
                            Timber.v("tag : %s", response.getSerachVacancy().get(0).getVacancyID());
                        },
                        throwable -> {
                            Timber.v("tag : %s", throwable.toString());
                            throwable.printStackTrace();
                        });*/

        publishSubject.debounce(200, TimeUnit.MILLISECONDS).
                filter(new Predicate<String>() {
                    @Override
                    public boolean test(String text) throws Exception {
                        if (text.isEmpty()) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                })
                .distinctUntilChanged()
                .switchMap(new io.reactivex.functions.Function<String, ObservableSource<SearchVacancyResponce>>() {
                    @Override
                    public ObservableSource<SearchVacancyResponce> apply(String query) throws Exception {
                        return Search(query);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            //  mSectionData.setValue(response.getGeonames());
                            try {
                                mSectionData.setValue(response.getSerachVacancy());
                                Timber.v("tag : %s", response.getSerachVacancy().get(0).getVacancyID());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        },
                        throwable -> {
                            Timber.v("tag : %s", throwable.toString());
                            throwable.printStackTrace();
                        });
    }

    public Observable<SearchVacancyResponce> Search(String query) {
        return retrofitFactory.getSearchService().getSearchResponse(query);
    }


}
