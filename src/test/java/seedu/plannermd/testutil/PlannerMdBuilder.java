package seedu.plannermd.testutil;

import seedu.plannermd.model.PlannerMd;
import seedu.plannermd.model.person.Person;

/**
 * A utility class to help with building PlannerMd objects.
 * Example usage: <br>
 *     {@code PlannerMd ab = new PlannerMdBuilder().withPerson("John", "Doe").build();}
 */
public class PlannerMdBuilder {

    private PlannerMd plannerMd;

    public PlannerMdBuilder() {
        plannerMd = new PlannerMd();
    }

    public PlannerMdBuilder(PlannerMd plannerMd) {
        this.plannerMd = plannerMd;
    }

    /**
     * Adds a new {@code Person} to the {@code PlannerMd} that we are building.
     */
    public PlannerMdBuilder withPerson(Person person) {
        plannerMd.addPerson(person);
        return this;
    }

    public PlannerMd build() {
        return plannerMd;
    }
}
