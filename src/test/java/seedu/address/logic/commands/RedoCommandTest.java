package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalApplications.AMAZON;
import static seedu.address.testutil.TypicalApplications.BYTEDANCE;
import static seedu.address.testutil.TypicalApplications.SHOPEE;
import static seedu.address.testutil.TypicalApplications.getTypicalInternship;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Internship;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.application.Application;
import seedu.address.model.application.Completion;
import seedu.address.model.application.Deadline;
import seedu.address.model.application.Status;
import seedu.address.testutil.EditApplicationDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RedoCommand.
 */
public class RedoCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalInternship(), new UserPrefs());
        expectedModel = new ModelManager(model.getInternship(), new UserPrefs());
    }

    @Test
    public void execute_cannotRedo_throwsCommandException() {
        expectedModel = new ModelManager(model.getInternship(), new UserPrefs());
        assertCommandFailure(new RedoCommand(), model, RedoCommand.MESSAGE_FAILED);
    }

    @Test
    public void execute_redoAddCommandSuccess() {
        AddCommand addCommand = new AddCommand(SHOPEE);
        UndoCommand undoCommand = new UndoCommand();
        try {
            addCommand.execute(model);
            undoCommand.execute(model);
        } catch (CommandException e) {
            System.out.println("Exception occurred in the command that is executed before Redo command");
        }
        expectedModel.addApplication(SHOPEE);
        assertCommandSuccess(new RedoCommand(), model, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_redoDeleteCommandSuccess() {
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_APPLICATION);
        UndoCommand undoCommand = new UndoCommand();
        try {
            deleteCommand.execute(model);
            undoCommand.execute(model);
        } catch (CommandException e) {
            System.out.println("Exception occurred in the command that is executed before Redo command");
        }
        expectedModel.deleteApplication(AMAZON);
        assertCommandSuccess(new RedoCommand(), model, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_redoClearCommandSuccess() {
        ClearCommand clearCommand = new ClearCommand();
        UndoCommand undoCommand = new UndoCommand();
        try {
            clearCommand.execute(model);
            undoCommand.execute(model);
        } catch (CommandException e) {
            System.out.println("Exception occurred in the command that is executed before Redo command");
        }
        expectedModel = new ModelManager(new Internship(), new UserPrefs());
        assertCommandSuccess(new RedoCommand(), model, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_redoCompleteCommandSuccess() {
        CompleteCommand completeCommand = new CompleteCommand(INDEX_FIRST_APPLICATION);
        UndoCommand undoCommand = new UndoCommand();
        try {
            completeCommand.execute(model);
            undoCommand.execute(model);
        } catch (CommandException e) {
            System.out.println("Exception occurred in the command that is executed before Redo command");
        }
        Application originalAmazon = model.getFilteredApplicationList().get(0);
        Application completedAmazon = new Application(originalAmazon.getCompany(), originalAmazon.getPosition(),
                originalAmazon.getDeadline(), new Completion("Completed"), originalAmazon.getStatus(),
                originalAmazon.getPriority(), originalAmazon.getRequirements(),
                originalAmazon.getInterviewDateAndTime());
        expectedModel.setApplication(AMAZON, completedAmazon);
        assertCommandSuccess(new RedoCommand(), model, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_redoAcceptCommandSuccess() {
        AcceptCommand acceptCommand = new AcceptCommand(INDEX_FIRST_APPLICATION);
        UndoCommand undoCommand = new UndoCommand();
        try {
            acceptCommand.execute(model);
            undoCommand.execute(model);
        } catch (CommandException e) {
            System.out.println("Exception occurred in the command that is executed before Redo command");
        }
        Application originalAmazon = model.getFilteredApplicationList().get(0);
        Application acceptedAmazon = new Application(originalAmazon.getCompany(), originalAmazon.getPosition(),
                originalAmazon.getDeadline(), new Completion("Completed"), new Status("Accepted"),
                originalAmazon.getPriority(), originalAmazon.getRequirements(),
                originalAmazon.getInterviewDateAndTime());
        expectedModel.setApplication(AMAZON, acceptedAmazon);
        assertCommandSuccess(new RedoCommand(), model, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_redoRejectCommandSuccess() {
        RejectCommand rejectCommand = new RejectCommand(INDEX_FIRST_APPLICATION);
        UndoCommand undoCommand = new UndoCommand();
        try {
            rejectCommand.execute(model);
            undoCommand.execute(model);
        } catch (CommandException e) {
            System.out.println("Exception occurred in the command that is executed before Redo command");
        }
        Application originalAmazon = model.getFilteredApplicationList().get(0);
        Application rejectedAmazon = new Application(originalAmazon.getCompany(),
                originalAmazon.getPosition(),
                originalAmazon.getDeadline(), new Completion("Completed"), new Status("Rejected"),
                originalAmazon.getPriority(), originalAmazon.getRequirements(),
                originalAmazon.getInterviewDateAndTime());
        expectedModel.setApplication(AMAZON, rejectedAmazon);
        assertCommandSuccess(new RedoCommand(), model, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_redoEditCommandSuccess() {
        Application originalAmazon = model.getFilteredApplicationList().get(0);
        Application editedAmazon = new Application(originalAmazon.getCompany(), originalAmazon.getPosition(),
                new Deadline("2021-11-11"), originalAmazon.getCompletion(), originalAmazon.getStatus(),
                originalAmazon.getPriority(), originalAmazon.getRequirements(),
                originalAmazon.getInterviewDateAndTime());
        EditCommand.EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder(editedAmazon).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_APPLICATION, descriptor);
        UndoCommand undoCommand = new UndoCommand();
        try {
            editCommand.execute(model);
            undoCommand.execute(model);
        } catch (CommandException e) {
            System.out.println("Exception occurred in the command that is executed before Redo command");
        }
        expectedModel.setApplication(AMAZON, editedAmazon);
        assertCommandSuccess(new RedoCommand(), model, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_redoCommandRedoneTheMostRecentChangeSuccess() {
        DeleteCommand deleteFirstApplication = new DeleteCommand(INDEX_FIRST_APPLICATION);
        UndoCommand undoCommand = new UndoCommand();
        try {
            deleteFirstApplication.execute(model);
            deleteFirstApplication.execute(model);
            undoCommand.execute(model); //undo the deletion of the second application, but the first is still deleted
        } catch (CommandException e) {
            System.out.println("Exception occurred in the command that is executed before Redo command");
        }
        expectedModel.deleteApplication(AMAZON);
        expectedModel.deleteApplication(BYTEDANCE);
        assertCommandSuccess(new RedoCommand(), model, RedoCommand.MESSAGE_SUCCESS, expectedModel);
        //expected behavior: redo the deletion of the second application, so both applications are deleted now
    }

}
