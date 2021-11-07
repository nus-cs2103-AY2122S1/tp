package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSON_DETAILS_FOUND;
import static seedu.address.commons.core.Messages.MESSAGE_PERSON_DETAILS_NOT_FOUND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ITEM;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameEqualKeywordPredicate;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) for {@code DetailsCommand}.
 */
class DetailsCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameEqualKeywordPredicate firstPredicate =
                new NameEqualKeywordPredicate(new Name("first"));
        NameEqualKeywordPredicate secondPredicate =
                new NameEqualKeywordPredicate(new Name("second"));

        DetailsCommand detailsFirstCommand = new DetailsCommand(firstPredicate);
        DetailsCommand detailsSecondCommand = new DetailsCommand(secondPredicate);
        DetailsCommand detailsThirdCommand = new DetailsCommand(Index.fromOneBased(1));
        DetailsCommand detailsFourthCommand = new DetailsCommand(Index.fromOneBased(2));

        // equivalence partitions testing
        // same object -> returns true
        assertTrue(detailsFirstCommand.equals(detailsFirstCommand));

        // same values -> returns true
        DetailsCommand detailsFirstCommandCopy = new DetailsCommand(firstPredicate);
        assertTrue(detailsFirstCommand.equals(detailsFirstCommandCopy));

        // different types -> returns false
        assertFalse(detailsFirstCommand.equals(1));
        assertFalse(detailsThirdCommand.equals(1));

        // null -> returns false
        assertFalse(detailsFirstCommand.equals(null));
        assertFalse(detailsThirdCommand.equals(null));

        // different person -> returns false
        assertFalse(detailsFirstCommand.equals(detailsSecondCommand));

        // same index -> returns true
        assertTrue(detailsThirdCommand.equals(new DetailsCommand(Index.fromOneBased(1))));

        // different index -> returns false
        assertFalse(detailsThirdCommand.equals(detailsFourthCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String name = "cool name";
        String expectedMessage = String.format(MESSAGE_PERSON_DETAILS_NOT_FOUND, name);
        NameEqualKeywordPredicate predicate = new NameEqualKeywordPredicate(new Name(name));
        DetailsCommand command = new DetailsCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        Name name = CARL.getName();
        String expectedMessage = String.format(MESSAGE_PERSON_DETAILS_FOUND, name);
        NameEqualKeywordPredicate predicate = new NameEqualKeywordPredicate(name);
        DetailsCommand command = new DetailsCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(CARL), model.getFilteredPersonList());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDisplay = model.getFilteredPersonList().get(INDEX_FIRST_ITEM.getZeroBased());
        DetailsCommand detailsCommand = new DetailsCommand(INDEX_FIRST_ITEM);

        String expectedMessage = String.format(Messages.MESSAGE_PERSON_DETAILS_FOUND, personToDisplay.getName());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredPersonList(x -> x.getName().equals(personToDisplay.getName()));

        assertCommandSuccess(detailsCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DetailsCommand detailsCommand = new DetailsCommand(outOfBoundIndex);

        assertCommandFailure(detailsCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_ITEM);

        Person personToDisplay = model.getFilteredPersonList().get(INDEX_FIRST_ITEM.getZeroBased());
        DetailsCommand detailsCommand = new DetailsCommand(INDEX_FIRST_ITEM);

        String expectedMessage = String.format(Messages.MESSAGE_PERSON_DETAILS_FOUND, personToDisplay.getName());

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredPersonList(x -> x.getName().equals(personToDisplay.getName()));

        assertCommandSuccess(detailsCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_ITEM);

        Index outOfBoundIndex = INDEX_SECOND_ITEM;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DetailsCommand detailsCommand = new DetailsCommand(outOfBoundIndex);

        assertCommandFailure(detailsCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
}
