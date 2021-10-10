package seedu.unify.testutil;

import static seedu.unify.logic.commands.CommandTestUtil.VALID_DATE_AMY;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.unify.model.UniFy;
import seedu.unify.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task ALICE = new TaskBuilder().withName("Alice Pauline")
            .withDate("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends").build();
    public static final Task BENSON = new TaskBuilder().withName("Benson Meier")
            .withDate("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Task CARL = new TaskBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withDate("wall street").build();
    public static final Task DANIEL = new TaskBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withDate("10th street").withTags("friends").build();
    public static final Task ELLE = new TaskBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withDate("michegan ave").build();
    public static final Task FIONA = new TaskBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withDate("little tokyo").build();
    public static final Task GEORGE = new TaskBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withDate("4th street").build();

    // Manually added
    public static final Task HOON = new TaskBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withDate("little india").build();
    public static final Task IDA = new TaskBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withDate("chicago ave").build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task AMY = new TaskBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withDate(VALID_DATE_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Task BOB = new TaskBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withDate(VALID_DATE_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalTasks() {} // prevents instantiation

    /**
     * Returns an {@code DateBook} with all the typical tasks.
     */
    public static UniFy getTypicalAddressBook() {
        UniFy ab = new UniFy();
        for (Task task : getTypicalTasks()) {
            ab.addTask(task);
        }
        return ab;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
