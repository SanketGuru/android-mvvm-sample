package sanketguru.com.sample.Search;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Converter;
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
        Map<String,String> Datamap=new HashMap<>();
        Datamap.put("Head","head data");
      /*  Observable<SearchVacancyResponce> call = retrofitFactory.getSearchService().getSearchResponseBody("100",Datamap,Datamap);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SearchVacancyResponce>() {
                    @Override
                    public void accept(SearchVacancyResponce searchVacancyResponce) throws Exception {

                    }
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
                .switchMap((io.reactivex.functions.Function<String, ObservableSource<List<SerachVacancy>>>) query -> Search(query))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            if (response.size() > 0) {
                                //  mSectionData.setValue(response.getGeonames());
                                try {
                                    mSectionData.setValue(response);
                                    Timber.v("tag : %s", response.get(0).getVacancyID());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        throwable -> {
                            Timber.v("tag : %s", throwable.toString());
                            throwable.printStackTrace();
                        });
    }

    public Observable<List<SerachVacancy>> Search(String query) {
        HashMap<String,String> Datamap=new HashMap<>();
        Datamap.put("Head","head data");

        return retrofitFactory.getSearchService().getSearchResponseBody(query,Datamap,Datamap)
                .map(response -> response.getSerachVacancy())
                .onErrorReturnItem(Collections.emptyList());
    }

    interface DData {
        void DoSome(int ss, String str);

        void DoSome2(int ss, String str);


    }

}
