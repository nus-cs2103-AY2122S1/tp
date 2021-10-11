package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARENT_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_PHONE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.StudentBook;
import seedu.address.model.Model;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.Student;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {
    public static final String VALID_STUDENT_NAME_AMY = "Amy Bee";
    public static final String VALID_STUDENT_NAME_BOB = "Bob Choo";
    public static final String VALID_STUDENT_PHONE_AMY = "11111111";
    public static final String VALID_STUDENT_PHONE_BOB = "22222222";

    public static final String VALID_PARENT_NAME_AMY = "Mr Bee";
    public static final String VALID_PARENT_NAME_BOB = "Mrs Clara Choo";
    public static final String VALID_PARENT_PHONE_AMY = "33333333";
    public static final String VALID_PARENT_PHONE_BOB = "44444444";

    public static final String VALID_PROGRESS_BOB = "No Progress";
    public static final String VALID_PROGRESS_AMY = "No Progress";
    public static final boolean VALID_PAYMENT_STATUS_AMY = false;
    public static final boolean VALID_PAYMENT_STATUS_BOB = false;

    public static final String STUDENT_NAME_DESC_AMY = " " + PREFIX_STUDENT_NAME + VALID_STUDENT_NAME_AMY;
    public static final String STUDENT_NAME_DESC_BOB = " " + PREFIX_STUDENT_NAME + VALID_STUDENT_NAME_BOB;
    public static final String STUDENT_PHONE_DESC_AMY = " " + PREFIX_STUDENT_PHONE + VALID_STUDENT_PHONE_AMY;
    public static final String STUDENT_PHONE_DESC_BOB = " " + PREFIX_STUDENT_PHONE + VALID_STUDENT_PHONE_BOB;
    public static final String PARENT_NAME_DESC_AMY = " " + PREFIX_PARENT_NAME + VALID_PARENT_NAME_AMY;
    public static final String PARENT_NAME_DESC_BOB = " " + PREFIX_PARENT_NAME + VALID_PARENT_NAME_BOB;
    public static final String PARENT_PHONE_DESC_AMY = " " + PREFIX_PARENT_PHONE + VALID_PARENT_PHONE_AMY;
    public static final String PARENT_PHONE_DESC_BOB = " " + PREFIX_PARENT_PHONE + VALID_PARENT_PHONE_BOB;

    public static final String INVALID_STUDENT_NAME_DESC =
            " " + PREFIX_STUDENT_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_STUDENT_PHONE_DESC =
            " " + PREFIX_STUDENT_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_PARENT_NAME_DESC =
            " " + PREFIX_PARENT_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PARENT_PHONE_DESC =
            " " + PREFIX_PARENT_PHONE + "911a"; // 'a' not allowed in phones


    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

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
     * - the address book, filtered student list and selected student in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        StudentBook expectedStudentBook = new StudentBook(actualModel.getAddressBook());
        List<Student> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedStudentBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the student at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Student student = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = student.getStudentName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
