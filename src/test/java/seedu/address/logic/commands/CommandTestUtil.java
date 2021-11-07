package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSESSMENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM_HANDLE;
import static seedu.address.model.assessment.Score.SCORE_DELIMITER;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.CsBook;
import seedu.address.model.Model;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.Student;
import seedu.address.testutil.EditStudentDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_TELEGRAM_HANDLE_AMY = "@amy_bee";
    public static final String VALID_TELEGRAM_HANDLE_BOB = "@bob_choo";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";

    public static final String VALID_GROUP_NAME_CS2103T = "CS2103T";
    public static final String VALID_GROUP_NAME_CS1231S = "CS1231S";
    public static final String VALID_GROUP_NAME_CS1101S = "CS1101S";
    public static final String VALID_GROUP_NAME_CS2030S = "CS2030S";
    public static final String VALID_GROUP_NAME_CS2040S = "CS2040S";

    public static final String VALID_GROUP_DESCRIPTION = "Software eng mod";
    public static final String VALID_GROUP_DESCRIPTION_CS1231S = "Discrete Structures";
    public static final String VALID_GROUP_DESCRIPTION_CS1101S = "Programming Methodology I";
    public static final String VALID_GROUP_DESCRIPTION_CS2030S = "Programming Methodology II";
    public static final String VALID_GROUP_DESCRIPTION_CS2040S = "Data Structures and Algorithms";

    public static final String VALID_GROUP_NAME_AMY = "CS2103T";
    public static final String VALID_DESC_AMY = "Software eng mod";
    public static final String VALID_GROUP_NAME_BOB = "CS2103T";
    public static final String VALID_DESC_BOB = "Software eng mod";

    public static final String VALID_ASSESSMENT_NAME_QUIZ1 = "Quiz 1";
    public static final String VALID_ASSESSMENT_NAME_LAB5 = "Lab 5";
    public static final int VALID_ACTUAL_SCORE_QUIZ1 = 40;
    public static final int VALID_ACTUAL_SCORE_LAB5 = 5;
    public static final int VALID_TOTAL_SCORE_QUIZ1 = 50;
    public static final int VALID_TOTAL_SCORE_LAB5 = 10;
    public static final String VALID_SCORE_QUIZ1 = VALID_ACTUAL_SCORE_QUIZ1 + SCORE_DELIMITER + VALID_TOTAL_SCORE_QUIZ1;
    public static final String VALID_SCORE_LAB5 = VALID_ACTUAL_SCORE_LAB5 + SCORE_DELIMITER + VALID_TOTAL_SCORE_LAB5;

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String TELEGRAM_HANDLE_DESC_AMY = " " + PREFIX_TELEGRAM_HANDLE + VALID_TELEGRAM_HANDLE_AMY;
    public static final String TELEGRAM_HANDLE_DESC_BOB = " " + PREFIX_TELEGRAM_HANDLE + VALID_TELEGRAM_HANDLE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String GROUP_NAME_DESC_AMY = " " + PREFIX_GROUP_NAME + VALID_GROUP_NAME_AMY;
    public static final String GROUP_NAME_DESC_BOB = " " + PREFIX_GROUP_NAME + VALID_GROUP_NAME_BOB;
    public static final String ASSESSMENT_NAME_DESC_QUIZ1 = " " + PREFIX_ASSESSMENT_NAME + VALID_ASSESSMENT_NAME_QUIZ1;
    public static final String ASSESSMENT_NAME_DESC_LAB5 = " " + PREFIX_ASSESSMENT_NAME + VALID_ASSESSMENT_NAME_LAB5;
    public static final String SCORE_DESC_QUIZ1 = " " + PREFIX_SCORE + VALID_SCORE_QUIZ1;
    public static final String SCORE_DESC_LAB5 = " " + PREFIX_SCORE + VALID_SCORE_LAB5;

    public static final String VALID_GROUP_NAME_CS2103T_DESC = " " + PREFIX_GROUP_NAME + VALID_GROUP_NAME_CS2103T;
    public static final String VALID_GROUP_NAME_CS1231S_DESC = " " + PREFIX_GROUP_NAME + VALID_GROUP_NAME_CS1231S;
    public static final String VALID_GROUP_NAME_CS1101S_DESC = " " + PREFIX_GROUP_NAME + VALID_GROUP_NAME_CS1101S;
    public static final String VALID_GROUP_NAME_CS2030S_DESC = " " + PREFIX_GROUP_NAME + VALID_GROUP_NAME_CS2030S;
    public static final String VALID_GROUP_NAME_CS2040S_DESC = " " + PREFIX_GROUP_NAME + VALID_GROUP_NAME_CS2040S;
    public static final String VALID_GROUP_DESCRIPTION_DESC = " " + PREFIX_DESCRIPTION + VALID_GROUP_DESCRIPTION;

    public static final String VALID_GROUP_DESCRIPTION_CS1231S_DESC = " " + PREFIX_DESCRIPTION
            + VALID_GROUP_DESCRIPTION_CS1231S;
    public static final String VALID_GROUP_DESCRIPTION_CS1101S_DESC = " " + PREFIX_DESCRIPTION
            + VALID_GROUP_DESCRIPTION_CS1101S;
    public static final String VALID_GROUP_DESCRIPTION_CS2030S_DESC = " " + PREFIX_DESCRIPTION
            + VALID_GROUP_DESCRIPTION_CS2030S;
    public static final String VALID_GROUP_DESCRIPTION_CS2040S_DESC = " " + PREFIX_DESCRIPTION
            + VALID_GROUP_DESCRIPTION_CS2040S;


    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_TELEGRAM_HANDLE_DESC = " " + PREFIX_TELEGRAM_HANDLE
            + "911a"; // 'a' not allowed in telegram handles
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ASSESSMENT_NAME_DESC = " " + PREFIX_ASSESSMENT_NAME
            + "Fin@ls"; // '@' not allowed in assessment names
    public static final String INVALID_SCORE_DESC = " " + PREFIX_SCORE + "60100"; // missing delimiter
    public static final String INVALID_GROUP_NAME_DESC = " " + PREFIX_GROUP_NAME + "!$%";
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditStudentDescriptor DESC_AMY;
    public static final EditCommand.EditStudentDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withTelegramHandle(VALID_TELEGRAM_HANDLE_AMY).withEmail(VALID_EMAIL_AMY).build();
        DESC_BOB = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withTelegramHandle(VALID_TELEGRAM_HANDLE_BOB).withEmail(VALID_EMAIL_BOB).build();
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
     * - the address book, filtered student list and selected student in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        CsBook expectedCsBook = new CsBook(actualModel.getCsBook());
        List<Student> expectedFilteredList = new ArrayList<>(actualModel.getFilteredStudentList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedCsBook, actualModel.getCsBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredStudentList());
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

}
