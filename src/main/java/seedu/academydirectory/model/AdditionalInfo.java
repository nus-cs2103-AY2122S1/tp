package seedu.academydirectory.model;

import java.util.NoSuchElementException;
import java.util.Objects;

public final class AdditionalInfo<T> {

    private static final AdditionalInfo<?> EMPTY = new AdditionalInfo<>();

    private final T value;

    private AdditionalInfo() {
        this.value = null;
    }

    public static<T> AdditionalInfo<T> empty() {
        @SuppressWarnings("unchecked")
        AdditionalInfo<T> t = (AdditionalInfo<T>) EMPTY;
        return t;
    }


    private AdditionalInfo(T value) {
        this.value = Objects.requireNonNull(value);
    }

    public static <T> AdditionalInfo<T> of(T value) {
        return new AdditionalInfo<>(value);
    }

    public static <T> AdditionalInfo<T> ofNullable(T value) {
        return value == null ? empty() : of(value);
    }

    public T get() {
        if (value == null) {
            throw new NoSuchElementException("No value present");
        }
        return value;
    }

    public boolean isPresent() {
        return value != null;
    }

    public boolean isEmpty() {
        return value == null;
    }
}
