package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ASSIGNMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.assignment.Assignment;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalAssignments;

public class RedoCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    void execute_validRedoPersonCommand_redoSuccess() throws CommandException {
        UndoCommand undoCommand = new UndoCommand();
        AddPersonCommand addPersonCommand = new AddPersonCommand(new PersonBuilder().build());
        addPersonCommand.execute(model);

        model.commitAddressBook();

        RedoCommand redoCommand = new RedoCommand();
        String expectedMessage = RedoCommand.MESSAGE_REDO_SUCCESS;

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.commitAddressBook();
        expectedModel.undoAddressBook();
        expectedModel.redoAddressBook();
        undoCommand.execute(model);
        assertCommandSuccess(redoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_validRedoAssignmentCommand_redoSuccess() throws CommandException {
        UndoCommand undoCommand = new UndoCommand();
        Assignment assignmentToAdd = TypicalAssignments.ASSIGNMENT_CS1101S_MISSION;

        AddAssignmentCommand addAssignmentCommand = new AddAssignmentCommand(
                INDEX_FIRST_PERSON, assignmentToAdd);

        addAssignmentCommand.execute(model);

        model.commitAddressBook();

        RedoCommand redoCommand = new RedoCommand();
        String expectedMessage = RedoCommand.MESSAGE_REDO_SUCCESS;

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.commitAddressBook();
        expectedModel.undoAddressBook();
        expectedModel.redoAddressBook();
        undoCommand.execute(model);
        assertCommandSuccess(redoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_validRedoListPersonCommand_redoSuccess() throws CommandException {
        UndoCommand undoCommand = new UndoCommand();

        ListPersonCommand listPersonCommand = new ListPersonCommand();

        model.commitAddressBook();

        listPersonCommand.execute(model);

        RedoCommand redoCommand = new RedoCommand();
        String expectedMessage = RedoCommand.MESSAGE_REDO_SUCCESS;

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.commitAddressBook();
        expectedModel.undoAddressBook();
        expectedModel.redoAddressBook();
        undoCommand.execute(model);
        assertCommandSuccess(redoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_validRedoShowAssignmentCommand_redoSuccess() throws CommandException {
        UndoCommand undoCommand = new UndoCommand();
        model.commitAddressBook();
        ShowAssignmentCommand showAssignmentCommand = new ShowAssignmentCommand(INDEX_FIRST_ASSIGNMENT);

        showAssignmentCommand.execute(model);

        RedoCommand redoCommand = new RedoCommand();
        String expectedMessage = RedoCommand.MESSAGE_REDO_SUCCESS;

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.commitAddressBook();
        expectedModel.undoAddressBook();
        expectedModel.redoAddressBook();
        undoCommand.execute(model);
        assertCommandSuccess(redoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_emptyCommandList_throwCommandException() {
        RedoCommand redoCommand = new RedoCommand();
        assertCommandFailure(redoCommand, model, Messages.MESSAGE_INVALID_REDO);
    }
}
