package safeforhall.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import safeforhall.model.AddressBook;
import safeforhall.model.event.Event;
import safeforhall.model.event.ResidentList;

/**
 * A utility class containing a list of {@code Event} objects to be used in tests.
 */
public class TypicalEvents {
    public static final Event BASKETBALL = new EventBuilder().withEventName("basketball")
            .withEventDate("10-10-2021")
            .withVenue("basketball court")
            .withEventTime("0830")
            .withCapacity("1")
            .withResidentList(ResidentList.DEFAULT_LIST, ResidentList.DEFAULT_LIST)
            .build();
    public static final Event VOLLEYBALL = new EventBuilder().withEventName("volleyball")
            .withEventDate("12-10-2021")
            .withEventTime("0800")
            .withVenue("volleyball court")
            .withCapacity("6")
            .withResidentList(TypicalPersons.DANIEL.getName().toString(), TypicalPersons.DANIEL.toString())
            .build();
    public static final Event POWERLIFTING = new EventBuilder().withEventName("powerlifting")
            .withEventDate("15-10-2021")
            .withEventTime("0830")
            .withVenue("gym")
            .withCapacity("7")
            .withResidentList(TypicalPersons.ELLE.getName().toString()
                            + ", " + TypicalPersons.FIONA.getName().toString()
                            + ", " + TypicalPersons.GEORGE.getName().toString(),
                    TypicalPersons.ELLE.toString()
                            + ", " + TypicalPersons.FIONA.toString()
                            + ", " + TypicalPersons.GEORGE.toString())
            .build();
    public static final Event SWIM_WO_RESIDENTS = new EventBuilder().withEventName("Swim Training")
            .withEventDate("19-10-2021")
            .withEventTime("0800")
            .withVenue("Pool")
            .withCapacity("15")
            .build();
    public static final Event SWIM = new EventBuilder().withEventName("swim")
            .withEventDate("10-09-2021")
            .withEventTime("0800")
            .withVenue("swimming pool")
            .withCapacity("8")
            .withResidentList(TypicalPersons.GEORGE.getName().toString(), TypicalPersons.GEORGE.toString())
            .build();
    public static final Event BAND = new EventBuilder().withEventName("band")
            .withEventDate("10-11-2021")
            .withEventTime("0830")
            .withVenue("band room")
            .withCapacity("9")
            .withResidentList(TypicalPersons.CARL.getName().toString()
                            + ", " + TypicalPersons.BENSON.getName().toString(),
                    TypicalPersons.CARL.toString()
                            + ", " + TypicalPersons.BENSON.toString())
            .build();
    public static final Event ROAD_RELAY = new EventBuilder().withEventName("road relay")
            .withEventDate("12-12-2021")
            .withEventTime("1830")
            .withVenue("track")
            .withCapacity("10")
            .withResidentList(TypicalPersons.GEORGE.getName().toString(), TypicalPersons.GEORGE.toString())
            .build();
    public static final Event DANCE = new EventBuilder().withEventName("dance")
            .withEventDate("01-10-2021")
            .withEventTime("1230")
            .withVenue("studio")
            .withCapacity("11")
            .withResidentList(TypicalPersons.GEORGE.getName().toString()
                            + ", " + TypicalPersons.CARL.getName().toString()
                            + ", " + TypicalPersons.BENSON.getName().toString(),
                    TypicalPersons.GEORGE.toString()
                            + ", " + TypicalPersons.CARL.toString()
                            + ", " + TypicalPersons.BENSON.toString())
            .build();

    // Manually added
    public static final Event POOL = new EventBuilder().withEventName("pool")
            .withEventDate("10-11-2021")
            .withEventTime("1800")
            .withVenue("pool room")
            .withCapacity("10")
            .withResidentList(TypicalPersons.GEORGE.getName().toString()
                            + ", " + TypicalPersons.CARL.getName().toString(),
                    TypicalPersons.GEORGE.toString()
                            + ", " + TypicalPersons.CARL.toString())
            .build();

    public static final Event HACKERS = new EventBuilder().withEventName("hackers")
            .withEventDate("01-10-2021")
            .withEventTime("2000")
            .withVenue("lab")
            .withCapacity("20")
            .withResidentList(TypicalPersons.BENSON.getName().toString(), TypicalPersons.BENSON.toString())
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalEvents() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Event event : getTypicalEvents()) {
            ab.addEvent(event);
        }
        return ab;
    }

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(BASKETBALL, VOLLEYBALL, POWERLIFTING, SWIM, BAND, ROAD_RELAY, DANCE));
    }
}
