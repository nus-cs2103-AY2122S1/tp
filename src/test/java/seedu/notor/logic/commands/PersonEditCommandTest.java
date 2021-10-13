package seedu.notor.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.notor.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.notor.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.notor.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.notor.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.notor.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.notor.logic.commands.CommandTestUtil.assertExecuteFailure;
import static seedu.notor.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.notor.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.notor.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.notor.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.notor.commons.core.Messages;
import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.person.PersonEditCommand;
import seedu.notor.logic.executors.Executor;
import seedu.notor.logic.executors.person.PersonEditExecutor;
import seedu.notor.logic.executors.person.PersonEditExecutor.PersonEditDescriptor;
import seedu.notor.model.AddressBook;
import seedu.notor.model.Model;
import seedu.notor.model.ModelManager;
import seedu.notor.model.UserPrefs;
import seedu.notor.model.person.Person;
import seedu.notor.testutil.PersonBuilder;
import seedu.notor.testutil.PersonEditDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class PersonEditCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @BeforeEach
    public void setUp() {
        Executor.setup(model);
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person editedPerson = new PersonBuilder().build();
        PersonEditDescriptor descriptor = new PersonEditDescriptorBuilder(editedPerson).build();
        PersonEditCommand personEditCommand = new PersonEditCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(PersonEditExecutor.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        CommandTestUtil.assertExecuteSuccess(personEditCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(lastPerson);
        Person editedPerson = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        PersonEditDescriptor descriptor = new PersonEditDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        PersonEditCommand personEditCommand = new PersonEditCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(PersonEditExecutor.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(lastPerson, editedPerson);

        CommandTestUtil.assertExecuteSuccess(personEditCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        Person editedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        PersonEditCommand
                personEditCommand = new PersonEditCommand(INDEX_FIRST_PERSON, new PersonEditDescriptor());

        String expectedMessage = String.format(PersonEditExecutor.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        CommandTestUtil.assertExecuteSuccess(personEditCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList).withName(VALID_NAME_BOB).build();
        PersonEditCommand personEditCommand = new PersonEditCommand(INDEX_FIRST_PERSON,
                new PersonEditDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(PersonEditExecutor.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        CommandTestUtil.assertExecuteSuccess(personEditCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        PersonEditDescriptor descriptor = new PersonEditDescriptorBuilder(firstPerson).build();
        PersonEditCommand personEditCommand = new PersonEditCommand(INDEX_SECOND_PERSON, descriptor);

        assertExecuteFailure(personEditCommand, model, PersonEditExecutor.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // edit person in filtered list into a duplicate in address book
        Person personInList = model.getAddressBook().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        PersonEditCommand personEditCommand = new PersonEditCommand(INDEX_FIRST_PERSON,
                new PersonEditDescriptorBuilder(personInList).build());

        assertExecuteFailure(personEditCommand, model, PersonEditExecutor.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        PersonEditDescriptor descriptor = new PersonEditDescriptorBuilder().withName(VALID_NAME_BOB).build();
        PersonEditCommand personEditCommand = new PersonEditCommand(outOfBoundIndex, descriptor);

        assertExecuteFailure(personEditCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
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
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        PersonEditCommand personEditCommand = new PersonEditCommand(outOfBoundIndex,
                new PersonEditDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertExecuteFailure(personEditCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final PersonEditCommand standardCommand = new PersonEditCommand(INDEX_FIRST_PERSON, DESC_AMY);

        // same values -> returns true
        PersonEditDescriptor copyDescriptor = new PersonEditDescriptor(DESC_AMY);
        PersonEditCommand
                commandWithSameValues = new PersonEditCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different index -> returns false
        assertNotEquals(standardCommand, new PersonEditCommand(INDEX_SECOND_PERSON, DESC_AMY));

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new PersonEditCommand(INDEX_FIRST_PERSON, DESC_BOB));
    }

}
