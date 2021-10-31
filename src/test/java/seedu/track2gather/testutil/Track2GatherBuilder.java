package seedu.track2gather.testutil;

import seedu.track2gather.model.Track2Gather;
import seedu.track2gather.model.person.Person;

/**
 * A utility class to help with building Track2Gather objects.
 * Example usage: <br>
 *     {@code Track2Gather ab = new Track2GatherBuilder().withPerson("John", "Doe").build();}
 */
public class Track2GatherBuilder {

    private Track2Gather track2Gather;

    public Track2GatherBuilder() {
        track2Gather = new Track2Gather();
    }

    public Track2GatherBuilder(Track2Gather track2Gather) {
        this.track2Gather = track2Gather;
    }

    /**
     * Adds a new {@code Person} to the {@code Track2Gather} that we are building.
     */
    public Track2GatherBuilder withPerson(Person person) {
        track2Gather.addPerson(person);
        return this;
    }

    public Track2Gather build() {
        return track2Gather;
    }
}
