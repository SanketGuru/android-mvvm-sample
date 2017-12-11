package sanketguru.com.sample

import android.support.v4.app.Fragment

/**
 * Created by Sanket Gurav on 12/11/2017.
 */
/**
 * This interface must be implemented by activities that contain this
 * fragment to allow an interaction in this fragment to be communicated
 * to the activity and potentially other fragments contained in that
 * activity.
 *
 *
 * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
 */
interface OnFragmentInteractionListener {
    // TODO: Update argument type and name
    fun onFragmentInteraction(data: Any)

    /**
     * will add fragment to main pane
     * @param fragment to be added which as arrguments set
     */
    fun addFragmentToMain(fragment: Fragment, addToBackstack : Boolean)

    /**
     * Set title of page
     * @param title title of page
     */
    fun setTitleHead(title: CharSequence)

    /**
     * will hide tool bar
     * @param hide
     */
    fun hideToolbar(hide: Boolean)

    /**
     * will lock drawer
     * @param lock
     */
    fun lockDrawer(lock: Boolean)

}