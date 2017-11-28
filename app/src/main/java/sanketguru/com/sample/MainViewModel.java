package sanketguru.com.sample;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by Sanket Gurav on 11/28/2017.
 */

public class MainViewModel extends ViewModel {
    MutableLiveData<UserDetails> userDetails;

    public MutableLiveData<UserDetails> getUserDetail() {
        if (userDetails == null) {
            userDetails = new MutableLiveData<>();
        }
        return userDetails;
    }
}
