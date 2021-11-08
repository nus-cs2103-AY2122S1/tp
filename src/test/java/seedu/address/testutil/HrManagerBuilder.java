package seedu.address.testutil;

import seedu.address.model.HrManager;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Person;
import seedu.address.model.position.Position;

/**
 * A utility class to help with building HrManager objects.
 * Example usage: <br>
 *     {@code HrManager hr = new HrManagerBuilder().withPerson("John", "Doe").build();}
 */
public class HrManagerBuilder {

    private HrManager hrManager;

    public HrManagerBuilder() {
        hrManager = new HrManager();
    }

    public HrManagerBuilder(HrManager hrManager) {
        this.hrManager = hrManager;
    }

    /**
     * Adds a new {@code Person} to the {@code HrManager} that we are building.
     */
    public HrManagerBuilder withPerson(Person person) {
        hrManager.addPerson(person);
        return this;
    }

    /**
     * Adds a new {@code Position} to the {@code HrManager} that we are building.
     */
    public HrManagerBuilder withPosition(Position position) {
        hrManager.addPosition(position);
        return this;
    }

    /**
     * Adds a new {@code Interview} to the {@code HrManager} that we are building.
     */
    public HrManagerBuilder withInterview(Interview interview) {
        hrManager.addInterview(interview);
        return this;
    }

    public HrManager build() {
        return hrManager;
    }
}
