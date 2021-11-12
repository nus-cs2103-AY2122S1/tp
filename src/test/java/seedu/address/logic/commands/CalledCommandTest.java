package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.IsCalled;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for CalledCommand.
 */
public class CalledCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_personAcceptedByModel_calledSuccessful() {
        Person person = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person calledPerson = new Person(person.getName(), person.getPhone(), person.getEmail(),
                new IsCalled("TRUE"), person.getAddress(), person.getGender(), person.getAge(), person.getInterests());
        CalledCommand calledCommand = new CalledCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(CalledCommand.MESSAGE_DONE_CALL_SUCCESS, calledPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), calledPerson);

        assertCommandSuccess(calledCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        CalledCommand calledCommand = new CalledCommand(outOfBoundIndex);

        assertCommandFailure(calledCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_personAlreadyMarkedAsCalled_showAppropriateMessage() {
        Person person = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person calledPerson = new Person(person.getName(), person.getPhone(), person.getEmail(),
                new IsCalled("TRUE"), person.getAddress(), person.getGender(), person.getAge(), person.getInterests());
        model.setPerson(person, calledPerson);

        CalledCommand calledCommand = new CalledCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(CalledCommand.MESSAGE_ALREADY_DONE_CALL, person);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(calledCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        final CalledCommand standardCommand = new CalledCommand(INDEX_FIRST_PERSON);

        // same values -> returns true
        CalledCommand commandWithSameValues = new CalledCommand(INDEX_FIRST_PERSON);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new CalledCommand(INDEX_SECOND_PERSON)));
    }
}
