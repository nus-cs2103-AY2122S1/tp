package seedu.address.storage;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.interests.Interest;

public class CsvAdaptedInterest {

    private final String interest;
    /**
     * Constructs a {@code CsvAdaptedInterest} with the given {@code interest}.
     */
    public CsvAdaptedInterest(String interest) {
        this.interest = interest;
    }

    /**
     * Converts a given {@code Interest}
     */
    public CsvAdaptedInterest(Interest source) {
        interest = source.value;
    }

    public String getInterest() {
        return interest;
    }

    /**
     * Converts this Csv adapted tag object into the model's {@code Interest} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted interest.
     */
    public Interest toModelType() throws IllegalValueException {
        if (!Interest.isValidInterest(interest)) {
            throw new IllegalValueException(Interest.MESSAGE_CONSTRAINTS);
        }
        return new Interest(interest);
    }

}
