package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalApplications.AMAZON;
import static seedu.address.testutil.TypicalApplications.SHOPEE;
import static seedu.address.testutil.TypicalApplications.getTypicalInternship;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.application.Application;
import seedu.address.testutil.ApplicationBuilder;
import seedu.address.testutil.EditApplicationDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for UndoCommand.
 */
public class UndoCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalInternship(), new UserPrefs());
        expectedModel = new ModelManager(model.getInternship(), new UserPrefs());
    }

    @Test
    public void execute_cannotUndo_throwsCommandException() {
        assertCommandFailure(new UndoCommand(), model, UndoCommand.MESSAGE_FAILED);
    }

    @Test
    public void execute_undoAddCommandSuccess() {
        AddCommand addCommand = new AddCommand(SHOPEE);
        try {
            addCommand.execute(model);
        } catch (CommandException e) {
            System.out.println("Exception occurred in the command that is executed before Undo command");
        }
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_undoDeleteCommandSuccess() {
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_APPLICATION);
        try {
            deleteCommand.execute(model);
        } catch (CommandException e) {
            System.out.println("Exception occurred in the command that is executed before Undo command");
        }
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_undoClearCommandSuccess() {
        ClearCommand clearCommand = new ClearCommand();
        clearCommand.execute(model);
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_undoCompleteCommandSuccess() {
        CompleteCommand completeCommand = new CompleteCommand(INDEX_FIRST_APPLICATION);
        try {
            completeCommand.execute(model);
        } catch (CommandException e) {
            System.out.println("Exception occurred in the command that is executed before Undo command");
        }
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_undoAcceptCommandSuccess() {
        AcceptCommand acceptCommand = new AcceptCommand(INDEX_FIRST_APPLICATION);
        try {
            acceptCommand.execute(model);
        } catch (CommandException e) {
            System.out.println("Exception occurred in the command that is executed before Undo command");
        }
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_undoRejectCommandSuccess() {
        RejectCommand rejectCommand = new RejectCommand(INDEX_FIRST_APPLICATION);
        try {
            rejectCommand.execute(model);
        } catch (CommandException e) {
            System.out.println("Exception occurred in the command that is executed before Undo command");
        }
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_undoEditCommandSuccess() {
        Application editedApplication = new ApplicationBuilder().withDeadline("2021-11-11").build();
        EditCommand.EditApplicationDescriptor descriptor =
                new EditApplicationDescriptorBuilder(editedApplication).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_APPLICATION, descriptor);
        try {
            editCommand.execute(model);
        } catch (CommandException e) {
            System.out.println("Exception occurred in the command that is executed before Undo command");
        }
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_undoCommandRecoversTheMostRecentChangeSuccess() {
        DeleteCommand deleteFirstApplication = new DeleteCommand(INDEX_FIRST_APPLICATION);
        try {
            deleteFirstApplication.execute(model);
            deleteFirstApplication.execute(model);
        } catch (CommandException e) {
            System.out.println("Exception occurred in the command that is executed before Undo command");
        }
        expectedModel.deleteApplication(AMAZON);
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS, expectedModel);
        //expected behavior: undo the deletion of the second application, but the first is still deleted
    }

}
