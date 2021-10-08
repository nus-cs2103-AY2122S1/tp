package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VISIT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VISIT_BOB;
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
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Visit;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for VisitCommand.
 */
public class VisitCommandTest {

    private static final String VISIT_STUB = "2021-11-11";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addVisitUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withVisit(VISIT_STUB).build();
        Optional<Visit> visit = Optional.ofNullable(new Visit(editedPerson.getVisit().get().value));

        VisitCommand visitCommand = new VisitCommand(INDEX_FIRST_PERSON, visit);

        String expectedMessage = String.format(VisitCommand.MESSAGE_ADD_VISIT_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(visitCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()))
                .withVisit(VISIT_STUB).build();
        Optional<Visit> visit = Optional.ofNullable(new Visit(editedPerson.getVisit().get().value));

        VisitCommand visitCommand = new VisitCommand(INDEX_FIRST_PERSON, visit);

        String expectedMessage = String.format(VisitCommand.MESSAGE_ADD_VISIT_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(visitCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        Optional<Visit> visit = Optional.ofNullable(new Visit(VALID_VISIT_BOB));
        VisitCommand visitCommand = new VisitCommand(outOfBoundIndex, visit);

        assertCommandFailure(visitCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        Optional<Visit> visit = Optional.ofNullable(new Visit(VALID_VISIT_BOB));
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        VisitCommand visitCommand = new VisitCommand(outOfBoundIndex, visit);

        assertCommandFailure(visitCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Optional<Visit> visit = Optional.ofNullable(new Visit(VALID_VISIT_AMY));
        Optional<Visit> differentVisit = Optional.ofNullable(new Visit(VALID_VISIT_BOB));
        final VisitCommand standardCommand = new VisitCommand(INDEX_FIRST_PERSON, visit);

        // same values -> returns true
        VisitCommand commandWithSameValues = new VisitCommand(INDEX_FIRST_PERSON, visit);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new VisitCommand(INDEX_SECOND_PERSON, visit)));

        // different visit -> returns false
        assertFalse(standardCommand.equals(new VisitCommand(INDEX_FIRST_PERSON, differentVisit)));
    }
}
