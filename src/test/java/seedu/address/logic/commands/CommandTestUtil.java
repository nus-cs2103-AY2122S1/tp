package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEW_MODULE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELE_HANDLE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.MODULE_NAME_0;
import static seedu.address.testutil.TypicalModules.MODULE_NAME_1;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.TeachingAssistantBuddy;
import seedu.address.model.module.Module;
import seedu.address.testutil.EditStudentDescriptorBuilder;
import seedu.address.testutil.EditTaskDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_NAME_CHARLIE = "Charlie Deen";
    public static final String VALID_STUDENT_ID_AMY = "A1111111A";
    public static final String VALID_STUDENT_ID_BOB = "A2222222A";
    public static final String VALID_STUDENT_ID_CHARLIE = "A3333333A";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_EMAIL_CHARLIE = "charlie@example.com";
    public static final String VALID_TELE_HANDLE_AMY = "@AmyBee";
    public static final String VALID_TELE_HANDLE_BOB = "@bobchoo";
    public static final String VALID_TELE_HANDLE_CHARLIE = "@charlie_D";

    public static final String MODULE_NAME_DESC_0 = " " + PREFIX_MODULE_NAME + MODULE_NAME_0;
    public static final String MODULE_NAME_DESC_1 = " " + PREFIX_MODULE_NAME + MODULE_NAME_1;
    public static final String NEW_MODULE_NAME_DESC_0 = " " + PREFIX_NEW_MODULE_NAME + MODULE_NAME_0;
    public static final String NEW_MODULE_NAME_DESC_1 = " " + PREFIX_NEW_MODULE_NAME + MODULE_NAME_1;
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String STUDENT_ID_DESC_AMY = " " + PREFIX_STUDENT_ID + VALID_STUDENT_ID_AMY;
    public static final String STUDENT_ID_DESC_BOB = " " + PREFIX_STUDENT_ID + VALID_STUDENT_ID_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String TELE_HANDLE_DESC_AMY = " " + PREFIX_TELE_HANDLE + VALID_TELE_HANDLE_AMY;
    public static final String TELE_HANDLE_DESC_BOB = " " + PREFIX_TELE_HANDLE + VALID_TELE_HANDLE_BOB;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_STUDENT_ID_DESC = " " + PREFIX_STUDENT_ID
            + "1234567"; // missing the first and last letter
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_TELE_HANDLE_DESC = " " + PREFIX_TELE_HANDLE + "teleHandle"; // missing @
    public static final String INVALID_MODULE_NAME_DESC = " " + PREFIX_MODULE_NAME
            + "modulE@"; // '@' not allowed in module names
    public static final String INVALID_NEW_MODULE_NAME_DESC = " " + PREFIX_NEW_MODULE_NAME
            + "modulE@"; // '@' not allowed in module names

    public static final String VALID_TASK_NAME_0 = "Assignment 1";
    public static final String VALID_TASK_NAME_1 = "Lab 7";
    public static final String VALID_TASK_NAME_2 = "Assignment3";
    public static final String VALID_TASK_ID_0 = "T1";
    public static final String VALID_TASK_ID_1 = "T77";
    public static final String VALID_TASK_ID_2 = "T333";
    public static final String VALID_TASK_DEADLINE_0 = "2021-10-28";
    public static final String VALID_TASK_DEADLINE_1 = "2022-11-11 12:00";
    public static final String VALID_TASK_DEADLINE_2 = "2021-10-01";
    public static final String INVALID_TASK_DEADLINE_1 = "!@#$";

    public static final String TASK_NAME_DESC_0 = " " + PREFIX_TASK_NAME + VALID_TASK_NAME_0;
    public static final String TASK_NAME_DESC_1 = " " + PREFIX_TASK_NAME + VALID_TASK_NAME_1;
    public static final String TASK_ID_DESC_0 = " " + PREFIX_TASK_ID + VALID_TASK_ID_0;
    public static final String TASK_ID_DESC_1 = " " + PREFIX_TASK_ID + VALID_TASK_ID_1;
    public static final String TASK_DEADLINE_DESC_0 = " " + PREFIX_TASK_DEADLINE + VALID_TASK_DEADLINE_0;
    public static final String TASK_DEADLINE_DESC_1 = " " + PREFIX_TASK_DEADLINE + VALID_TASK_DEADLINE_1;

    public static final String INVALID_TASK_NAME_DESC = " " + PREFIX_TASK_NAME
            + "Midterm @ SoC"; // only alphanumeric characters and spaces are allowed in taskName
    public static final String INVALID_TASK_ID_DESC = " " + PREFIX_TASK_ID
            + "t100"; // taskId must begin with a capital 'T'
    public static final String INVALID_TASK_DEADLINE_DESC = " " + PREFIX_TASK_DEADLINE
            + "18th November, 2021"; // only alphanumeric characters and dashes are allowed in taskDeadline

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditStudentCommand.EditStudentDescriptor DESC_AMY;
    public static final EditStudentCommand.EditStudentDescriptor DESC_BOB;
    public static final EditTaskCommand.EditTaskDescriptor DESC_TASK_0;
    public static final EditTaskCommand.EditTaskDescriptor DESC_TASK_1;

    static {
        DESC_AMY = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withStudentId(VALID_STUDENT_ID_AMY).withEmail(VALID_EMAIL_AMY).withTeleHandle(VALID_TELE_HANDLE_AMY)
                .build();
        DESC_BOB = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withStudentId(VALID_STUDENT_ID_AMY).withEmail(VALID_EMAIL_BOB).withTeleHandle(VALID_TELE_HANDLE_BOB)
                .build();
        DESC_TASK_0 = new EditTaskDescriptorBuilder().withTaskName(VALID_TASK_NAME_0)
                .withTaskId(VALID_TASK_ID_0).withTaskDeadline(VALID_TASK_DEADLINE_0)
                .build();
        DESC_TASK_1 = new EditTaskDescriptorBuilder().withTaskName(VALID_TASK_NAME_1)
                .withTaskId(VALID_TASK_ID_1).withTaskDeadline(VALID_TASK_DEADLINE_1)
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
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        TeachingAssistantBuddy expectedBuddy = new TeachingAssistantBuddy(actualModel.getBuddy());
        List<Module> expectedFilteredList = new ArrayList<>(actualModel.getFilteredModuleList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedBuddy, actualModel.getBuddy());
        assertEquals(expectedFilteredList, actualModel.getFilteredModuleList());
    }
}
