package seedu.address.testutil;

import seedu.address.model.Internship;
import seedu.address.model.application.Application;

/**
 * A utility class to help with building Internship objects.
 * Example usage: <br>
 *     {@code Internship internship = new InternshipBuilder().withApplication("Shopee", "Grab").build();}
 */
public class InternshipBuilder {

    private Internship internship;

    public InternshipBuilder() {
        internship = new Internship();
    }

    public InternshipBuilder(Internship internship) {
        this.internship = internship;
    }

    /**
     * Adds a new {@code Application} to the {@code Internship} that we are building.
     */
    public InternshipBuilder withApplication(Application application) {
        internship.addApplication(application);
        return this;
    }

    public Internship build() {
        return internship;
    }
}
