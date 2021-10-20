package safeforhall.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import safeforhall.model.AddressBook;
import safeforhall.model.event.Event;

/**
 * A utility class containing a list of {@code Event} objects to be used in tests.
 */
public class TypicalEvents {
    public static final Event FOOTBALL_TRAINING = new EventBuilder().withName("Football Training")
            .withDate("20-10-2021")
            .withVenue("Field")
            .withCapacity("20")
            .build();
    public static final Event SWIM_TRAINING = new EventBuilder().withName("Swim Training")
            .withDate("19-10-2021")
            .withVenue("Pool")
            .withCapacity("15")
            .build();
    public static final Event BASKETBALL_TRAINING = new EventBuilder().withName("Basketball Training")
            .withDate("21-10-2021")
            .withVenue("Basketball Court")
            .withCapacity("10")
            .build();

    private TypicalEvents() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical events.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Event event : getTypicalEvents()) {
            ab.addEvent(event);
        }
        return ab;
    }

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(FOOTBALL_TRAINING, SWIM_TRAINING, BASKETBALL_TRAINING));
    }
}
