package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Contains integration tests (interaction with the Model) and unit tests for DeleteTagCommand.
 */
public class DeleteTagCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_indexAndTagSpecifiedUnfilteredList_success() {
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Set<Tag> tagsToDelete = personToEdit.getTags();
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(INDEX_FIRST_PERSON, tagsToDelete);

        for (Tag tagToDelete: tagsToDelete) {
            personToEdit = DeleteTagCommand.deleteTag(personToEdit, tagToDelete);
        }

        String expectedMessage =
                String.format(DeleteTagCommand.MESSAGE_DELETED_TAG_SUCCESS, DeleteTagCommand.tagString(tagsToDelete))
                        + " " + String.format(DeleteTagCommand.MESSAGE_EDIT_PERSON_SUCCESS, personToEdit);


        expectedModel.setPerson(model.getFilteredPersonList().get(0), personToEdit);

        assertCommandSuccess(deleteTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_indexOnlySpecifiedUnfilteredList_success() {
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(INDEX_FIRST_PERSON, null);

        personToEdit = DeleteTagCommand.deleteTag(personToEdit, null);

        String expectedMessage =
                String.format(DeleteTagCommand.MESSAGE_DELETED_ALL_TAG_SUCCESS)
                        + " " + String.format(DeleteTagCommand.MESSAGE_EDIT_PERSON_SUCCESS, personToEdit);

        expectedModel.setPerson(model.getFilteredPersonList().get(0), personToEdit);

        assertCommandSuccess(deleteTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_tagOnlySpecifiedUnfilteredList_success() {
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        Person firstPersonToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Set<Tag> tagsToDelete = firstPersonToEdit.getTags();
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(null, tagsToDelete);

        for (int i = 0; i < model.getFilteredPersonList().size(); i++) {
            Person personToEdit = model.getFilteredPersonList().get(i);
            for (Tag tagToDelete: tagsToDelete) {
                personToEdit = DeleteTagCommand.deleteTag(personToEdit, tagToDelete);
            }
            expectedModel.setPerson(model.getFilteredPersonList().get(i), personToEdit);
        }

        String expectedMessage =
                String.format(DeleteTagCommand.MESSAGE_DELETED_TAG_SUCCESS, DeleteTagCommand.tagString(tagsToDelete));

        assertCommandSuccess(deleteTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noIndexOrTagSpecifiedUnfilteredList_success() {
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(null, null);

        for (int i = 0; i < model.getFilteredPersonList().size(); i++) {
            Person personToEdit = model.getFilteredPersonList().get(i);
            personToEdit = DeleteTagCommand.deleteTag(personToEdit, null);
            expectedModel.setPerson(model.getFilteredPersonList().get(i), personToEdit);
        }

        String expectedMessage = DeleteTagCommand.MESSAGE_DELETED_ALL_TAG_SUCCESS;

        assertCommandSuccess(deleteTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_indexAndTagSpecifiedFilteredList_success() {
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);

        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Set<Tag> tagsToDelete = personToEdit.getTags();
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(INDEX_FIRST_PERSON, personToEdit.getTags());

        for (Tag tagToDelete: tagsToDelete) {
            personToEdit = DeleteTagCommand.deleteTag(personToEdit, tagToDelete);
        }

        String expectedMessage =
                String.format(DeleteTagCommand.MESSAGE_DELETED_TAG_SUCCESS, DeleteTagCommand.tagString(tagsToDelete))
                        + " " + String.format(DeleteTagCommand.MESSAGE_EDIT_PERSON_SUCCESS, personToEdit);


        expectedModel.setPerson(model.getFilteredPersonList().get(0), personToEdit);

        assertCommandSuccess(deleteTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_indexOnlySpecifiedFilteredList_success() {
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);

        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(INDEX_FIRST_PERSON, null);

        personToEdit = DeleteTagCommand.deleteTag(personToEdit, null);

        String expectedMessage =
                String.format(DeleteTagCommand.MESSAGE_DELETED_ALL_TAG_SUCCESS)
                        + " " + String.format(DeleteTagCommand.MESSAGE_EDIT_PERSON_SUCCESS, personToEdit);

        expectedModel.setPerson(model.getFilteredPersonList().get(0), personToEdit);

        assertCommandSuccess(deleteTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_tagOnlySpecifiedFilteredList_success() {
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);

        Person firstPersonToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Set<Tag> tagsToDelete = firstPersonToEdit.getTags();
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(null, tagsToDelete);

        for (int i = 0; i < model.getFilteredPersonList().size(); i++) {
            Person personToEdit = model.getFilteredPersonList().get(i);
            for (Tag tagToDelete: tagsToDelete) {
                personToEdit = DeleteTagCommand.deleteTag(personToEdit, tagToDelete);
            }
            expectedModel.setPerson(model.getFilteredPersonList().get(i), personToEdit);
        }

        String expectedMessage =
                String.format(DeleteTagCommand.MESSAGE_DELETED_TAG_SUCCESS, DeleteTagCommand.tagString(tagsToDelete));

        assertCommandSuccess(deleteTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noIndexOrTagSpecifiedFilteredList_success() {
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);

        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(null, null);

        for (int i = 0; i < model.getFilteredPersonList().size(); i++) {
            Person personToEdit = model.getFilteredPersonList().get(i);
            personToEdit = DeleteTagCommand.deleteTag(personToEdit, null);
            expectedModel.setPerson(model.getFilteredPersonList().get(i), personToEdit);
        }

        String expectedMessage = DeleteTagCommand.MESSAGE_DELETED_ALL_TAG_SUCCESS;

        assertCommandSuccess(deleteTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexWithTagUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);

        Set<Tag> placeholderTag = new HashSet<>(Arrays.asList(
                new Tag("Thisshouldnotbeinthefinallist")));

        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(outOfBoundIndex, placeholderTag);
        assertCommandFailure(deleteTagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexWithoutTagUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);

        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(outOfBoundIndex, null);
        assertCommandFailure(deleteTagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexWithTagFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        Set<Tag> placeholderTag = new HashSet<>(Arrays.asList(
                new Tag("Thisshouldnotbeinthefinallist")));

        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(outOfBoundIndex, placeholderTag);
        assertCommandFailure(deleteTagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexWithoutTagFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(outOfBoundIndex, null);
        assertCommandFailure(deleteTagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_tagAbsentWithoutIndexFilteredList_failure() {
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);

        Person firstPersonToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Set<Tag> tagsToDelete = new HashSet<>(firstPersonToEdit.getTags());
        String initialTagString = DeleteTagCommand.tagString(tagsToDelete);
        tagsToDelete.add(new Tag("Thisshouldnotbeinthefinallist")); //Extra Tag, causing error
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(null, tagsToDelete);

        for (int i = 0; i < model.getFilteredPersonList().size(); i++) {
            Person personToEdit = model.getFilteredPersonList().get(i);
            for (Tag tagToDelete: tagsToDelete) {
                personToEdit = DeleteTagCommand.deleteTag(personToEdit, tagToDelete);
            }
            expectedModel.setPerson(model.getFilteredPersonList().get(i), personToEdit);
        }

        String expectedMessage = String.format(DeleteTagCommand.MESSAGE_DELETED_TAG_SUCCESS, initialTagString);

        assertCommandSuccess(deleteTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_tagAbsentWithIndexSpecifiedFilteredList_failure() {
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);

        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Set<Tag> tagsToDelete = new HashSet<>(personToEdit.getTags());
        String initialTagString = DeleteTagCommand.tagString(tagsToDelete);
        tagsToDelete.add(new Tag("Thisshouldnotbeinthefinallist"));
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(INDEX_FIRST_PERSON, tagsToDelete);

        for (Tag tagToDelete: tagsToDelete) {
            personToEdit = DeleteTagCommand.deleteTag(personToEdit, tagToDelete);
        }

        expectedModel.setPerson(model.getFilteredPersonList().get(0), personToEdit);

        String expectedMessage = String.format(DeleteTagCommand.MESSAGE_DELETED_TAG_SUCCESS, initialTagString) + " "
                + String.format(DeleteTagCommand.MESSAGE_EDIT_PERSON_SUCCESS, personToEdit);

        assertCommandSuccess(deleteTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        final Set<Tag> defaultTags = new HashSet<>(Arrays.asList(new Tag("Default")));
        final Set<Tag> differentTags = new HashSet<>(Arrays.asList(new Tag("Different")));
        final DeleteTagCommand standardCommand = new DeleteTagCommand(INDEX_FIRST_PERSON, defaultTags);

        // same values -> returns true
        DeleteTagCommand commandWithSameValues = new DeleteTagCommand(INDEX_FIRST_PERSON, defaultTags);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new DeleteTagCommand(INDEX_SECOND_PERSON, defaultTags)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new DeleteTagCommand(INDEX_FIRST_PERSON, differentTags)));
    }

}
