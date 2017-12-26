package sanketguru.com.sample.util.nav;

import android.os.Bundle;
import android.support.annotation.NonNull;

import sanketguru.com.sample.constant.MainConstants;

/**
 * Created on 23/12/2017.
 */

public class FragmentStateModel {
    private final boolean goBack;
    private final int pageId;
    private final Bundle args;
    private final boolean addToStack;

    public FragmentStateModel(int pageId, @NonNull Bundle args,
                              boolean addToStack) {
        this.pageId = pageId;
        this.args = args;
        this.addToStack = addToStack;
        this.goBack = false;
    }

    public FragmentStateModel(boolean goBack) {
        this.goBack = goBack;
        //Just initializing for the sake of being final.
        this.pageId = MainConstants.NAVIGATE_TO_NONE;
        this.args = null;
        this.addToStack = false;
    }

    boolean isGoBack() {
        return goBack;
    }

    int getPageId() {
        return pageId;
    }

    Bundle getArgs() {
        return args;
    }

    boolean isAddToStack() {
        return addToStack;
    }
}
