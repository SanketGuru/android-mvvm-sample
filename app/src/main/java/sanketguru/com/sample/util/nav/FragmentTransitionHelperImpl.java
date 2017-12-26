package sanketguru.com.sample.util.nav;

import android.app.Activity;

import sanketguru.com.sample.MainActivity;

/**
 * Created on 23/12/2017.
 */

public class FragmentTransitionHelperImpl extends FragmentTransitionHelper {

    /**
     * Navigation Helper instance
     */
    private final NavigationHelper navHelper;
    /**
     * Activity instance
     */
    private MainActivity activity;

    public FragmentTransitionHelperImpl() {
        navHelper = new NavigationHelper();
    }

    public NavigationHelper getNavHelper() {
        return navHelper;
    }

    /**
     * Set the activity associated with the handler
     *
     * @param activity the activity to set
     */
    public final void setActivity(MainActivity activity) {
        this.activity = activity;
        navHelper.setActivity(activity);
    }

    @Override
    protected boolean storeMessage(FragmentStateModel message) {
        return true;
    }

    @Override
    protected void processMessage(FragmentStateModel message) {
        final Activity activity = this.activity;
        if (activity != null) {
            if (message.isGoBack()) {
                navHelper.goBackOnce();
            } else {
                navHelper.addFragment(message);
            }
        }
    }
}
