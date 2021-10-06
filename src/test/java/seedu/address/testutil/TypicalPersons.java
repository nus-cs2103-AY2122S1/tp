package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.question.Question;

/**
 * A utility class containing a list of {@code Question} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Question ALICE = new QuestionBuilder().withName("Alice Pauline")
            .withPhone("94351253")
            .withTags("friends").build();
    public static final Question BENSON = new QuestionBuilder().withName("Benson Meier")
            .withPhone("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Question CARL = new QuestionBuilder().withName("Carl Kurz").withPhone("95352563")
            .build();
    public static final Question DANIEL = new QuestionBuilder().withName("Daniel Meier").withPhone("87652533")
            .withTags("friends").build();
    public static final Question ELLE = new QuestionBuilder().withName("Elle Meyer").withPhone("9482224")
            .build();
    public static final Question FIONA = new QuestionBuilder().withName("Fiona Kunz").withPhone("9482427")
            .build();
    public static final Question GEORGE = new QuestionBuilder().withName("George Best").withPhone("9482442")
            .build();

    // Manually added
    public static final Question HOON = new QuestionBuilder().withName("Hoon Meier").withPhone("8482424")
            .build();
    public static final Question IDA = new QuestionBuilder().withName("Ida Mueller").withPhone("8482131")
            .build();

    // Manually added - Question's details found in {@code CommandTestUtil}
    public static final Question AMY = new QuestionBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Question BOB = new QuestionBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Question question : getTypicalPersons()) {
            ab.addQuestion(question);
        }
        return ab;
    }

    public static List<Question> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
