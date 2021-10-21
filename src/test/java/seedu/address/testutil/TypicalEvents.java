package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_DATE_BADMINTON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_DATE_CHESS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_BADMINTON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_CHESS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.module.event.Event;
import seedu.address.model.module.member.Member;

/**
 * A utility class containing a list of {@code Events} objects to be used in tests.
 */
public class TypicalEvents {

    public static final Event ICEBREAKER = new EventBuilder().withName("Icebreaker")
            .withDate("11/11/2022").withParticipants(TypicalMembers.ALICE, TypicalMembers.BENSON).build();
    public static final Event RUMBLING = new EventBuilder().withName("that scenery")
            .withDate("09/11/2022").withParticipants(TypicalMembers.FIONA, TypicalMembers.BENSON).build();
    public static final Event PERFORMANCE = new EventBuilder().withName("Performance")
            .withDate("01/12/2022").withParticipants(TypicalMembers.CARL).build();
    public static final Event OSMANTHUS_WINE = new EventBuilder().withName("Wine party")
            .withDate("11/11/2022").withParticipants(TypicalMembers.ALICE, TypicalMembers.AMY,
                    TypicalMembers.IDA).build();
    public static final Event BILLIARDS = new EventBuilder().withName("Billiards")
            .withDate("21/11/2022").withParticipants(TypicalMembers.ELLE, TypicalMembers.BENSON).build();
    public static final Event RED_LIGHT_GREEN_LIGHT = new EventBuilder().withName("Red light Green light")
            .withDate("09/12/2022").withParticipants(TypicalMembers.ALICE, TypicalMembers.BENSON,
                    TypicalMembers.GEORGE, TypicalMembers.HOON).build();
    public static final Event SYMPHONY_NO_9_ORCHESTRA = new EventBuilder().withName("Orchestra")
            .withDate("11/04/2023").withParticipants(TypicalMembers.BENSON).build();

    // Manually added
    public static final Event RIDER_WAR = new EventBuilder().withName("Henshin")
            .withDate("10/08/2023").withParticipants(TypicalMembers.GEORGE, TypicalMembers.DANIEL,
                    TypicalMembers.AMY).build();
    public static final Event ADVENTURE_TIME = new EventBuilder().withName("Its Adventure Time")
            .withDate("01/05/2023").withParticipants(TypicalMembers.GEORGE).build();
    // Manually added - Event's details found in {@code CommandTestUtil}
    public static final Event BADMINTON = new EventBuilder().withName(VALID_EVENT_NAME_BADMINTON)
            .withDate(VALID_EVENT_DATE_BADMINTON).build();
    public static final Event CHESS = new EventBuilder().withName(VALID_EVENT_NAME_CHESS)
            .withDate(VALID_EVENT_DATE_CHESS).build();

    private TypicalEvents() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical members and events.
     *
     * @return AddressBook with the typical members and events
     */
    public static AddressBook getTypicalAddressBookWithEvents() {
        AddressBook ab = new AddressBook();
        for (Member member : TypicalMembers.getTypicalMembers()) {
            ab.addMember(member);
        }
        for (Event event : getTypicalEvents()) {
            ab.addEvent(event);
        }
        return ab;
    }

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(ICEBREAKER, RUMBLING, PERFORMANCE, OSMANTHUS_WINE, BILLIARDS,
                RED_LIGHT_GREEN_LIGHT, SYMPHONY_NO_9_ORCHESTRA));
    }
}
