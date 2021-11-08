package seedu.address.logic.candidate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.candidate.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.address.logic.candidate.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.logic.candidate.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.candidate.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.candidate.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalHrManager;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.general.ClearCommand;
import seedu.address.model.HrManager;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RemarkCommand.
 */
public class RemarkCandidateCommandTest {

    private static final String REMARK_STUB = "Some remark";

    private Model model = new ModelManager(getTypicalHrManager(), new UserPrefs());

    @Test
    public void execute_addRemarkUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withRemark(REMARK_STUB).build();

        RemarkCandidateCommand remarkCandidateCommand = new RemarkCandidateCommand(INDEX_FIRST_PERSON,
                new Remark(editedPerson.getRemark().value));

        String expectedMessage = String.format(RemarkCandidateCommand.MESSAGE_ADD_REMARK_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new HrManager(model.getHrManager()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(remarkCandidateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteRemarkUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withRemark("").build();

        RemarkCandidateCommand remarkCandidateCommand = new RemarkCandidateCommand(INDEX_FIRST_PERSON,
                new Remark(editedPerson.getRemark().toString()));

        String expectedMessage = String.format(RemarkCandidateCommand.MESSAGE_DELETE_REMARK_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new HrManager(model.getHrManager()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(remarkCandidateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()))
                .withRemark(REMARK_STUB).build();

        RemarkCandidateCommand remarkCandidateCommand = new RemarkCandidateCommand(INDEX_FIRST_PERSON,
                new Remark(editedPerson.getRemark().value));

        String expectedMessage = String.format(RemarkCandidateCommand.MESSAGE_ADD_REMARK_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new HrManager(model.getHrManager()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);

        assertCommandSuccess(remarkCandidateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        RemarkCandidateCommand remarkCandidateCommand = new RemarkCandidateCommand(outOfBoundIndex,
                new Remark(VALID_REMARK_BOB));

        assertCommandFailure(remarkCandidateCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getHrManager().getPersonList().size());

        RemarkCandidateCommand remarkCandidateCommand = new RemarkCandidateCommand(outOfBoundIndex,
                new Remark(VALID_REMARK_BOB));

        assertCommandFailure(remarkCandidateCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final RemarkCandidateCommand standardCommand = new RemarkCandidateCommand(INDEX_FIRST_PERSON,
                new Remark(VALID_REMARK_AMY));
        // same values -> returns true
        RemarkCandidateCommand commandWithSameValues = new RemarkCandidateCommand(INDEX_FIRST_PERSON,
                new Remark(VALID_REMARK_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));
        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));
        // null -> returns false
        assertFalse(standardCommand.equals(null));
        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));
        // different index -> returns false
        assertFalse(standardCommand.equals(new RemarkCandidateCommand(INDEX_SECOND_PERSON,
                new Remark(VALID_REMARK_AMY))));
        // different remark -> returns false
        assertFalse(standardCommand.equals(new RemarkCandidateCommand(INDEX_FIRST_PERSON,
                new Remark(VALID_REMARK_BOB))));
    }
}
