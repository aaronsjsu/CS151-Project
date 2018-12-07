package model.observable;

/**
 * Defines an observer design pattern.
 * This acts as a replacement for the outdated listeners
 * that were created before generics were implemented.
 * @see Observable
 * @since 1.0
 */
public interface ChangeListener
{
    /**
     * Notifies the listener of a change made to the observable.
     */
    void fire();
}
