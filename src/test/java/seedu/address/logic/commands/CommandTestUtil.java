package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPLETION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE_OF_APPLICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_DATE_AND_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REQUIREMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.testutil.Assert.assertThrows;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Internship;
import seedu.address.model.Model;
import seedu.address.model.application.Application;
import seedu.address.model.application.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditApplicationDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMAZON = "Amazon";
    public static final String VALID_NAME_BYTEDANCE = "ByteDance";
    public static final String VALID_NAME_GRAB = "Grab";
    public static final String VALID_NAME_SHOPEE = "Shopee";

    public static final String VALID_POSITION_AMAZON = "Software Engineer";
    public static final String VALID_POSITION_BYTEDANCE = "Web Developer";
    public static final String VALID_POSITION_GRAB = "Backend Engineer";
    public static final String VALID_POSITION_SHOPEE = "Frontend Engineer";

    public static final String VALID_DEADLINE_AMAZON = "2021-12-29";
    public static final String VALID_DEADLINE_BYTEDANCE = "2021-11-29";
    public static final String VALID_DEADLINE_GRAB = "2020-12-12";
    public static final String VALID_DEADLINE_SHOPEE = "2021-12-31";

    public static final String VALID_COMPLETION_AMAZON = "Uncompleted";
    public static final String VALID_COMPLETION_BYTEDANCE = "Completed";
    public static final String VALID_COMPLETION_GRAB = "Uncompleted";
    public static final String VALID_COMPLETION_SHOPEE = "Uncompleted";

    public static final String VALID_STATUS_AMAZON = "Pending";
    public static final String VALID_STATUS_BYTEDANCE = "Rejected";
    public static final String VALID_STATUS_GRAB = "Accepted";
    public static final String VALID_STATUS_SHOPEE = "Pending";

    public static final String VALID_PRIORITY_AMAZON = "Medium";
    public static final String VALID_PRIORITY_BYTEDANCE = "High";
    public static final String VALID_PRIORITY_GRAB = "Low";
    public static final String VALID_PRIORITY_SHOPEE = "Low";

    public static final String VALID_REQUIREMENTS_AMAZON = "Resume";
    public static final String VALID_REQUIREMENTS_BYTEDANCE = "CV";
    public static final String VALID_REQUIREMENTS_GRAB = "Interview";

    public static final String VALID_INTERVIEW_DATE_AND_TIME_AMAZON = "2021-12-12 0630";
    public static final String VALID_INTERVIEW_DATE_AND_TIME_BYTEDANCE = "2021-12-12 0530";
    public static final String VALID_INTERVIEW_DATE_AND_TIME_GRAB = "2021-12-12 0730";

    public static final String NAME_DESC_AMAZON = " " + PREFIX_COMPANY_NAME + VALID_NAME_AMAZON;
    public static final String NAME_DESC_BYTEDANCE = " " + PREFIX_COMPANY_NAME + VALID_NAME_BYTEDANCE;
    public static final String EMPTY_NAME_DESC = " " + PREFIX_COMPANY_NAME;

    public static final String POSITION_DESC_AMAZON = " " + PREFIX_INTERNSHIP_POSITION + VALID_POSITION_AMAZON;
    public static final String POSITION_DESC_BYTEDANCE = " " + PREFIX_INTERNSHIP_POSITION + VALID_POSITION_BYTEDANCE;
    public static final String EMPTY_POSITION_DESC = " " + PREFIX_INTERNSHIP_POSITION;

    public static final String DEADLINE_DESC_AMAZON = " " + PREFIX_DEADLINE_OF_APPLICATION + VALID_DEADLINE_AMAZON;
    public static final String DEADLINE_DESC_BYTEDANCE = " " + PREFIX_DEADLINE_OF_APPLICATION
            + VALID_DEADLINE_BYTEDANCE;
    public static final String EMPTY_DEADLINE_DESC = " " + PREFIX_DEADLINE_OF_APPLICATION;

    public static final String PRIORITY_DESC_AMAZON = " " + PREFIX_PRIORITY + VALID_PRIORITY_AMAZON;
    public static final String EMPTY_PRIORITY_DESC = " " + PREFIX_PRIORITY;

    public static final String COMPLETION_DESC_AMAZON = " " + VALID_COMPLETION_AMAZON;
    public static final String EMPTY_COMPLETION_DESC = " " + PREFIX_COMPLETION;

    public static final String STATUS_DESC_AMAZON = " " + VALID_STATUS_AMAZON;
    public static final String EMPTY_STATUS_DESC = " " + PREFIX_STATUS;

    public static final String REQUIREMENTS_DESC_AMAZON = " " + PREFIX_REQUIREMENT + VALID_REQUIREMENTS_AMAZON;
    public static final String EMPTY_REQUIREMENT_DESC = " " + PREFIX_REQUIREMENT;

    public static final String INTERVIEW_DATE_AND_TIME_DESC_AMAZON = " " + PREFIX_INTERVIEW_DATE_AND_TIME
            + VALID_INTERVIEW_DATE_AND_TIME_AMAZON;
    public static final String EMPTY_INTERVIEW_DATE_AND_TIME_DESC = " " + PREFIX_INTERVIEW_DATE_AND_TIME;

    public static final String INVALID_NAME_DESC = " " + PREFIX_COMPANY_NAME + "Google&"; // '&' not allowed in names
    public static final String INVALID_POSITION_DESC = " "
            + PREFIX_INTERNSHIP_POSITION + "Tester#"; // '#' not allowed for positions
    public static final String INVALID_DEADLINE_DESC = " "
            + PREFIX_DEADLINE_OF_APPLICATION + "03-12-2001"; // wrong date format (DD-MM-YYY) not allowed
    public static final String INVALID_PRIORITY_DESC = " " + PREFIX_PRIORITY
            + "higher"; // not an allowed priority value
    public static final String INVALID_PRIORITY_DESC_2 = " " + PREFIX_PRIORITY
            + "high high"; // priority value number more than 1
    public static final String INVALID_COMPLETION_DESC = " " + PREFIX_COMPLETION
            + "completing"; // not an allowed completion value
    public static final String INVALID_COMPLETION_DESC_2 = " " + PREFIX_COMPLETION
            + "completed completed"; // completion value number more than 1
    public static final String INVALID_STATUS_DESC = " " + PREFIX_STATUS
            + "waiting"; // not an allowed status value
    public static final String INVALID_STATUS_DESC_2 = " " + PREFIX_STATUS
            + "pending pending"; // status value number more than 1
    public static final String INVALID_INTERVIEW_DATE_AND_TIME_DESC = " " + PREFIX_INTERVIEW_DATE_AND_TIME
            + "2021-12-21 09:00"; //wrong date format
    public static final String INVALID_REQUIREMENT_DESC = " " + PREFIX_REQUIREMENT
            + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"; // requirement longer than 20 characters



    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditApplicationDescriptor DESC_AMAZON;
    public static final EditCommand.EditApplicationDescriptor DESC_BYTEDANCE;

    static {
        DESC_AMAZON = new EditApplicationDescriptorBuilder()
                .withCompany(VALID_NAME_AMAZON)
                .withPosition(VALID_POSITION_AMAZON)
                .withDeadline(VALID_DEADLINE_AMAZON)
                .withRequirements(VALID_REQUIREMENTS_AMAZON)
                .withInterviewDateAndTime(VALID_INTERVIEW_DATE_AND_TIME_AMAZON).build();
        DESC_BYTEDANCE = new EditApplicationDescriptorBuilder()
                .withCompany(VALID_NAME_BYTEDANCE)
                .withPosition(VALID_POSITION_BYTEDANCE)
                .withDeadline(VALID_DEADLINE_BYTEDANCE)
                .withRequirements(VALID_REQUIREMENTS_BYTEDANCE)
                .withInterviewDateAndTime(VALID_INTERVIEW_DATE_AND_TIME_BYTEDANCE).build();
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
     * - the Internship, filtered application list and selected application in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Internship expectedInternship = new Internship(actualModel.getInternship());
        List<Application> expectedFilteredList = new ArrayList<>(actualModel.getFilteredApplicationList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedInternship, actualModel.getInternship());
        assertEquals(expectedFilteredList, actualModel.getFilteredApplicationList());
    }
    /**
     * Updates Internship's filtered list to show only the application at the given {@code targetIndex} in the
     * Internship's application list.
     */
    public static void showApplicationAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredApplicationList().size());

        Application application = model.getFilteredApplicationList().get(targetIndex.getZeroBased());
        final String[] splitName = application.getCompany().fullCompanyName.split("\\s+");
        model.updateFilteredApplicationList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredApplicationList().size());
    }

    /**
     * Gets the current date.
     *
     * @return The string representation of the current date.
     */
    public static String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        return formatter.format(currentDate);
    }

}
