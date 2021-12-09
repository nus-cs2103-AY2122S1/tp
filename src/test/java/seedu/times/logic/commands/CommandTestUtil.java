package seedu.times.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.times.logic.commands.EditCommand.EditStudentDescriptor;
import static seedu.times.logic.commands.classcommands.EditClassCommand.EditClassDescriptor;
import static seedu.times.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.times.logic.parser.CliSyntax.PREFIX_CLASSTIMING;
import static seedu.times.logic.parser.CliSyntax.PREFIX_CLASS_NAME;
import static seedu.times.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.times.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.times.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.times.logic.parser.CliSyntax.PREFIX_NOK;
import static seedu.times.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.times.logic.parser.CliSyntax.PREFIX_RATE;
import static seedu.times.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.times.logic.parser.ParserUtil.FIND_REGEX_WITH_COMMA_DELIMITER;
import static seedu.times.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.times.commons.core.index.Index;
import seedu.times.logic.commands.exceptions.CommandException;
import seedu.times.model.Model;
import seedu.times.model.TimesTable;
import seedu.times.model.person.Student;
import seedu.times.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.times.model.tuitionclass.TuitionClass;
import seedu.times.model.tuitionclass.predicates.ClassNameContainsKeywordsPredicate;
import seedu.times.testutil.EditClassDescriptorBuilder;
import seedu.times.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    //// SAMPLE STUDENT INFO
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_NAME_NOK = "Papa";
    public static final String VALID_PHONE_NOK = "33333333";
    public static final String VALID_EMAIL_NOK = "papa@example.com";
    public static final String VALID_ADDRESS_NOK = "Block 456, Squid Street 69";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String VALID_PREFIX_NOK = " " + PREFIX_NOK;
    public static final String NAME_DESC_NOK = " " + PREFIX_NAME + VALID_NAME_NOK;
    public static final String PHONE_DESC_NOK = " " + PREFIX_PHONE + VALID_PHONE_NOK;
    public static final String EMAIL_DESC_NOK = " " + PREFIX_EMAIL + VALID_EMAIL_NOK;
    public static final String ADDRESS_DESC_NOK = " " + PREFIX_ADDRESS + VALID_ADDRESS_NOK;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses

    //// SAMPLE CLASS INFO
    public static final String VALID_CLASSNAME_IB_PHYSICS = "IB Physics";
    public static final String VALID_CLASSNAME_IB_MATHS = "IB Maths";
    public static final String VALID_CLASSTIMING_IB_PHYSICS = "tue 13:00-14:00";
    public static final String VALID_CLASSTIMING_IB_MATHS = "mon 10:00-11:00";
    public static final String VALID_RATE_IB_PHYSICS = "70";
    public static final String VALID_RATE_IB_MATHS = "80";
    public static final String VALID_LOCATION_IB_PHYSICS = "Block 312, Adams Street 1";
    public static final String VALID_LOCATION_IB_MATHS = "Block 123, Brown Street 3";
    public static final String[] VALID_STUDENTLIST_IB_PHYSICS = {"Benson Meier", "Alice Pauline"};
    public static final String[] VALID_STUDENTLIST_IB_MATHS = {"Carl Kurz", "Daniel Meier"};
    public static final String[] VALID_EMPTY_STUDENTLIST = { };

    public static final String CLASSNAME_DESC_PHYSICS = " " + PREFIX_CLASS_NAME + VALID_CLASSNAME_IB_PHYSICS;
    public static final String CLASSNAME_DESC_MATHS = " " + PREFIX_CLASS_NAME + VALID_CLASSNAME_IB_MATHS;
    public static final String RATE_DESC_PHYSICS = " " + PREFIX_RATE + VALID_RATE_IB_PHYSICS;
    public static final String RATE_DESC_MATHS = " " + PREFIX_RATE + VALID_RATE_IB_MATHS;
    public static final String CLASSTIMING_DESC_PHYSICS = " " + PREFIX_CLASSTIMING + VALID_CLASSTIMING_IB_PHYSICS;
    public static final String CLASSTIMING_DESC_MATHS = " " + PREFIX_CLASSTIMING + VALID_CLASSTIMING_IB_MATHS;
    public static final String LOCATION_DESC_PHYSICS = " " + PREFIX_LOCATION + VALID_LOCATION_IB_PHYSICS;
    public static final String LOCATION_DESC_MATHS = " " + PREFIX_LOCATION + VALID_LOCATION_IB_MATHS;
    public static final String CLASS_NAME_DESC_PHYSICS = " " + PREFIX_CLASS_NAME + VALID_CLASSNAME_IB_PHYSICS;
    public static final String CLASS_NAME_DESC_MATHS = " " + PREFIX_CLASS_NAME + VALID_CLASSNAME_IB_MATHS;

    public static final String INVALID_RATE_DESC = " " + PREFIX_RATE + "-32"; // negative rate not allowed for rate
    // empty string not allowed for classTiming
    public static final String INVALID_CLASSTIMING_DESC = " " + PREFIX_CLASSTIMING;
    public static final String INVALID_LOCATION_DESC = " " + PREFIX_LOCATION; // empty string not allowed for locations
    // non-alphanumeric not allowed for className
    public static final String INVALID_CLASSNAME_DESC = " " + PREFIX_CLASS_NAME + "IB_MATHS!";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditStudentDescriptor DESC_AMY;
    public static final EditCommand.EditStudentDescriptor DESC_BOB;
    public static final EditClassDescriptor DESC_IB_PHYSICS;
    public static final EditClassDescriptor DESC_IB_MATHS;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).withNokName(VALID_NAME_NOK).withNokPhone(VALID_PHONE_NOK)
                .withNokAddress(VALID_ADDRESS_NOK).withNokEmail(VALID_EMAIL_NOK).build();

        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).withNokName(VALID_NAME_NOK)
                .withNokPhone(VALID_PHONE_NOK).withNokAddress(VALID_ADDRESS_NOK)
                .withNokEmail(VALID_EMAIL_NOK).build();

        DESC_IB_PHYSICS = new EditClassDescriptorBuilder().withClassName(VALID_CLASSNAME_IB_PHYSICS)
                .withClassTiming(VALID_CLASSTIMING_IB_PHYSICS).withRate(VALID_RATE_IB_PHYSICS)
                .withLocation(VALID_LOCATION_IB_PHYSICS).withStudentList(VALID_STUDENTLIST_IB_PHYSICS).build();

        DESC_IB_MATHS = new EditClassDescriptorBuilder().withClassName(VALID_CLASSNAME_IB_MATHS)
                .withClassTiming(VALID_CLASSTIMING_IB_MATHS).withRate(VALID_RATE_IB_MATHS)
                .withLocation(VALID_LOCATION_IB_MATHS).withStudentList(VALID_STUDENTLIST_IB_PHYSICS).build();
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
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        TimesTable expectedTimesTable = new TimesTable(actualModel.getTimesTable());
        List<Student> expectedFilteredList = new ArrayList<>(actualModel.getFilteredStudentList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedTimesTable, actualModel.getTimesTable());
        assertEquals(expectedFilteredList, actualModel.getFilteredStudentList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the Student at the given {@code targetIndex} in the
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
     * Updates {@code model}'s filtered list to show only the classes at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showClassAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTuitionClassList().size());

        TuitionClass tuitionClass = model.getFilteredTuitionClassList().get(targetIndex.getZeroBased());
        final String[] splitName = tuitionClass.getClassName().toString().split(FIND_REGEX_WITH_COMMA_DELIMITER);
        model.updateFilteredClassList(new ClassNameContainsKeywordsPredicate(Arrays.asList(splitName)));

        assertEquals(1, model.getFilteredTuitionClassList().size());
    }

}
