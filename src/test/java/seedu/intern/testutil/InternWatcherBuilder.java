package seedu.intern.testutil;

import seedu.intern.model.InternWatcher;
import seedu.intern.model.applicant.Applicant;

/**
 * A utility class to help with building InternWatcher objects.
 * Example usage: <br>
 *     {@code InternWatcher ab = new InternWatcherBuilder().withApplicant("John", "Doe").build();}
 */
public class InternWatcherBuilder {

    private InternWatcher internWatcher;

    public InternWatcherBuilder() {
        internWatcher = new InternWatcher();
    }

    public InternWatcherBuilder(InternWatcher internWatcher) {
        this.internWatcher = internWatcher;
    }

    /**
     * Adds a new {@code Applicant} to the {@code InternWatcher} that we are building.
     */
    public InternWatcherBuilder withApplicant(Applicant applicant) {
        internWatcher.addApplicant(applicant);
        return this;
    }

    public InternWatcher build() {
        return internWatcher;
    }
}
