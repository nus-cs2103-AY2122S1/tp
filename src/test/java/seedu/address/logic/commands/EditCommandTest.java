package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TASK_ONE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TASK_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.EditCommand.MESSAGE_DUPLICATE_TASK;
import static seedu.address.logic.commands.EditCommand.MESSAGE_EDIT_PERSON_SUCCESS;
import static seedu.address.logic.commands.EditCommand.MESSAGE_EDIT_TASK_SUCCESS;
import static seedu.address.logic.commands.EditCommand.MESSAGE_INVALID_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Address;
import seedu.address.model.person.Description;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDate;
import seedu.address.model.task.TaskName;
import seedu.address.model.task.TaskTime;
import seedu.address.model.task.Venue;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person editedPerson = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);
        Person person = model.getFilteredPersonList().get(0);
        String expectedMessage = String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(person, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(lastPerson);
        Person editedPerson = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(lastPerson, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, new EditPersonDescriptor());
        Person editedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validTargetIndexFilteredList_success() {
        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditPersonDescriptor emptyPersonDescriptor = new EditPersonDescriptor();
        EditCommand editCommand =
                new EditCommand(INDEX_FIRST_PERSON, emptyPersonDescriptor, Index.fromZeroBased(0), DESC_TASK_ONE);
        List<Task> tasks = new ArrayList<>(personToEdit.getTasks());

        Task taskToEdit = tasks.get(Index.fromZeroBased(0).getZeroBased());
        Task editedTask = new Task(DESC_TASK_ONE.getTaskName().orElse(taskToEdit.getTaskName()),
                DESC_TASK_ONE.getTaskDate().orElse(taskToEdit.getDate()),
                DESC_TASK_ONE.getTaskTime().orElse(taskToEdit.getTime()),
                DESC_TASK_ONE.getTaskVenue().orElse(taskToEdit.getVenue()));
        tasks.set(Index.fromZeroBased(0).getZeroBased(), editedTask);
        Person editedPerson =
                new Person(
                        emptyPersonDescriptor.getName().orElse(personToEdit.getName()),
                        emptyPersonDescriptor.getPhone().orElse(personToEdit.getPhone()),
                        emptyPersonDescriptor.getEmail().orElse(personToEdit.getEmail()),
                        emptyPersonDescriptor.getAddress().orElse(personToEdit.getAddress()),
                        emptyPersonDescriptor.getTags().orElse(personToEdit.getTags()), tasks,
                        emptyPersonDescriptor.getDescription().orElse(personToEdit.getDescription()),
                        emptyPersonDescriptor.getImportance().orElse(personToEdit.isImportant())
                );
        String editedTaskMessage = String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask);
        String expectedMessage = String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson) + "\n"
                + editedTaskMessage;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(personToEdit, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(firstPerson).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // edit person in filtered list into a duplicate in address book
        Person personInList = model.getAddressBook().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditPersonDescriptorBuilder(personInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
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

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_duplicateTaskFilteredList_failure() {
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand =
                new EditCommand(INDEX_FIRST_PERSON, descriptor, Index.fromZeroBased(0),
                        new EditTaskCommand.EditTaskDescriptor()
                );

        assertCommandFailure(editCommand, model, MESSAGE_DUPLICATE_TASK);
    }
    @Test
    public void execute_invalidTaskIndexFilteredList_failure() {
        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Index outOfBoundIndex = Index.fromZeroBased(personToEdit.getTasks().size());
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor, outOfBoundIndex, DESC_TASK_ONE);

        assertCommandFailure(editCommand, model, String.format(MESSAGE_INVALID_TASK, personToEdit.getName()));
    }

    @Test
    public void equals() {
        EditCommand standardCommand = new EditCommand(INDEX_FIRST_PERSON, DESC_AMY);
        EditCommand editTaskCommandOne =
                new EditCommand(INDEX_FIRST_PERSON, DESC_AMY, Index.fromZeroBased(0), DESC_TASK_ONE);
        EditCommand editTaskCommandTwo =
                new EditCommand(INDEX_FIRST_PERSON, DESC_AMY, Index.fromZeroBased(1), DESC_TASK_TWO);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        Command otherCommand = new ClearCommand();
        assertNotEquals(standardCommand, otherCommand);

        // different index -> returns false
        assertNotEquals(standardCommand, new EditCommand(INDEX_SECOND_PERSON, DESC_AMY));

        // different person descriptor -> returns false
        assertNotEquals(standardCommand, new EditCommand(INDEX_FIRST_PERSON, DESC_BOB));

        // different task descriptor -> returns false
        assertNotEquals(editTaskCommandOne, standardCommand);

        // different target task index -> returns false
        assertNotEquals(editTaskCommandTwo, editTaskCommandOne);
    }

}
