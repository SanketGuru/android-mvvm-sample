package sanketguru.com.sample.util.nav;

import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;

import sanketguru.com.sample.MainActivity;
import sanketguru.com.sample.MainFragment;
import sanketguru.com.sample.R;
import sanketguru.com.sample.menu.MenuGetter;
import timber.log.Timber;

/**
 * Try and migrate all the naviagtion logic within this helper
 * e.g - navigateTo
 * Created on 21/12/2017.
 */

public class NavigationHelper {

    private final ArrayList<String> pageStack = new ArrayList<>();
    private int containerViewId;
    private MainActivity activity;

    @Nullable
    private static MainFragment getCurrentFragment(FragmentManager fragmentManager,
                                                   @IdRes int container) {
        Fragment currentFragment;
        int backStackEntryCount = fragmentManager.getBackStackEntryCount();
        if (backStackEntryCount > 0) {
            // Get the last fragment in back task after
            // popBackStackImmediate() to resume the current fragment
            FragmentManager.BackStackEntry backEntry =
                    fragmentManager.getBackStackEntryAt(backStackEntryCount - 1);
            currentFragment = fragmentManager.findFragmentByTag(backEntry.getName());
        } else {
            // Last fragment is not attached to back stack. So get
            // the fragment by id and resume the fragment.
            currentFragment = fragmentManager.findFragmentById(container);
        }
        return (MainFragment) currentFragment;
    }

    void setActivity(MainActivity activity) {
        this.activity = activity;
        this.containerViewId = R.id.mainView;
    }

    public void setupFragment(Fragment fragment, boolean addToStack) {
        addFragment(containerViewId, fragment, addToStack);
    }

    void addFragment(final FragmentStateModel stateModel) {
        MainActivity activity = this.activity;
        if (activity == null) {
            return;
        }
        MainFragment currentFragment =
                getCurrentFragment(activity.getSupportFragmentManager(), containerViewId);
        addFragment(currentFragment, stateModel);
    }

    public void addFragment(@Nullable final MainFragment currentFrag,
                            final FragmentStateModel stateModel) {
        final MainActivity activity = this.activity;
        if (activity == null) {
            return;
        }
        final MainFragment fragment = MenuGetter.getFragment(stateModel.getPageId());
        if (fragment == null) {
            return;
        }

        if (fragment.getArguments() == null) {
            fragment.setArguments(stateModel.getArgs());
        } else {
            fragment.getArguments().putAll(stateModel.getArgs());
        }

        fragment.previousFragment = currentFrag;
        addFragment(containerViewId, fragment, stateModel.isAddToStack());
    }

    private void addFragment(int containerViewId, Fragment newFragment, boolean addStack) {
        final MainActivity activity = this.activity;
        if (activity == null) {
            return;
        }
        if (newFragment == null) {
            return;
        }
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        MainFragment currOpenFragment = getCurrentFragment(fragmentManager, containerViewId);

        if (currOpenFragment != null) currOpenFragment.onFragmentPause();
       /* if (newFragment.getArguments() != null) {
            newFragment.getArguments().remove(MenuGetter.PAGE_IS_SENSITIVE);
        }*/

//        setCrashlyticsInfo(newFragment, addStack);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        String tag = newFragment.getClass().getCanonicalName();
        if (newFragment.isAdded()) {
            fragmentTransaction.show(newFragment);
        } else {
            if (addStack) {
                fragmentTransaction.add(containerViewId, newFragment, tag)
                        .hide(currOpenFragment)
                        .addToBackStack(tag)
                        .commitAllowingStateLoss();
                fragmentManager.executePendingTransactions();
            } else {
                if (fragmentManager.getFragments() == null || fragmentManager.getFragments()
                        .isEmpty()) {
                    fragmentTransaction.add(containerViewId, newFragment, tag)
                            .commitNowAllowingStateLoss();
                } else {
                    if (fragmentManager.getBackStackEntryCount() > 0) {
                        fragmentManager.popBackStack(null,
                                FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    }
                    fragmentTransaction.replace(containerViewId, newFragment, tag)
                            .commitNowAllowingStateLoss();
                }
            }
        }
        if (!addStack) ((MainFragment) newFragment).onFragmentResume();
    }

    void goBackOnce() {
        final MainActivity activity = this.activity;
        if (activity == null) {
            return;
        }
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        int backStackEntryCount = fragmentManager.getBackStackEntryCount();
        if (backStackEntryCount > 0) {
            // Get the last fragment tag from FragmentManager.BackStackEntry
            FragmentManager.BackStackEntry backEntry =
                    fragmentManager.getBackStackEntryAt(backStackEntryCount - 1);
            // Closing fragment before calling popBackStackImmediate()
            MainFragment closingFragment =
                    (MainFragment) fragmentManager.findFragmentByTag(backEntry.getName());
            closingFragment.onFragmentPause();
            // Close the current fragment
            fragmentManager.popBackStackImmediate();
            removeFromStack();
            MainFragment currentFragment =
                    getCurrentFragment(fragmentManager, containerViewId);
            if (currentFragment != null) {
                activity.setLastTitle(currentFragment.toString());
                currentFragment.onFragmentResume();
            }
        }
    }

    private void removeFromStack() {
        if (pageStack.size() > 0) {
            pageStack.remove(pageStack.size() - 1);
        }
        Timber.d("Current Stack : %s", pageStack.toString());
    }
}
