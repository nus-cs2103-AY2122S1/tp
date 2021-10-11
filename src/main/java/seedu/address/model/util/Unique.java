package seedu.address.model.util;

public interface Unique<T> {
    boolean isSame(T other);
}
