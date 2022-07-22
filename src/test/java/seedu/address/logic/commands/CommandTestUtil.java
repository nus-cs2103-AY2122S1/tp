package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LIMIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.NameContainsKeywordsPredicate;
import seedu.address.model.student.Student;
import seedu.address.model.tuition.TuitionClass;
import seedu.address.testutil.EditClassDescriptorBuilder;
import seedu.address.testutil.EditStudentDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_REMARK_AMY = "Like skiing.";
    public static final String VALID_REMARK_BOB = "Favourite pastime: Eating";
    public static final String VALID_REMARK_CHEMISTRY = "H2O is water";
    public static final String VALID_REMARK_PHYSICS = "Force equals mass times acceleration.";
    public static final String VALID_LIMIT_FIVE = "5";
    public static final String VALID_LIMIT_ONE = "1";
    public static final String VALID_CLASS_MON = "Mon 10:00-12:00";
    public static final String VALID_CLASS_SUN = "Sun 10:00-11:00";
    public static final String VALID_CLASS_TUE = "Tue 23:00-23:30";
    public static final String VALID_CLASS_PHY = "Physics";
    public static final String VALID_CLASS_CHEM = "Chemistry";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String REMARK_DESC_AMY = PREFIX_REMARK + VALID_REMARK_AMY;
    public static final String REMARK_DESC_BOB = PREFIX_REMARK + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String CLASSLIMIT_DESC_ONE = " " + PREFIX_LIMIT + VALID_LIMIT_ONE;
    public static final String CLASSLIMIT_DESC_FIVE = " " + PREFIX_LIMIT + VALID_LIMIT_FIVE;
    public static final String CLASSNAME_DESC_PHYSICS = " " + PREFIX_NAME + VALID_CLASS_PHY;
    public static final String CLASSNAME_DESC_CHEM = " " + PREFIX_NAME + VALID_CLASS_CHEM;
    public static final String TIMESLOT_DESC_MONDAY = " " + PREFIX_TIMESLOT + VALID_CLASS_MON;
    public static final String TIMESLOT_DESC_TUESDAY = " " + PREFIX_TIMESLOT + VALID_CLASS_TUE;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_LIMIT_POSITIVE = " " + PREFIX_LIMIT + "1001";
    public static final String INVALID_LIMIT_NEGATIVE = " " + PREFIX_LIMIT + "-1";
    public static final String INVALID_TIMESLOT_DAY = " " + PREFIX_TIMESLOT + "Thurs 11:00-12:00";
    public static final String INVALID_TIMESLOT_START = " " + PREFIX_TIMESLOT + "Mon 14:02-14:00";
    public static final String INVALID_TIMESLOT_EQUAL = " " + PREFIX_TIMESLOT + "Mon 14:00-14:00";
    public static final String INVALID_TIMESLOT_END = " " + PREFIX_TIMESLOT + "Mon 23:58-24:00";
    public static final String INVALID_TIMESLOT_HOUR = " " + PREFIX_TIMESLOT + "Mon 11:00-25:30";
    public static final String INVALID_TIMESLOT_MINUTE = " " + PREFIX_TIMESLOT + "Mon 11:90-12:00";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = " NonEmptyPreamble ";
    public static final String PREAMBLE_INTEGER = " 1 ";
    public static final String PREAMBLE_SYMBOL = " $$ ";

    public static final EditCommand.EditStudentDescriptor DESC_AMY;
    public static final EditCommand.EditStudentDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .build();
        DESC_BOB = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .build();
    }

    public static final EditClassCommand.EditClassDescriptor DESC_CHEM;
    public static final EditClassCommand.EditClassDescriptor DESC_PHY;

    static {
        DESC_CHEM = new EditClassDescriptorBuilder().withName(VALID_CLASS_CHEM).withTimeslot(VALID_CLASS_MON)
                .withLimit(VALID_LIMIT_FIVE).build();
        DESC_PHY = new EditClassDescriptorBuilder().withName(VALID_CLASS_PHY).withTimeslot(VALID_CLASS_TUE)
                .withLimit(VALID_LIMIT_ONE).build();
    }


    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage) {
        try {
            CommandResult expectedCommandResult = new CommandResult(expectedMessage);
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered student list and selected student in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Student> expectedFilteredList = new ArrayList<>(actualModel.getFilteredStudentList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredStudentList());
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered student list and selected student in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailureWithoutException(Command command, Model actualModel,
                                                            String expectedMessage) {
        try {
            CommandResult expectedCommandResult = new CommandResult(expectedMessage);
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should fail.", ce);
        }
    }
    /**
     * Updates {@code model}'s filtered list to show only the student at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showStudentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredStudentList().size());

        Student student = model.getFilteredStudentList().get(targetIndex.getZeroBased());
        final String[] splitName = student.getName().fullName.split("\\s+");
        model.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredStudentList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the tuition class at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showTuitionClassAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTuitionList().size());

        TuitionClass tuitionClass = model.getFilteredTuitionList().get(targetIndex.getZeroBased());
        final String[] splitName = tuitionClass.getName().name.split("\\s+");
        model.updateFilteredTuitionList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredTuitionList().size());
    }

}
