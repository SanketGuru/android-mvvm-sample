package sanketguru.com.sample.Search;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import sanketguru.com.sample.OnFragmentInteractionListener;
import sanketguru.com.sample.R;
import timber.log.Timber;

/**
 * Created by Bhavesh on 12-12-2017.
 */

public class SearchFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    private SearchViewModel searchViewModel;
    private OnFragmentInteractionListener mListener;
    @BindView(R.id.searchView)
    SearchView searchView;
    @BindView(R.id.textViewResult)
    TextView textViewResult;

    Unbinder unBinder;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        //  homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        setUpSearchObservable();
//        homeViewModel.mSectionData.observe(this, dashBoard -> {
//            if (dashBoard != null) {
//                setUpUiTrays(dashBoard);
//
//
//            }
//            mainScrollRefresh.setRefreshing(false);
//        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        unBinder = ButterKnife.bind(this, v);

        // searchView.setOnClickListener(view ->  searchViewModel.getSearchList( RxSearchObservable.fromView(searchView)));

        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        searchViewModel.mSectionData.observe(this, new Observer<List<SerachVacancy>>() {
            @Override
            public void onChanged(@Nullable List<SerachVacancy> serachVacancies) {
                Timber.v("tag valu: %s", serachVacancies.get(0).getLookupValue().toString());
                textViewResult.setText(serachVacancies.get(0).getLookupValue().toString());
            }
        });
        return v;
    }


    private void setUpSearchObservable() {
        searchViewModel.getSearchList(RxSearchObservable.fromView(searchView));
       /* RxSearchObservable.fromView(searchView)
                .debounce(200, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .switchMap(new Function<String, ObservableSource<SearchVacancyResponce>>() {
                    @Override
                    public ObservableSource<SearchVacancyResponce> apply(String query) throws Exception {
                        return searchViewModel.Search(query);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            //  mSectionData.setValue(response.getGeonames());
                            try {
                                Timber.v("tag : %s", response.getSerachVacancy().get(0).getVacancyID());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        },
                        throwable -> {
                            Timber.v("tag : %s", throwable.toString());
                            throwable.printStackTrace();
                });*/
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            //   mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onRefresh() {

    }
}

