package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
import seedu.address.model.person.Frequency;
import seedu.address.model.person.Occurrence;
import seedu.address.model.person.Person;
import seedu.address.model.person.Visit;
import seedu.address.testutil.DateTimeUtil;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for VisitCommand.
 */
public class VisitCommandTest {

    private static final String VISIT_STUB = DateTimeUtil.getValidVisitString();

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addVisitUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withVisit(VISIT_STUB).build();
        Optional<Visit> visit = Optional.of(new Visit(editedPerson.getVisit().get().value));
        Optional<Frequency> frequency = Optional.of(Frequency.EMPTY);
        Optional<Occurrence> occurrence = Optional.of(new Occurrence(1));

        VisitCommand visitCommand = new VisitCommand(INDEX_FIRST_PERSON, visit, frequency, occurrence);

        String expectedMessage = String.format(VisitCommand.MESSAGE_ADD_VISIT_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(visitCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addRecurringVisitUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withVisit(VISIT_STUB).build();
        Optional<Visit> visit = Optional.of(new Visit(editedPerson.getVisit().get().value));
        Optional<Frequency> frequency = Optional.of(Frequency.WEEKLY);
        Optional<Occurrence> occurrence = Optional.of(new Occurrence(2));

        VisitCommand visitCommand = new VisitCommand(INDEX_FIRST_PERSON, visit, frequency, occurrence);

        String expectedMessage = String.format(VisitCommand.MESSAGE_ADD_RECURRING_VISIT_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(visitCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addVisitFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()))
                .withVisit(VISIT_STUB).build();
        Optional<Visit> visit = Optional.of(new Visit(editedPerson.getVisit().get().value));
        Optional<Frequency> frequency = Optional.of(Frequency.EMPTY);
        Optional<Occurrence> occurrence = Optional.of(new Occurrence(1));

        VisitCommand visitCommand = new VisitCommand(INDEX_FIRST_PERSON, visit, frequency, occurrence);

        String expectedMessage = String.format(VisitCommand.MESSAGE_ADD_VISIT_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(visitCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addRecurringVisitFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()))
                .withVisit(VISIT_STUB).build();
        Optional<Visit> visit = Optional.of(new Visit(editedPerson.getVisit().get().value));
        Optional<Frequency> frequency = Optional.of(Frequency.WEEKLY);
        Optional<Occurrence> occurrence = Optional.of(new Occurrence(2));

        VisitCommand visitCommand = new VisitCommand(INDEX_FIRST_PERSON, visit, frequency, occurrence);

        String expectedMessage = String.format(VisitCommand.MESSAGE_ADD_RECURRING_VISIT_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(visitCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addInvalidRecurringVisitFilteredList_throwCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person editedPerson = new PersonBuilder(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()))
                .withVisit(VISIT_STUB).build();
        Optional<Visit> visit = Optional.of(new Visit(editedPerson.getVisit().get().value));
        Optional<Frequency> frequency = Optional.of(Frequency.EMPTY);
        Optional<Occurrence> occurrence = Optional.of(new Occurrence(2));

        VisitCommand visitCommand = new VisitCommand(INDEX_FIRST_PERSON, visit, frequency, occurrence);
        assertCommandFailure(visitCommand, model, VisitCommand.MESSAGE_INVALID_OPTIONAL_FLAG);

    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        Optional<Visit> visit = Optional.of(new Visit(VALID_VISIT_BOB));
        Optional<Frequency> frequency = Optional.of(Frequency.EMPTY);
        Optional<Occurrence> occurrence = Optional.of(new Occurrence(1));
        VisitCommand visitCommand = new VisitCommand(outOfBoundIndex, visit, frequency, occurrence);

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
        Optional<Visit> visit = Optional.of(new Visit(VALID_VISIT_BOB));
        Optional<Frequency> frequency = Optional.of(Frequency.EMPTY);
        Optional<Occurrence> occurrence = Optional.of(new Occurrence(1));
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        VisitCommand visitCommand = new VisitCommand(outOfBoundIndex, visit, frequency, occurrence);

        assertCommandFailure(visitCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Optional<Visit> visit = Optional.of(new Visit(VALID_VISIT_AMY));
        Optional<Visit> differentVisit = Optional.of(new Visit(VALID_VISIT_BOB));
        Optional<Frequency> frequency = Optional.of(Frequency.EMPTY);
        Optional<Occurrence> occurrence = Optional.of(new Occurrence(1));
        final VisitCommand standardCommand = new VisitCommand(INDEX_FIRST_PERSON, visit, frequency, occurrence);

        // same values -> returns true
        VisitCommand commandWithSameValues = new VisitCommand(INDEX_FIRST_PERSON, visit, frequency, occurrence);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different index -> returns false
        assertNotEquals(standardCommand, new VisitCommand(INDEX_SECOND_PERSON, visit, frequency, occurrence));

        // different visit -> returns false
        assertNotEquals(standardCommand, new VisitCommand(INDEX_FIRST_PERSON, differentVisit,
                frequency, occurrence));
    }
}
