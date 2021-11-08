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

public class UndoCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    void execute_validUndoPersonCommand_undoSuccess() throws CommandException {
        AddPersonCommand addPersonCommand = new AddPersonCommand(new PersonBuilder().build());
        addPersonCommand.execute(model);

        model.commitAddressBook();

        UndoCommand undoCommand = new UndoCommand();

        String expectedMessage = UndoCommand.MESSAGE_UNDO_SUCCESS;

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.commitAddressBook();
        expectedModel.undoAddressBook();

        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_validUndoAssignmentCommand_undoSuccess() throws CommandException {
        Assignment assignmentToAdd = TypicalAssignments.ASSIGNMENT_CS1101S_MISSION;

        AddAssignmentCommand addAssignmentCommand = new AddAssignmentCommand(
                INDEX_FIRST_PERSON, assignmentToAdd);

        model.commitAddressBook();

        addAssignmentCommand.execute(model);

        UndoCommand undoCommand = new UndoCommand();

        String expectedMessage = UndoCommand.MESSAGE_UNDO_SUCCESS;

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.commitAddressBook();
        expectedModel.undoAddressBook();

        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_validUndoListPersonCommand_undoSuccess() throws CommandException {
        model.commitAddressBook();
        ListPersonCommand listPersonCommand = new ListPersonCommand();
        listPersonCommand.execute(model);

        UndoCommand undoCommand = new UndoCommand();

        String expectedMessage = UndoCommand.MESSAGE_UNDO_SUCCESS;

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.commitAddressBook();
        expectedModel.undoAddressBook();

        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_validUndoShowAssignmentCommand_undoSuccess() throws CommandException {
        model.commitAddressBook();
        ShowAssignmentCommand showAssignmentCommand = new ShowAssignmentCommand(INDEX_FIRST_ASSIGNMENT);
        showAssignmentCommand.execute(model);

        UndoCommand undoCommand = new UndoCommand();

        String expectedMessage = UndoCommand.MESSAGE_UNDO_SUCCESS;

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.commitAddressBook();
        expectedModel.undoAddressBook();

        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_emptyCommandList_throwCommandException() {
        UndoCommand undoCommand = new UndoCommand();
        assertCommandFailure(undoCommand, model, Messages.MESSAGE_INVALID_UNDO);
    }
}
