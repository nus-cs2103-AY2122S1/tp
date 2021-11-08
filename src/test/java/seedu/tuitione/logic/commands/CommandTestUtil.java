package seedu.tuitione.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.tuitione.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.tuitione.commons.core.index.Index;
import seedu.tuitione.logic.commands.exceptions.CommandException;
import seedu.tuitione.model.Model;
import seedu.tuitione.model.Tuitione;
import seedu.tuitione.model.student.NameContainsKeywordsPredicate;
import seedu.tuitione.model.student.Student;
import seedu.tuitione.testutil.EditStudentDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "91111111";
    public static final String VALID_PHONE_BOB = "82222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_GRADE_AMY = "S2";
    public static final String VALID_GRADE_BOB = "S1";
    public static final String VALID_REMARK_HUSBAND = "husband";
    public static final String VALID_REMARK_FRIEND = "friend";
    public static final String VALID_REMARK_FINANCIAL_AID = "FA";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String GRADE_DESC_AMY = " " + PREFIX_GRADE + VALID_GRADE_AMY;
    public static final String GRADE_DESC_BOB = " " + PREFIX_GRADE + VALID_GRADE_BOB;
    public static final String REMARK_DESC_FRIEND = " " + PREFIX_REMARK + VALID_REMARK_FRIEND;
    public static final String REMARK_DESC_HUSBAND = " " + PREFIX_REMARK + VALID_REMARK_HUSBAND;
    public static final String REMARK_DESC_FINANCIAL_AID = " " + PREFIX_REMARK + VALID_REMARK_FINANCIAL_AID;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_GRADE_DESC = " " + PREFIX_GRADE + "F1"; // grade should start with 'S' or 'P'
    public static final String INVALID_REMARK_DESC = " " + PREFIX_REMARK + "hubby*"; // '*' not allowed in remarks

    public static final String VALID_SUBJECT_SCIENCE = "Science";
    public static final String VALID_SUBJECT_MATHS = "Maths";
    public static final String VALID_GRADE_SCIENCE = "S1";
    public static final String VALID_GRADE_MATHS = "S2";
    public static final String VALID_LESSONTIME_SCIENCE = "1200";
    public static final String VALID_LESSONTIME_MATHS = "1300";
    public static final String VALID_DAYOFWEEK_SCIENCE = "Wed";
    public static final String VALID_DAYOFWEEK_MATHS = "Fri";
    public static final String VALID_PRICE_SCIENCE = "100.0";
    public static final String VALID_PRICE_MATHS = "120.0";

    public static final String SUBJECT_DESC_SCIENCE = " " + PREFIX_SUBJECT + VALID_SUBJECT_SCIENCE;
    public static final String SUBJECT_DESC_MATHS = " " + PREFIX_SUBJECT + VALID_SUBJECT_MATHS;
    public static final String GRADE_DESC_SCIENCE = " " + PREFIX_GRADE + VALID_GRADE_SCIENCE;
    public static final String GRADE_DESC_MATHS = " " + PREFIX_GRADE + VALID_GRADE_MATHS;
    public static final String LESSONTIME_DESC_SCIENCE = " " + PREFIX_TIME + VALID_LESSONTIME_SCIENCE;
    public static final String LESSONTIME_DESC_MATHS = " " + PREFIX_TIME + VALID_LESSONTIME_MATHS;
    public static final String DAYOFWEEK_DESC_SCIENCE = " " + PREFIX_DAY + VALID_DAYOFWEEK_SCIENCE;
    public static final String DAYOFWEEK_DESC_MATHS = " " + PREFIX_DAY + VALID_DAYOFWEEK_MATHS;
    public static final String PRICE_DESC_SCIENCE = " " + PREFIX_COST + VALID_PRICE_SCIENCE;
    public static final String PRICE_DESC_MATHS = " " + PREFIX_COST + VALID_PRICE_MATHS;

    public static final String INVALID_SUBJECT_DESC = " " + PREFIX_SUBJECT + "Science101*";
    public static final String INVALID_LESSONTIME_DESC = " " + PREFIX_TIME + "12:00";
    public static final String INVALID_LESSONTIME_ODD_TIME_DESC = " " + PREFIX_TIME + "0100";
    public static final String INVALID_DAYOFWEEK_DESC = " " + PREFIX_DAY + "SadDay";
    public static final String INVALID_PRICE_DESC = " " + PREFIX_COST + "-1.0";


    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditStudentDescriptor DESC_AMY;
    public static final EditCommand.EditStudentDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withRemarks(VALID_REMARK_FRIEND).build();
        DESC_BOB = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withRemarks(VALID_REMARK_HUSBAND, VALID_REMARK_FRIEND).build();
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
     * - the tuitione book, filtered student list and selected student in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Tuitione expectedTuitione = new Tuitione(actualModel.getTuitione());
        List<Student> expectedFilteredList = new ArrayList<>(actualModel.getFilteredStudentList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedTuitione, actualModel.getTuitione());
        assertEquals(expectedFilteredList, actualModel.getFilteredStudentList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the student at the given {@code targetIndex} in the
     * {@code model}'s tuitione book.
     */
    public static void showStudentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredStudentList().size());

        Student student = model.getFilteredStudentList().get(targetIndex.getZeroBased());
        final String[] splitName = student.getName().fullName.split("\\s+");
        model.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredStudentList().size());
    }


}
