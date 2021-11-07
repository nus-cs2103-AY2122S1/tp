package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_DINNER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_PARTY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.event.Event;

/**
 * A utility class containing a list of {@code Event} objects to be used in tests.
 */
public class TypicalEvents {

    public static final Event PARTY_EVENT = new EventBuilder().withEventName(VALID_EVENT_NAME_PARTY).build();
    public static final Event DINNER_EVENT = new EventBuilder().withEventName(VALID_EVENT_NAME_DINNER).build();

    private TypicalEvents() {} // prevents instantiation

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(PARTY_EVENT));
    }
}
