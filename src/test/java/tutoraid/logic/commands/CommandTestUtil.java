package tutoraid.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tutoraid.commons.core.index.Index;
import tutoraid.logic.commands.exceptions.CommandException;
import tutoraid.logic.parser.CliSyntax;
import tutoraid.model.Model;
import tutoraid.model.StudentBook;
import tutoraid.model.student.NameContainsKeywordsPredicate;
import tutoraid.model.student.Student;
import tutoraid.testutil.Assert;
import tutoraid.testutil.EditStudentDescriptorBuilder;

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

    public static final String STUDENT_NAME_DESC_AMY = " " + CliSyntax.PREFIX_STUDENT_NAME + VALID_STUDENT_NAME_AMY;
    public static final String STUDENT_NAME_DESC_BOB = " " + CliSyntax.PREFIX_STUDENT_NAME + VALID_STUDENT_NAME_BOB;
    public static final String STUDENT_PHONE_DESC_AMY = " " + CliSyntax.PREFIX_STUDENT_PHONE + VALID_STUDENT_PHONE_AMY;
    public static final String STUDENT_PHONE_DESC_BOB = " " + CliSyntax.PREFIX_STUDENT_PHONE + VALID_STUDENT_PHONE_BOB;
    public static final String PARENT_NAME_DESC_AMY = " " + CliSyntax.PREFIX_PARENT_NAME + VALID_PARENT_NAME_AMY;
    public static final String PARENT_NAME_DESC_BOB = " " + CliSyntax.PREFIX_PARENT_NAME + VALID_PARENT_NAME_BOB;
    public static final String PARENT_PHONE_DESC_AMY = " " + CliSyntax.PREFIX_PARENT_PHONE + VALID_PARENT_PHONE_AMY;
    public static final String PARENT_PHONE_DESC_BOB = " " + CliSyntax.PREFIX_PARENT_PHONE + VALID_PARENT_PHONE_BOB;

    public static final String INVALID_STUDENT_NAME_DESC =
            " " + CliSyntax.PREFIX_STUDENT_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_STUDENT_PHONE_DESC =
            " " + CliSyntax.PREFIX_STUDENT_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_PARENT_NAME_DESC =
            " " + CliSyntax.PREFIX_PARENT_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PARENT_PHONE_DESC =
            " " + CliSyntax.PREFIX_PARENT_PHONE + "911a"; // 'a' not allowed in phones


    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditStudentCommand.EditStudentDescriptor DESC_AMY;
    public static final EditStudentCommand.EditStudentDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditStudentDescriptorBuilder()
            .withStudentName(VALID_STUDENT_NAME_AMY)
            .withStudentPhone(VALID_STUDENT_PHONE_AMY)
            .withParentPhone(VALID_PARENT_PHONE_AMY)
            .withParentName(VALID_PARENT_NAME_AMY)
            .build();
        DESC_BOB = new EditStudentDescriptorBuilder()
            .withStudentName(VALID_STUDENT_NAME_BOB)
            .withStudentPhone(VALID_STUDENT_PHONE_BOB)
            .withParentPhone(VALID_PARENT_PHONE_BOB)
            .withParentName(VALID_PARENT_NAME_BOB)
            .build();
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
     * - the student book, filtered student list and selected student in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        StudentBook expectedStudentBook = new StudentBook(actualModel.getStudentBook());
        List<Student> expectedFilteredList = new ArrayList<>(actualModel.getFilteredStudentList());

        Assert.assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedStudentBook, actualModel.getStudentBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredStudentList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the student at the given {@code targetIndex} in the
     * {@code model}'s student book.
     */
    public static void showStudentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredStudentList().size());

        Student student = model.getFilteredStudentList().get(targetIndex.getZeroBased());
        final String[] splitName = student.getStudentName().fullName.split("\\s+");
        model.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredStudentList().size());
    }

}
