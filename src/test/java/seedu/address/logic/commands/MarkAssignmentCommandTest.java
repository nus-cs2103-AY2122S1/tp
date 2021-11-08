package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.clonePerson;
import static seedu.address.logic.commands.CommandTestUtil.clonePersonInModel;
import static seedu.address.logic.commands.CommandTestUtil.setUpNewModelWithClonedPerson;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ASSIGNMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ASSIGNMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SIXTH_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.person.Person;

public class MarkAssignmentCommandTest {
    private Model model;
    private Person personToShow;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        personToShow = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
    }

    @Test
    void execute_validIndexAssignmentList_success() {
        Person selectedPerson = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Person clonedActualPerson = clonePerson(selectedPerson);
        Person clonedExpectedPerson = clonePerson(selectedPerson);

        Assignment assignmentToMark = selectedPerson.getAssignments()
                .asUnmodifiableObservableList().get(INDEX_FIRST_ASSIGNMENT.getZeroBased());

        Model actualModel = clonePersonInModel(model, selectedPerson, clonedActualPerson);
        Model expectedModel = setUpNewModelWithClonedPerson(selectedPerson, clonedExpectedPerson);

        expectedModel.markAssignment(clonedExpectedPerson, assignmentToMark);

        actualModel.updateAssignmentList(clonedActualPerson);
        MarkAssignmentCommand markAssignmentCommand =
                new MarkAssignmentCommand(INDEX_FIRST_ASSIGNMENT);

        String expectedMessage =
                String.format(MarkAssignmentCommand.MESSAGE_MARK_ASSIGNMENT_SUCCESS,
                        assignmentToMark);
        assertCommandSuccess(markAssignmentCommand, actualModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexAssignmentList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getPersonAssignmentList(personToShow).size() + 1);

        model.updateAssignmentList(personToShow);
        MarkAssignmentCommand markAssignmentCommand =
                new MarkAssignmentCommand(outOfBoundIndex);

        assertCommandFailure(markAssignmentCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_assignmentAlreadyCompleted_throwsCommandException() throws Exception {
        Person selectedPerson = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());

        model.updateAssignmentList(selectedPerson);

        MarkAssignmentCommand markAssignmentCommandFirstTime =
                new MarkAssignmentCommand(INDEX_FIRST_ASSIGNMENT);

        markAssignmentCommandFirstTime.execute(model);

        MarkAssignmentCommand markAssignmentCommandSecondTime =
                new MarkAssignmentCommand(INDEX_FIRST_ASSIGNMENT);

        assertCommandFailure(markAssignmentCommandSecondTime, model,
                MarkAssignmentCommand.MESSAGE_ASSIGNMENT_ALREADY_COMPLETED);
    }

    @Test
    public void equals() {
        Person personInList = model.getFilteredPersonList().get(INDEX_SIXTH_PERSON.getZeroBased());
        model.updateAssignmentList(personInList);

        MarkAssignmentCommand markFirstCommand =
                new MarkAssignmentCommand(INDEX_FIRST_ASSIGNMENT);
        MarkAssignmentCommand markSecondCommand =
                new MarkAssignmentCommand(INDEX_SECOND_ASSIGNMENT);

        // same object -> returns true
        assertTrue(markFirstCommand.equals(markFirstCommand));

        // same values -> returns true
        MarkAssignmentCommand markFirstCommandCopy =
                new MarkAssignmentCommand(INDEX_FIRST_ASSIGNMENT);
        assertTrue(markFirstCommand.equals(markFirstCommandCopy));

        // different types -> returns false
        assertFalse(markFirstCommand.equals(1));

        // null -> returns false
        assertFalse(markFirstCommand.equals(null));

        // different assignment -> returns false
        assertFalse(markFirstCommand.equals(markSecondCommand));
    }
}
