package seedu.academydirectory.model;

import java.util.NoSuchElementException;
import java.util.Objects;

public final class AdditionalInfo<T> {

    private static final AdditionalInfo<?> EMPTY = new AdditionalInfo<>();

    private final T value;

    private AdditionalInfo() {
        this.value = null;
    }

    /**
     * Constructs an instance with the described value.
     *
     * @param value the non-{@code null} value to describe
     * @throws NullPointerException if value is {@code null}
     */
    private AdditionalInfo(T value) {
        this.value = Objects.requireNonNull(value);
    }

    /**
     * Returns an empty {@code AdditionalInfo} instance.  No value is present for this
     * {@code AdditionalInfo}.
     *
     * @param <T> The type of the non-existent value
     * @return an empty {@code AdditionalInfo}
     */
    public static<T> AdditionalInfo<T> empty() {
        @SuppressWarnings("unchecked")
        AdditionalInfo<T> t = (AdditionalInfo<T>) EMPTY;
        return t;
    }

    /**
     * Returns an {@code AdditionalInfo} describing the given non-{@code null}
     * value.
     *
     * @param value the value to describe, which must be non-{@code null}
     * @param <T> the type of the value
     * @return an {@code AdditionalInfo} with the value present
     * @throws NullPointerException if value is {@code null}
     */
    public static <T> AdditionalInfo<T> of(T value) {
        return new AdditionalInfo<>(value);
    }

    /**
     * Returns an {@code AdditionalInfo} describing the given value, if
     * non-{@code null}, otherwise returns an empty {@code AdditionalInfo}.
     *
     * @param value the possibly-{@code null} value to describe
     * @param <T> the type of the value
     * @return an {@code AdditionalInfo} with a present value if the specified value
     *         is non-{@code null}, otherwise an empty {@code AdditionalInfo}
     */
    public static <T> AdditionalInfo<T> ofNullable(T value) {
        return value == null ? empty() : of(value);
    }

    /**
     * If a value is present, returns the value, otherwise throws
     * {@code NoSuchElementException}.
     *
     * @apiNote
     * The preferred alternative to this method is {@link #orElseThrow()}.
     *
     * @return the non-{@code null} value described by this {@code AdditionalInfo}
     * @throws NoSuchElementException if no value is present
     */
    public T get() {
        if (value == null) {
            throw new NoSuchElementException("No value present");
        }
        return value;
    }

    /**
     * If a value is present, returns {@code true}, otherwise {@code false}.
     *
     * @return {@code true} if a value is present, otherwise {@code false}
     */
    public boolean isPresent() {
        return value != null;
    }

    /**
     * If a value is  not present, returns {@code true}, otherwise
     * {@code false}.
     *
     * @return  {@code true} if a value is not present, otherwise {@code false}
     */
    public boolean isEmpty() {
        return value == null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof AdditionalInfo)) {
            return false;
        }

        AdditionalInfo<?> other = (AdditionalInfo<?>) obj;
        return Objects.equals(value, other.value);
    }
}
