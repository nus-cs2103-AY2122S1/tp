package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.data.event.Event;
import seedu.address.model.data.member.Member;

/**
 * A utility class containing a list of {@code Events} objects to be used in tests.
 */
public class TypicalEvents {

    public static final Event ICEBREAKER = new EventBuilder().withName("Icebreaker")
            .withDate("11-11-2022").withParticipants(TypicalMembers.ALICE, TypicalMembers.BENSON).build();
    public static final Event LAUNCH_DATE = new EventBuilder().withName("Lunch date")
            .withDate("09-11-2022").withParticipants(TypicalMembers.FIONA, TypicalMembers.BENSON).build();
    public static final Event PERFORMANCE = new EventBuilder().withName("Performance")
            .withDate("01-12-2022").withParticipants(TypicalMembers.CARL).build();
    public static final Event ICEBREAKER = new EventBuilder().withName("Alice Pauline")
            .withDate("11-11-2022").withParticipants(TypicalMembers.ALICE, TypicalMembers.BENSON).build();
    public static final Event ICEBREAKER = new EventBuilder().withName("Alice Pauline")
            .withDate("11-11-2022").withParticipants(TypicalMembers.ALICE, TypicalMembers.BENSON).build();
    public static final Event ICEBREAKER = new EventBuilder().withName("Alice Pauline")
            .withDate("11-11-2022").withParticipants(TypicalMembers.ALICE, TypicalMembers.BENSON).build();
    public static final Event ICEBREAKER = new EventBuilder().withName("Alice Pauline")
            .withDate("11-11-2022").withParticipants(TypicalMembers.ALICE, TypicalMembers.BENSON).build();

    // Manually added
    public static final Member HOON = new MemberBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Member IDA = new MemberBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();
    // Manually added - Member's details found in {@code CommandTestUtil}
    public static final Member AMY = new MemberBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withPositions(VALID_POSITION_FRIEND).build();
    public static final Member BOB = new MemberBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .withPositions(VALID_POSITION_HUSBAND, VALID_POSITION_FRIEND)
            .build();

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
        for(Event event : getTypicalEvents()) {
            ab.addEvent(event);
        }
        return ab;
    }

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
