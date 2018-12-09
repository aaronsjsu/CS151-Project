package model.contracts;

import javax.swing.event.ChangeListener;

/**
 * Defines an observer design pattern.
 *
 * @see javax.swing.event.ChangeListener
 * @since 1.0
 */
public interface Observable
{
    /**
     * Adds a listener to listen for changes from the observable.
     * @param listener Listener to be notified of changes.
     */
    void addListener(ChangeListener listener);

    /**
     * Removes a listener from the observable.
     * After the operation, the listener will no longer be notified.
     * @param listener Listener to remove.
     * @return true if the listener was successfully removed.
     */
    boolean removeListener(ChangeListener listener);
}
