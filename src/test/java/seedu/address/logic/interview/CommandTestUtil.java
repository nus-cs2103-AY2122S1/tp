package seedu.address.logic.interview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CANDIDATE_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Command;
import seedu.address.logic.CommandResult;
import seedu.address.logic.candidate.exceptions.CommandException;
import seedu.address.model.HrManager;
import seedu.address.model.Model;
import seedu.address.model.interview.Interview;
import seedu.address.model.interview.Interview.InterviewStatus;
import seedu.address.model.interview.PositionTitleContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;
import seedu.address.testutil.EditInterviewDescriptorBuilder;

public class CommandTestUtil {

    public static final Position VALID_POSITION_ADMIN = new Position(new Title("Admin"));
    public static final Position VALID_POSITION_MANAGER = new Position(new Title("Manager"));
    public static final HashSet<Person> VALID_CANDIDATES_SET = new HashSet<>(List.of(ALICE, BOB));
    public static final HashSet<Index> VALID_EMPTY_CANDIDATE_INDEX_SET = new HashSet<>();
    public static final HashSet<Index> VALID_CANDIDATE_INDEX_SET =
            new HashSet<>(List.of(Index.fromZeroBased(1), Index.fromZeroBased(2), Index.fromZeroBased(3)));
    public static final LocalDate VALID_LOCAL_DATE = LocalDate.of(2021, 10, 18);
    public static final LocalDate VALID_LOCAL_DATE_OTHER_DATE = LocalDate.of(2021, 10, 01);
    public static final LocalTime VALID_START_TIME = LocalTime.NOON; //12:00
    public static final LocalTime VALID_START_TIME_OTHER_START_TIME = LocalTime.MIDNIGHT; //00:00
    public static final Duration VALID_DURATION = Duration.ofMinutes(180);
    public static final Duration VALID_DURATION_OTHER_DURATION = Duration.ofMinutes(120);
    public static final InterviewStatus VALID_STATUS_PENDING = InterviewStatus.PENDING;
    public static final InterviewStatus VALID_STATUS_COMPLETED = InterviewStatus.COMPLETED;

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final String VALID_NAME_ALICE = ALICE.getName().fullName;
    public static final String VALID_NAME_BOB = BOB.getName().fullName;
    public static final String VALID_DATE = "18/10/2021";
    public static final String VALID_DATE_OTHER_DATE = "21/10/2021";
    public static final String VALID_TIME = "1200";
    public static final String VALID_TIME_OTHER_TIME = "1300";
    public static final String VALID_DURATION_TIME = "180";
    public static final String VALID_DURATION_TIME_OTHER_DURATION = "190";
    public static final String VALID_CANDIDATE_INDEX_1 = "1";
    public static final String VALID_CANDIDATE_INDEX_2 = "2";
    public static final String VALID_POSITION_ADMIN_ASSISTANT = "Administrative Assistant";

    public static final String VALID_POSITION_ADMIN_DESC = " " + PREFIX_POSITION + VALID_POSITION_ADMIN.getTitle();
    public static final String VALID_POSITION_MANAGER_DESC = " " + PREFIX_POSITION + VALID_POSITION_MANAGER.getTitle();
    public static final String VALID_POSITION_ADMIN_ASST_DESC = " " + PREFIX_POSITION + VALID_POSITION_ADMIN_ASSISTANT;
    public static final String VALID_CANDIDATE_DESC_ALICE = " " + PREFIX_CANDIDATE_INDEX + "1";
    public static final String VALID_CANDIDATE_DESC_BOB = " " + PREFIX_CANDIDATE_INDEX + "2";
    public static final String VALID_DATE_DESC = " " + PREFIX_DATE + VALID_DATE;
    public static final String VALID_DATE_OTHER_DATE_DESC = " " + PREFIX_DATE + VALID_DATE_OTHER_DATE;
    public static final String VALID_TIME_DESC = " " + PREFIX_TIME + VALID_TIME;
    public static final String VALID_TIME_OTHER_TIME_DESC = " " + PREFIX_TIME + VALID_TIME_OTHER_TIME;
    public static final String VALID_DURATION_DESC = " " + PREFIX_DURATION + VALID_DURATION_TIME;
    public static final String VALID_DURATION_TIME_OTHER_DURATION_DESC = " " + PREFIX_DURATION
            + VALID_DURATION_TIME_OTHER_DURATION;
    public static final String VALID_STATUS_PENDING_DESC = " " + PREFIX_INTERVIEW_STATUS + VALID_STATUS_PENDING;
    public static final String VALID_STATUS_COMPLETED_DESC = " " + PREFIX_INTERVIEW_STATUS
            + VALID_STATUS_COMPLETED;

