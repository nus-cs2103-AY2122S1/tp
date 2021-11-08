package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonBetweenIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_EIGHTH_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_ELEVENTH_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_NINETH_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_TENTH_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

public class DeleteMultipleCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexEightToElevenUnfilteredList_success() {
        DeleteMultipleCommand deleteMultipleCommand = new DeleteMultipleCommand(INDEX_EIGHTH_PERSON,
                INDEX_ELEVENTH_PERSON);

        Person eighth = model.getFilteredPersonList().get(INDEX_EIGHTH_PERSON.getZeroBased());
        Person nineth = model.getFilteredPersonList().get(INDEX_NINETH_PERSON.getZeroBased());
        Person tenth = model.getFilteredPersonList().get(INDEX_TENTH_PERSON.getZeroBased());
        Person eleventh = model.getFilteredPersonList().get(INDEX_ELEVENTH_PERSON.getZeroBased());
        String expectedMessage = new StringBuilder(DeleteMultipleCommand.MESSAGE_DELETE_MULTIPLE_PERSON_SUCCESS)
                .append(String.format("\n%1$s", eighth))
                .append(String.format("\n%1$s", nineth))
                .append(String.format("\n%1$s", tenth))
                .append(String.format("\n%1$s", eleventh))
                .toString();

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(eighth);
        expectedModel.deletePerson(nineth);
        expectedModel.deletePerson(tenth);
        expectedModel.deletePerson(eleventh);

        assertCommandSuccess(deleteMultipleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index validIndex = Index.fromOneBased(model.getFilteredPersonList().size());
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteMultipleCommand deleteMultipleCommand = new DeleteMultipleCommand(validIndex, outOfBoundIndex);

        assertCommandFailure(deleteMultipleCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredListDeleteThreePersons_success() {
        showPersonBetweenIndex(model, INDEX_FIRST_PERSON, INDEX_THIRD_PERSON);

        Person personFirstToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person personSecondToDelete = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Person personThirdToDelete = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        DeleteMultipleCommand deleteCommand = new DeleteMultipleCommand(INDEX_FIRST_PERSON, INDEX_THIRD_PERSON);

        String expectedMessage = String.format(DeleteMultipleCommand.MESSAGE_DELETE_MULTIPLE_PERSON_SUCCESS
                + "\n%1$s\n%2$s\n%3$s", personFirstToDelete, personSecondToDelete, personThirdToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        expectedModel.deletePerson(personFirstToDelete);
        expectedModel.deletePerson(personSecondToDelete);
        expectedModel.deletePerson(personThirdToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonBetweenIndex(model, INDEX_FIRST_PERSON, INDEX_SECOND_PERSON);

        Index outOfBoundIndex = INDEX_THIRD_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteMultipleCommand deleteFirstToThird = new DeleteMultipleCommand(INDEX_FIRST_PERSON,
                INDEX_THIRD_PERSON);
        DeleteMultipleCommand deleteThirdToEighth = new DeleteMultipleCommand(INDEX_FIRST_PERSON,
                INDEX_EIGHTH_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstToThird.equals(deleteFirstToThird));

        // same values -> returns true
        DeleteMultipleCommand deleteFirstToThirdCopy = new DeleteMultipleCommand(INDEX_FIRST_PERSON,
                INDEX_THIRD_PERSON);
        assertTrue(deleteFirstToThird.equals(deleteFirstToThirdCopy));

        // different types -> returns false
        assertFalse(deleteFirstToThird.equals(1));

        // null -> returns false
        assertFalse(deleteFirstToThird.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstToThird.equals(deleteThirdToEighth));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
