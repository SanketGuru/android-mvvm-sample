package sanketguru.com.sample.menu;

import android.os.Bundle;
import android.support.annotation.Nullable;

import sanketguru.com.sample.MainFragment;
import sanketguru.com.sample.Search.SearchFragment;
import sanketguru.com.sample.constant.MainConstants;
import sanketguru.com.sample.home.HomeFragment;

/**
 * Created on 23/12/2017.
 */

public class MenuGetter implements MainConstants {
    public final static String PAGE_SOURCE = "Source";

    private MenuGetter() {
    }

    @Nullable
    public static MainFragment getFragment(int menuCode) {
        Bundle args = new Bundle();
        MainFragment frag = null;
        switch (menuCode) {
            case NAVIGATE_TO_GALLERY:
                frag = new HomeFragment();
                frag.setArguments(args);
                break;
                case NAVIGATE_TO_SEARCH:
                frag = new SearchFragment();
                frag.setArguments(args);
                break;
        }
        return frag;
    }
}
