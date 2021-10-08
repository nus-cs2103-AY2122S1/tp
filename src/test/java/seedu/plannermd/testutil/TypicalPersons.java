package seedu.plannermd.testutil;

import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_BIRTH_DATE_AMY;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_BIRTH_DATE_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import seedu.plannermd.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in
 * tests.
 */
public class TypicalPersons {
    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withRemark("Awaiting PCR test result").withTags("friends")
            .withBirthDate("20/07/1964").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25").withBirthDate("20/07/1964")
            .withEmail("johnd@example.com").withPhone("98765432").withRemark("Hypochondriac")
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").withRemark("Prefers liquid medication")
            .withBirthDate("20/07/1965").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withBirthDate("20/07/1964")
            .withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").withBirthDate("19/05/1955")
            .build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").withBirthDate("20/08/1948").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").withBirthDate("9/1/1944").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").withBirthDate("3/01/1946").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").withBirthDate("31/05/1948").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withBirthDate(VALID_BIRTH_DATE_AMY)
            .withRemark(VALID_REMARK_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withBirthDate(VALID_BIRTH_DATE_BOB)
            .withRemark(VALID_REMARK_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {
    } // prevents instantiation
}
