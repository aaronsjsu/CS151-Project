package model;

import java.io.*;
import java.util.Objects;

/**
 * Defines a contract for objects to be saved (and
 * thus loaded) from a specified storage medium.
 * Any class implementing savable is inherently Serializable.
 *
 * @see Serializable
 * @since 1.0
 */
public interface Savable extends Serializable
{
    /**
     * Saves this object to the storage medium.
     * Implementations should call Savable.save and
     * Savable.load for simplified saving and loading.
     * 
     * @return true if the object was successfully saved.
     */
    boolean save();

    /**
     * Saves a serializable object to the storage medium.
     * 
     * @param obj Serializable object to be saved.
     * @param filename Filename of the file which will host the serialized object.
     * @param <T> Data type of the serialized object.
     * @return true if the object was successfully saved to the storage medium.
     */
    static <T extends Serializable> boolean save(final T obj, final String filename)
    {
        try (final FileOutputStream fos = new FileOutputStream(Objects.requireNonNull(filename));
             final ObjectOutputStream oos = new ObjectOutputStream(fos))
        {
            oos.writeObject(Objects.requireNonNull(obj));
            return true;
        }
        catch (final IOException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Loads a serialized object from the storage medium.
     * 
     * @param filename Filename of the file containing the serialized object.
     * @param <T> Expected data type of the serialized object.
     * @return Serialized object read from the storage medium, otherwise null.
     */
    static <T extends Serializable> T load(final String filename)
    {
        try (final FileInputStream fis = new FileInputStream(Objects.requireNonNull(filename));
             final ObjectInputStream ois = new ObjectInputStream(fis))
        {
            return (T) ois.readObject();
        }
        /* If the file is not present, simply ignore the exception. */
        catch (final FileNotFoundException e) { }
        catch (final IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
