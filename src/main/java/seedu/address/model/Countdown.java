package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

/**
 * Represents a Countdown of the wedding day.
 * Guarantees: details are present and not null.
 */
public class Countdown implements ReadOnlyCountdown {

    // Date fields
    private LocalDate weddingDate;

    public Countdown() {
        this.weddingDate = LocalDate.now();
    }

    /**
     * Creates a Countdown using the LocalDate in the {@code toBeCopied}
     */
    public Countdown(ReadOnlyCountdown toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Resets the existing data of this {@code Countdown} with {@code newData}.
     */
    public void resetData(ReadOnlyCountdown newData) {
        requireNonNull(newData);

        setDate(newData.getDate());
    }

    /**
     * Replaces the contents of the date with {@code weddingDate}.
     */
    public void setDate(LocalDate weddingDate) {
        this.weddingDate = weddingDate;
    }

    /**
     * Returns an unmodifiable view of the wedding date.
     */
    @Override
    public LocalDate getDate() {
        return weddingDate;
    }
}
