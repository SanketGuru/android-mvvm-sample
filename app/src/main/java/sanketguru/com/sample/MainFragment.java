package sanketguru.com.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

import sanketguru.com.sample.util.nav.FragmentStateModel;

/**
 * Created on 21/12/2017.
 */

public class MainFragment extends Fragment {

    @Nullable
    public MainFragment previousFragment;
    private Bundle mFragmentResult;
    private MainActivity mContext;
    private ViewGroup containerView;

    private boolean backPressEnabled = true;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Bundle bundle = data.getExtras();
        } else {
            getMainActivity().loadFragment(resultCode);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        containerView = (ViewGroup) view;
    }

    public MainActivity getMainActivity() {
        if (mContext != null) {
            return mContext;
        } else {
            return (MainActivity) super.getActivity();
        }
    }

    public void navigateTo(int id, @NonNull Bundle bundle, final boolean addStack) {
        getMainActivity().gotoPage(this, new FragmentStateModel(id, bundle, addStack));
    }

    public boolean isBackPressEnabled() {
        return backPressEnabled;
    }

    public void setBackPressEnabled(boolean backPressEnabled) {
        this.backPressEnabled = backPressEnabled;
    }

    public final void setFragmentResult(Bundle result) {
        if (previousFragment != null) {
            previousFragment.setResult(result);
        }
    }

    private void setResult(Bundle result) {
        this.mFragmentResult = result;
    }

    @CallSuper
    public void onFragmentResume() {

    }

    public void onFragmentPause() {

    }
}
