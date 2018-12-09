package model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Defines an interval of time [a, b] where a is a point in time before or at b.
 *
 * @since 1.0
 */
public class TimeInterval implements Comparable<TimeInterval>
{
    /**
     * The time in which the interval begins.
     * This time is guaranteed not to be after the ending time.
     */
    public final LocalDateTime start;

    /**
     * The time in which the interval ends.
     * This time is guaranteed not to be before the starting time.
     */
    public final LocalDateTime end;

    /**
     * Constructs a time interval with a specified start and ending time.
     * The starting time must not be after the ending time.
     * Reversely, the ending time must not be before the starting time.
     * @param start Starting time of the interval.
     * @param end Ending time of the interval.
     */
    public TimeInterval(final LocalDateTime start, final LocalDateTime end)
    {
        if (Objects.requireNonNull(start).isAfter(Objects.requireNonNull(end)))
            throw new IllegalArgumentException("Starting time of the interval cannot be after the ending time!");
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a time interval given a single point in time.
     * @param point Point in time to treat as an interval.
     */
    public TimeInterval(final LocalDateTime point)
    {
        this.start = this.end = Objects.requireNonNull(point);
    }

    /**
     * Compares this object with the specified object for order.
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     */
    @Override public int compareTo(final TimeInterval o)
    {
        return start.compareTo(o.start);
    }
}
