package seedu.address.logic.position;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Command;
import seedu.address.logic.CommandResult;
import seedu.address.logic.candidate.exceptions.CommandException;
import seedu.address.model.HrManager;
import seedu.address.model.Model;
import seedu.address.model.position.Position;
import seedu.address.model.position.Position.PositionStatus;
import seedu.address.model.position.TitleContainsKeywordsPredicate;
import seedu.address.testutil.EditPositionDescriptorBuilder;

public class CommandTestUtil {

    public static final String VALID_TITLE_BOOKKEEPER = "Bookkeeper";
    public static final String VALID_TITLE_HR_MANAGER = "HR Manager";
    public static final String VALID_TITLE_ADMIN_ASSISTANT = "Administrative Assistant";
    public static final PositionStatus VALID_STATUS_OPEN = PositionStatus.OPEN;
    public static final PositionStatus VALID_STATUS_CLOSED = PositionStatus.CLOSED;

    public static final String POSITION_HR_MANAGER = " " + PREFIX_TITLE + VALID_TITLE_HR_MANAGER;
    public static final String POSITION_ADMIN_ASSISTANT = " " + PREFIX_TITLE + VALID_TITLE_ADMIN_ASSISTANT;
    public static final String POSITION_BOOKKEEPER = " " + PREFIX_TITLE + VALID_TITLE_BOOKKEEPER;
    public static final String STATUS_OPEN = " " + PREFIX_POSITION_STATUS + "open";

    public static final String INVALID_POSITION_TITLE = " " + PREFIX_TITLE + "!accountant";
    public static final String INVALID_POSITION_STATUS = " " + PREFIX_POSITION_STATUS + "status";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditPositionCommand.EditPositionDescriptor DESC_BOOKKEEPER;
    public static final EditPositionCommand.EditPositionDescriptor DESC_HR_MANAGER;
    public static final EditPositionCommand.EditPositionDescriptor DESC_ADMIN_ASSISTANT;

    static {
        DESC_BOOKKEEPER = new EditPositionDescriptorBuilder().withTitle(VALID_TITLE_BOOKKEEPER)
                .withPositionStatus(VALID_STATUS_OPEN).build();
        DESC_HR_MANAGER = new EditPositionDescriptorBuilder().withTitle(VALID_TITLE_HR_MANAGER)
                .withPositionStatus(VALID_STATUS_OPEN).build();
        DESC_ADMIN_ASSISTANT = new EditPositionDescriptorBuilder().withTitle(VALID_TITLE_ADMIN_ASSISTANT)
                .withPositionStatus(VALID_STATUS_CLOSED).build();
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
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, CommandResult.CommandType.POSITION);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    private static boolean isEditPCommand(Command command) {
        String commandClassName = command.getClass().getSimpleName();
        return commandClassName.equals("EditPositionCommand");
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the given {@code command} is an edit command <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertEditPositionCommandSuccess(Command command, Model actualModel,
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
     * Convenience wrapper to {@link #assertEditPositionCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertEditPositionCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                                        Model expectedModel) {
        if (!isEditPCommand(command)) {
            throw new AssertionError("Command should be an EditPositionCommand");
        }

        CommandResult expectedCommandResult = new CommandResult(expectedMessage,
                CommandResult.CommandType.POSITION);
        assertEditPositionCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertListCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, CommandResult.CommandType.LIST_P);
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
        HrManager expectedHrManager = new HrManager(actualModel.getHrManager());
        List<Position> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPositionList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedHrManager, actualModel.getHrManager());
        assertEquals(expectedFilteredList, actualModel.getFilteredPositionList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the position at the given {@code targetIndex} in the
     * {@code model}'s HrManager.
     */
    public static void showPositionAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPositionList().size());

        Position position = model.getFilteredPositionList().get(targetIndex.getZeroBased());
        final String[] splitTitle = position.getTitle().fullTitle.split("\\s+");
        model.updateFilteredPositionList(new TitleContainsKeywordsPredicate(Arrays.asList(splitTitle[0])));

        assertEquals(1, model.getFilteredPositionList().size());
    }
}
