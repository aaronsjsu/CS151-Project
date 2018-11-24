package model.observable;

/**
 * Defines an observer design pattern.
 * This acts as a replacement for the outdated listeners
 * that were created before generics were implemented.
 * 
 * @param <T> Data type of the object that may change.
 * @see Observable
 * @since 1.0
 */
public interface ChangeListener<T>
{
    /**
     * Notifies the listener of a change made to the observable.
     * @param source Observable which was changed.
     * @param oldValue Previous value which was overwritten.
     * @param newValue New value of the observable.
     */
    void fire(Observable<T> source, T oldValue, T newValue);
}
