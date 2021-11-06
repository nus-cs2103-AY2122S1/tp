package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEAVES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SHIFT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_BY;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Rhrh;
import seedu.address.model.person.employee.Employee;
import seedu.address.model.person.employee.EmployeeClassContainsKeywordsPredicate;
import seedu.address.testutil.EditEmployeeDescriptorBuilder;


/**
 * Contains helper methods for testing Employee commands.
 */
public class EmployeeCommandTestUtil {

    public static final String VALID_LEAVES_AMY = "2";
    public static final String VALID_LEAVES_BOB = "1";
    public static final String VALID_SALARY_AMY = "14000";
    public static final String VALID_SALARY_BOB = "5000";
    public static final String VALID_JOB_TITLE_AMY = "Account Manager";
    public static final String VALID_JOB_TITLE_BOB = "UIUX Designer";
    public static final String VALID_SHIFTS_AMY = "2021-12-25 0800";
    public static final String VALID_SHIFTS_BOB = "2021-12-26 0800";
    public static final String VALID_SORT_BY_LEAVES = "l";
    public static final String VALID_SORT_BY_SALARY = "sal";
    public static final String VALID_SORT_BY_JOB_TITLE = "jt";

    public static final String LEAVES_DESC_AMY = " " + PREFIX_LEAVES + VALID_LEAVES_AMY;
    public static final String LEAVES_DESC_BOB = " " + PREFIX_LEAVES + VALID_LEAVES_BOB;
    public static final String SALARY_DESC_AMY = " " + PREFIX_SALARY + VALID_SALARY_AMY;
    public static final String SALARY_DESC_BOB = " " + PREFIX_SALARY + VALID_SALARY_BOB;
    public static final String JOB_TITLE_DESC_AMY = " " + PREFIX_JOB_TITLE + VALID_JOB_TITLE_AMY;
    public static final String JOB_TITLE_DESC_BOB = " " + PREFIX_JOB_TITLE + VALID_JOB_TITLE_BOB;
    public static final String SHIFTS_DESC_AMY = " " + PREFIX_SHIFT + VALID_SHIFTS_AMY;
    public static final String SHIFTS_DESC_BOB = " " + PREFIX_SHIFT + VALID_SHIFTS_BOB;
    public static final String SORT_BY_DESC_LEAVES = " " + PREFIX_SORT_BY + VALID_SORT_BY_LEAVES;
    public static final String SORT_BY_DESC_SALARY = " " + PREFIX_SORT_BY + VALID_SORT_BY_SALARY;
    public static final String SORT_BY_DESC_JOB_TITLE = " " + PREFIX_SORT_BY + VALID_SORT_BY_JOB_TITLE;

    public static final String INVALID_LEAVES_DESC = " " + PREFIX_LEAVES + "abc"; // alphabets not allowed
    public static final String INVALID_SALARY_DESC = " " + PREFIX_SALARY + "abc"; //alphabets not allowed
    public static final String INVALID_JOB_TITLE_DESC = " " + PREFIX_JOB_TITLE + "Account Manager*"; // '*' not allowed
    public static final String INVALID_SHIFT_DESC = " " + PREFIX_SHIFT + "123-123-123-123"; // must be in correct format
    public static final String INVALID_SORT_BY_SHIFT = " " + PREFIX_SORT_BY + "sh";
    public static final String INVALID_SORT_BY_TAG = " " + PREFIX_SORT_BY + "t";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditEmployeeCommand.EditEmployeeDescriptor DESC_AMY;
    public static final EditEmployeeCommand.EditEmployeeDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditEmployeeDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).withSalary(VALID_SALARY_AMY)
                .withJobTitle(VALID_JOB_TITLE_AMY).withLeaves(VALID_LEAVES_AMY).build();
        DESC_BOB = new EditEmployeeDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).withSalary(VALID_SALARY_BOB)
                .withJobTitle(VALID_JOB_TITLE_BOB).withLeaves(VALID_LEAVES_BOB).build();
    }


    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel,
                                            CommandResult expectedCommandResult, Model expectedModel) {
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
        CommandResult expectedCommandResult = new CommandResult(expectedMessage,
                false, false, false, true, false, false);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - RHRH, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Rhrh expectedRhrh = new Rhrh(actualModel.getRhrh());
        List<Employee> expectedFilteredList = new ArrayList<>(actualModel.getFilteredEmployeeList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedRhrh, actualModel.getRhrh());
        assertEquals(expectedFilteredList, actualModel.getFilteredEmployeeList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the Employee at the given {@code targetIndex} in the
     * {@code model}'s RHRH.
     */
    public static void showEmployeeAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredEmployeeList().size());

        Employee employee = model.getFilteredEmployeeList().get(targetIndex.getZeroBased());
        final String[] splitName = employee.getName().fullName.split("\\s+");
        model.updateFilteredEmployeeList(new EmployeeClassContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredEmployeeList().size());
    }
}
