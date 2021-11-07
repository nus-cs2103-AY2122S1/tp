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
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
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
    private EditCommand.EditTaskDescriptor editTaskDescriptor;

    @BeforeEach
    public void setUp() {
        editTaskDescriptor = new EditCommand.EditTaskDescriptor();
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person editedPerson = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);
        Person person = model.getFilteredPersonList().get(0);
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

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

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(lastPerson, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, new EditPersonDescriptor());
        Person editedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);
        expectedModel.setPerson(
                expectedModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()), editedPerson);

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
    public void execute_filteredListTask_success() {
        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        model.displayPersonTaskList(personToEdit);

        List<Task> tasks = new ArrayList<>(personToEdit.getTasks());
        Task newTask = new Task(new TaskName("walk"), null, null, null);
        editTaskDescriptor.setTaskName(new TaskName("walk"));
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().build();
        tasks.set(0, newTask);
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getTags(), tasks, personToEdit.getDescription(),
                personToEdit.isImportant()
        );
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor, INDEX_FIRST_TASK, editTaskDescriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson) + "\n"
                + String.format(EditCommand.MESSAGE_EDIT_TASK_SUCCESS, newTask);

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setPerson(
                expectedModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()), editedPerson);
        expectedModel.displayPersonTaskList(
                expectedModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()));

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredListPersonAndTask_success() {
        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        model.displayPersonTaskList(personToEdit);

        List<Task> tasks = new ArrayList<>(personToEdit.getTasks());
        Task newTask = new Task(new TaskName("walk"), null, null, null);
        editTaskDescriptor.setTaskName(new TaskName("walk"));
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        tasks.set(0, newTask);
        Person editedPerson = new Person(
                new Name(VALID_NAME_BOB), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getTags(), tasks, personToEdit.getDescription(),
                personToEdit.isImportant()
        );
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor, INDEX_FIRST_TASK, editTaskDescriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson) + "\n"
                + String.format(EditCommand.MESSAGE_EDIT_TASK_SUCCESS, newTask);

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.displayPersonTaskList(
                expectedModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()));
        expectedModel.setPerson(
                expectedModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_outOfBoundsPersonIndexFilteredList_failure() {
        Person person = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        model.displayPersonTaskList(person);

        Index outOfBoundIndex = Index.fromZeroBased(person.getTasks().size());
        editTaskDescriptor.setTaskName(new TaskName("walk"));
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().build();
        EditCommand editCommand = new EditCommand(
                INDEX_FIRST_PERSON, descriptor, outOfBoundIndex, editTaskDescriptor
        );
        assertCommandFailure(
                editCommand, model, String.format(EditCommand.MESSAGE_INVALID_TASK, person.getName())
        );
    }

    @Test
    public void execute_duplicateTask_failure() {
        Person person = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        model.displayPersonTaskList(person);

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().build();
        Task task = person.getTasks().get(0);
        editTaskDescriptor.setTaskName(task.getTaskName());
        editTaskDescriptor.setTaskDate(task.getDate());
        editTaskDescriptor.setTaskTime(task.getTime());
        editTaskDescriptor.setTaskVenue(task.getVenue());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor, INDEX_FIRST_TASK, editTaskDescriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_TASK);
    }

    @Test
    public void equals() {
        EditCommand standardCommand = new EditCommand(INDEX_FIRST_PERSON, DESC_AMY);
        EditCommand editTaskCommandOne =
                new EditCommand(INDEX_FIRST_PERSON, DESC_AMY, Index.fromZeroBased(0), DESC_TASK_ONE);
        EditCommand editTaskCommandTwo =
                new EditCommand(INDEX_FIRST_PERSON, DESC_AMY, Index.fromZeroBased(1), DESC_TASK_TWO);
        EditCommand.EditTaskDescriptor editTaskDescriptorOne = new EditCommand.EditTaskDescriptor();
        editTaskDescriptorOne.setTaskName(new TaskName("work"));
        editTaskDescriptorOne.setTaskDate(new TaskDate("2017-12-12"));
        editTaskDescriptorOne.setTaskTime(new TaskTime("15:30"));
        editTaskDescriptorOne.setTaskVenue(new Venue("dummy"));
        EditCommand.EditTaskDescriptor editTaskDescriptorTwo = new EditCommand.EditTaskDescriptor();
        editTaskDescriptorTwo.setTaskName(new TaskName("sleep"));
        editTaskDescriptorTwo.setTaskDate(new TaskDate("2017-12-12"));
        editTaskDescriptorTwo.setTaskTime(new TaskTime("15:30"));
        editTaskDescriptorTwo.setTaskVenue(new Venue("dummy"));

        // same values -> returns true
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, DESC_AMY);
        EditCommand.EditTaskDescriptor editTaskDescriptorOneCopy =
                new EditCommand.EditTaskDescriptor(editTaskDescriptorOne);

        assertEquals(standardCommand, commandWithSameValues);
        assertEquals(editTaskDescriptorOne, editTaskDescriptorOneCopy);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);
        assertEquals(editTaskDescriptorOne, editTaskDescriptorOne);

        // null -> returns false
        assertNotEquals(null, standardCommand);
        assertNotEquals(null, editTaskDescriptorOne);

        // different types -> returns false
        Command otherCommand = new ClearCommand();
        assertNotEquals(standardCommand, otherCommand);
        assertNotEquals(editTaskDescriptorOne, editTaskCommandOne);

        // different index -> returns false
        assertNotEquals(standardCommand, new EditCommand(INDEX_SECOND_PERSON, DESC_AMY));

        // different person descriptor -> returns false
        assertNotEquals(standardCommand, new EditCommand(INDEX_FIRST_PERSON, DESC_BOB));

        // different task descriptor -> returns false
        assertNotEquals(editTaskCommandOne, standardCommand);
        assertNotEquals(editTaskDescriptorOne, editTaskDescriptorTwo);

        // different target task index -> returns false
        assertNotEquals(editTaskCommandTwo, editTaskCommandOne);
    }

}
