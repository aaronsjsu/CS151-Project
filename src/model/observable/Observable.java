package model.observable;

/**
 * Defines an observer design pattern.
 * This acts as a replacement for the outdated listeners
 * that were created before generics were implemented.
 *
 * @param <T> Data type of the change listeners.
 * @see ChangeListener
 * @since 1.0
 */
public interface Observable<T>
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
