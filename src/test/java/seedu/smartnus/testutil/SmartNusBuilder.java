package seedu.smartnus.testutil;

import seedu.smartnus.model.SmartNus;
import seedu.smartnus.model.question.Question;

/**
 * A utility class to help with building SmartNus objects.
 * Example usage: <br>
 *     {@code SmartNus smartNus = new SmartNusBuilder().build();}
 */
public class SmartNusBuilder {

    private SmartNus smartNus;

    public SmartNusBuilder() {
        smartNus = new SmartNus();
    }

    public SmartNusBuilder(SmartNus smartNus) {
        this.smartNus = smartNus;
    }

    /**
     * Adds a new {@code Question} to the {@code SmartNus} that we are building.
     */
    public SmartNusBuilder withQuestion(Question question) {
        smartNus.addQuestion(question);
        return this;
    }

    public SmartNus build() {
        return smartNus;
    }
}
