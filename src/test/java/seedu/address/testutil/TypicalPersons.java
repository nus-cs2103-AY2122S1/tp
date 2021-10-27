package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_NOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_NOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_NOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_NOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Student;
import seedu.address.model.tuitionclass.ClassName;
import seedu.address.model.tuitionclass.ClassTiming;
import seedu.address.model.tuitionclass.Location;
import seedu.address.model.tuitionclass.Rate;
import seedu.address.model.tuitionclass.TuitionClass;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Student ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withNokName("Long Chai Boon")
            .withNokAddress("325, Clementi State 3, #40-32")
            .withNokEmail("longchatbooon@gmail.com").withNokPhone("67785914")
            .withTags("Maths").build();
    public static final Student BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withNokName("Short Chai Boon")
            .withNokAddress("325, West State 3, #60-12")
            .withNokEmail("hahiihi@gmail.com").withNokPhone("87759868")
            .withTags("Physics").build();
    public static final Student NO_NOK_BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .build();
    public static final Student CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("Campbell Road Ave 2, #11")
            .withNokName("Jamie Kurz")
            .withNokAddress("325, Bat Cave 3, #10-23")
            .withNokEmail("dontcallmeillcome@gmail.com").withNokPhone("62212222")
            .build();
    public static final Student DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street")
            .withNokName("Bad Chai Boon")
            .withNokAddress("1, Happy Sad 3, #10-12")
            .withNokEmail("happyorsadyouchoose@gmail.com").withNokPhone("88888888")
            .withTags("Maths").build();
    public static final Student ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave")
            .withNokName("Bernard Wan")
            .withNokAddress("222, Berkeys State 4, #11-32")
            .withNokEmail("bernard@gmail.com").withNokPhone("67785314").build();
    public static final Student FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo")
            .withNokName("Ong Lin Zhen")
            .withNokAddress("323, Lin Estate 3, #44-31")
            .withNokEmail("zhenngggii@gmail.com").withNokPhone("67382344")
            .build();
    public static final Student GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street")
            .withNokName("Hehe Chai Red")
            .withNokAddress("999, Estate State 4, #10-32")
            .withNokEmail("salmon@gmail.com").withNokPhone("67111111")
            .build();

    // Manually added
    public static final Student HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india")
            .build();
    public static final Student IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Student AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND)
            .withNokName(VALID_NAME_NOK).withNokPhone(VALID_PHONE_NOK).withNokAddress(VALID_ADDRESS_NOK)
            .withNokEmail(VALID_EMAIL_NOK).build();
    public static final Student BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withNokName(VALID_NAME_NOK).withNokPhone(VALID_PHONE_NOK).withNokAddress(VALID_ADDRESS_NOK)
            .withNokEmail(VALID_EMAIL_NOK)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Student student : getTypicalPersons()) {
            ab.addStudent(student);
        }

        return ab;
    }

    public static List<Student> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
