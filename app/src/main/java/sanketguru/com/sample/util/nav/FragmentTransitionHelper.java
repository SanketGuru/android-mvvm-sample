package sanketguru.com.sample.util.nav;

import java.util.Vector;

import timber.log.Timber;

/**
 * Created on 23/12/2017.
 * A Fragment transition helper that cache's the fragment navigation
 * called after Activity onPause, and replays it once Activity onResume is done with
 */

public abstract class FragmentTransitionHelper {
    /**
     * Message Queue Buffer
     */
    private final Vector<FragmentStateModel> messageQueueBuffer = new Vector<>();
    /**
     * Flag indicating the pause state
     */
    private boolean paused;

    public boolean isPaused() {
        return paused;
    }

    /**
     * Resume the handler
     */
    final public void resume() {
        paused = false;
        int i = 0;
        while (messageQueueBuffer.size() > 0) {
            Timber.d("Processing the queue : %d", ++i);
            final FragmentStateModel msg = messageQueueBuffer.elementAt(0);
            messageQueueBuffer.removeElementAt(0);
            handleMessage(msg);
        }
    }

    /**
     * Pause the handler
     */
    final public void pause() {
        paused = true;
    }

    /**
     * Notification that the message is about to be stored as the activity is
     * paused. If not handled the message will be saved and replayed when the
     * activity resumes.
     *
     * @param message the message which optional can be handled
     * @return true if the message is to be stored
     */
    protected abstract boolean storeMessage(FragmentStateModel message);

    /**
     * Notification message to be processed. This will either be directly from
     * handleMessage or played back from a saved message when the activity was
     * paused.
     *
     * @param message the message to be handled
     */
    protected abstract void processMessage(FragmentStateModel message);

    final public void navigateTo(FragmentStateModel message) {
        handleMessage(message);
    }

    /**
     * it takes a FragmentStateModel param with necessary data about
     * navigation, if the activity is paused, the message is stored.
     * if the message is not stored, it is dropped altogether.
     *
     * @param msg a message containing navigation description.
     */
    private void handleMessage(FragmentStateModel msg) {
        if (paused) {
            if (storeMessage(msg)) {
                messageQueueBuffer.add(msg);
            }
        } else {
            processMessage(msg);
        }
    }
}
