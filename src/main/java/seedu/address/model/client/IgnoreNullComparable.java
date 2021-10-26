package seedu.address.model.client;

/**
 * This interface ensures a class {@code T} to be comparable according to a {@code SortDirection}
 * where null or the class null equivalent will inherently be positioned last.
 */
public interface IgnoreNullComparable<T> {
    int compareWithDirection(T o, SortDirection sortDirection);
}
