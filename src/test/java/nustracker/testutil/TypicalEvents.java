package nustracker.testutil;

import static nustracker.logic.commands.CommandTestUtil.VALID_EVENTDATE_FINAL;
import static nustracker.logic.commands.CommandTestUtil.VALID_EVENTDATE_TEST;
import static nustracker.logic.commands.CommandTestUtil.VALID_EVENTNAME_FINAL;
import static nustracker.logic.commands.CommandTestUtil.VALID_EVENTNAME_TEST;
import static nustracker.logic.commands.CommandTestUtil.VALID_EVENTTIME_FINAL;
import static nustracker.logic.commands.CommandTestUtil.VALID_EVENTTIME_TEST;
import static nustracker.logic.commands.CommandTestUtil.VALID_PARTICIPANT_FINAL;
import static nustracker.logic.commands.CommandTestUtil.VALID_PARTICIPANT_TEST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nustracker.model.event.Event;
import nustracker.model.event.EventName;

/**
 * A utility class containing a list of {@code Event} objects to be used in tests.
 */
public class TypicalEvents {

    public static final Event ORIENTATION = new EventBuilder().withName("Orientation Camp")
            .withDate("01-08-2022")
            .withTime("0900")
            .withParticipants("e9123119", "e9192390")
            .withBlacklist("e9382710").build();
    public static final Event SPORTS_CAMP = new EventBuilder().withName("Sports Camp")
            .withDate("10-09-2021")
            .withTime("1200")
            .withParticipants("e9831818")
            .withBlacklist("e0456789", "e0123456").build();
    public static final Event MATH_OLYMPIAD = new EventBuilder().withName("Math Olympiad")
            .withDate("05-03-2017")
            .withTime("1615")
            .withParticipants("e0322322", "e9123119", "e0123456")
            .withBlacklist().build();

    // For delete event tests
    public static final EventName EVENTNAME_ONE = new EventName("Orientation Camp");
    public static final EventName EVENTNAME_TWO = new EventName("Sports Camp");
    public static final EventName EVENTNAME_THREE = new EventName("Math Olympiad");
    public static final EventName EVENTNAME_INVALID = new EventName("does not exist");

    // Manually added - Event's details found in {@code CommandTestUtil}
    public static final Event TEST = new EventBuilder().withName(VALID_EVENTNAME_TEST).withDate(VALID_EVENTDATE_TEST)
            .withTime(VALID_EVENTTIME_TEST).withParticipants(VALID_PARTICIPANT_TEST)
            .withBlacklist(VALID_PARTICIPANT_FINAL).build();
    public static final Event FINAL = new EventBuilder().withName(VALID_EVENTNAME_FINAL).withDate(VALID_EVENTDATE_FINAL)
            .withTime(VALID_EVENTTIME_FINAL).withParticipants(VALID_PARTICIPANT_FINAL)
            .withBlacklist(VALID_PARTICIPANT_TEST).build();

    private TypicalEvents() {} // prevents instantiation

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(ORIENTATION, SPORTS_CAMP, MATH_OLYMPIAD));
    }
}
