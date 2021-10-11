package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.LastVisit;
import seedu.address.model.person.Person;
import seedu.address.model.person.Visit;




public class DoneCommandTest {
    private static final String VISIT_STUB = "2021-11-11 12:00";
    private static final Optional<Visit> EMPTY_VISIT = Optional.ofNullable(new Visit(""));

    // TODO: add in integration testing with delete command and last visited field
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDone = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        String personNewLastVisitedDate = personToDone.getVisit().toString();
        Optional<LastVisit> personNewLastVisited = Optional.of(new LastVisit(personNewLastVisitedDate));
        Person donePerson = new Person(personToDone.getName(), personToDone.getPhone(), personToDone.getLanguage(),
                personToDone.getAddress(), personNewLastVisited, EMPTY_VISIT,
                personToDone.getFrequency(), personToDone.getOccurrence(), personToDone.getTags());

        DoneCommand doneCommand = new DoneCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(
                DoneCommand.MESSAGE_DONE_PERSON_SUCCESS, personToDone);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(personToDone, donePerson);

        assertCommandSuccess(doneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noScheduledVisit_throwsCommandException() {
        Person personToDone = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person personToDoneNoVisit = new Person(personToDone.getName(), personToDone.getPhone(),
                personToDone.getLanguage(), personToDone.getAddress(),
                personToDone.getLastVisit(), EMPTY_VISIT, personToDone.getFrequency(),
                personToDone.getOccurrence(), personToDone.getTags());
        ModelManager newModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        newModel.setPerson(personToDone, personToDoneNoVisit);

        DoneCommand doneCommand = new DoneCommand(INDEX_FIRST_PERSON);

        assertCommandFailure(doneCommand, newModel, Messages.MESSAGE_NO_SCHEDULED_VISIT);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DoneCommand doneCommand = new DoneCommand(outOfBoundIndex);

        assertCommandFailure(doneCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDone = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        String personNewLastVisitedDate = personToDone.getVisit().toString();
        Optional<LastVisit> personNewLastVisited = Optional.of(new LastVisit(personNewLastVisitedDate));
        Person donePerson = new Person(personToDone.getName(), personToDone.getPhone(), personToDone.getLanguage(),
                personToDone.getAddress(), personNewLastVisited, EMPTY_VISIT,
                personToDone.getFrequency(), personToDone.getOccurrence(), personToDone.getTags());

        DoneCommand doneCommand = new DoneCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(
                DoneCommand.MESSAGE_DONE_PERSON_SUCCESS, personToDone);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(personToDone, donePerson);

        assertCommandSuccess(doneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DoneCommand doneCommand = new DoneCommand(outOfBoundIndex);

        assertCommandFailure(doneCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }


    @Test
    public void equals() {
        DoneCommand doneFirstCommand = new DoneCommand(INDEX_FIRST_PERSON);
        DoneCommand doneSecondCommand = new DoneCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(doneFirstCommand.equals(doneFirstCommand));

        // same values -> returns true
        DoneCommand doneFirstCommandCopy = new DoneCommand(INDEX_FIRST_PERSON);
        assertTrue(doneFirstCommand.equals(doneFirstCommandCopy));

        // different types -> returns false
        assertFalse(doneFirstCommand.equals(3));

        // null -> returns false
        assertFalse(doneFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(doneFirstCommand.equals(doneSecondCommand));
    }
}