    public static final String VALID_POSITION_ADMIN_NAME = VALID_POSITION_ADMIN.getTitle().toString();
    public static final String VALID_POSITION_MANAGER_NAME = VALID_POSITION_MANAGER.getTitle().toString();

    public static final String INVALID_POSITION_DESC = " " + PREFIX_POSITION + "@123";
    public static final String INVALID_DATE_DESC = " " + PREFIX_DATE + "38/10/2021";
    public static final String INVALID_TIME_DESC = " " + PREFIX_TIME + "2500";
    public static final String INVALID_DURATION_TIME = " " + PREFIX_DURATION + "-180";
    public static final String INVALID_INDEX_DESC = " " + PREFIX_CANDIDATE_INDEX + "?";
    public static final String INVALID_STATUS_DESC = " " + PREFIX_INTERVIEW_STATUS + "blah";

    public static final EditInterviewCommand.EditInterviewDescriptor DESC_INTERVIEW_ADMIN_ASSISTANT;
    public static final EditInterviewCommand.EditInterviewDescriptor DESC_INTERVIEW_MANAGER;

    static {
        DESC_INTERVIEW_ADMIN_ASSISTANT = new EditInterviewDescriptorBuilder().withPosition(VALID_POSITION_ADMIN_NAME)
                .withDate(VALID_DATE).withStartTime(VALID_TIME)
                .withDuration(VALID_DURATION_TIME).withStatus(VALID_STATUS_PENDING).build();
        DESC_INTERVIEW_MANAGER = new EditInterviewDescriptorBuilder().withPosition(VALID_POSITION_MANAGER_NAME)
                .withDate(VALID_DATE).withStartTime(VALID_TIME)
                .withDuration(VALID_DURATION_TIME_OTHER_DURATION).withStatus(VALID_STATUS_PENDING).build();
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
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, CommandResult.CommandType.INTERVIEW);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertListCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, CommandResult.CommandType.LIST_I);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    private static boolean isEditICommand(Command command) {
        String commandClassName = command.getClass().getSimpleName();
        return commandClassName.equals("EditInterviewCommand");
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the given {@code command} is an edit command <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertEditInterviewCommandSuccess(Command command, Model actualModel,
                                                         CommandResult expectedEditCommandResult, Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedEditCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertEditInterviewCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertEditInterviewCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                                         Model expectedModel) {
        if (!isEditICommand(command)) {
            throw new AssertionError("Command should be an EditInterviewCommand");
        }

        CommandResult expectedCommandResult = new CommandResult(expectedMessage,
                CommandResult.CommandType.INTERVIEW);
        assertEditInterviewCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the HR Manager, filtered interview list and selected interview in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        HrManager expectedHrManager = new HrManager(actualModel.getHrManager());
        List<Interview> expectedFilteredList = new ArrayList<>(actualModel.getFilteredInterviewList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedHrManager, actualModel.getHrManager());
        assertEquals(expectedFilteredList, actualModel.getFilteredInterviewList());
    }

    /**
     * Executes the given {@code command} twice, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the HR Manager, filtered interview list and selected interview in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailureRepeatedAssign(Command command, Model actualModel, String expectedMessage) {
        HrManager expectedHrManager = new HrManager(actualModel.getHrManager());
        List<Interview> expectedFilteredList = new ArrayList<>(actualModel.getFilteredInterviewList());

        try {
            command.execute(actualModel);
            assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
            assertEquals(expectedHrManager, actualModel.getHrManager());
            assertEquals(expectedFilteredList, actualModel.getFilteredInterviewList());
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Updates {@code model}'s filtered list to show only the interview at the given {@code targetIndex} in the
     * {@code model}'s HR Manager.
     */
    public static void showInterviewAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredInterviewList().size());

        Interview interview = model.getFilteredInterviewList().get(targetIndex.getZeroBased());
        final String[] splitTitle = interview.getPositionTitle().fullTitle.split("\\s+");
        model.updateFilteredInterviewList(new PositionTitleContainsKeywordsPredicate(Arrays.asList(splitTitle[0])));

        assertEquals(1, model.getFilteredInterviewList().size());
    }
}
