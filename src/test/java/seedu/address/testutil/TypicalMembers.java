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
import seedu.address.model.module.member.Member;

/**
 * A utility class containing a list of {@code Member} objects to be used in tests.
 */
public class TypicalMembers {

    public static final Member ALICE = new MemberBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withPositions("friends")
            .withTaskList(TypicalTasks.getTypicalTasks()).build();
    public static final Member BENSON = new MemberBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withPositions("owesMoney", "friends")
            .withTaskList(TypicalTasks.getTypicalTasks()).build();
    public static final Member CARL = new MemberBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").build();
    public static final Member DANIEL = new MemberBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withPositions("friends").build();
    public static final Member ELLE = new MemberBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final Member FIONA = new MemberBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final Member GEORGE = new MemberBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").build();

    // Manually added
    public static final Member HOON = new MemberBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Member IDA = new MemberBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // No email and no address
    public static final Member JERRY = new MemberBuilder().withName("Jerry Smith").withPhone("123456789")
            .withEmail(null).withAddress(null).build();

    // Manually added - Member's details found in {@code CommandTestUtil}
    public static final Member AMY = new MemberBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withPositions(VALID_POSITION_FRIEND).build();
    public static final Member BOB = new MemberBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .withPositions(VALID_POSITION_HUSBAND, VALID_POSITION_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalMembers() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical members.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Member member : getTypicalMembers()) {
            ab.addMember(member);
        }
        return ab;
    }

    public static List<Member> getTypicalMembers() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, JERRY, GEORGE));
    }
}
