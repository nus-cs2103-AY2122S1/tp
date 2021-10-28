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
 * Contains integration tests (interaction with the Model) and unit tests for AddTagCommand.
 */
public class AddTagCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Set<Tag> tagsToAdd = new HashSet<>(Arrays.asList(new Tag("NewTag")));
    private Set<Tag> multiTagsToAdd = new HashSet<>(Arrays.asList(new Tag("NewTag"),
            new Tag("NewTag2")));

    @Test
    public void execute_indexAndTagSpecifiedUnfilteredList_success() {
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        AddTagCommand addTagCommand = new AddTagCommand(INDEX_FIRST_PERSON, tagsToAdd);

        for (Tag tagToAdd : tagsToAdd) {
            personToEdit = AddTagCommand.addTag(personToEdit, tagToAdd);
        }

        String expectedMessage =
                String.format(AddTagCommand.MESSAGE_ADD_TAG_SUCCESS, AddTagCommand.tagString(tagsToAdd))
                        + " " + String.format(AddTagCommand.MESSAGE_EDIT_PERSON_SUCCESS, personToEdit);


        expectedModel.setPerson(model.getFilteredPersonList().get(0), personToEdit);

        assertCommandSuccess(addTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_tagOnlySpecifiedUnfilteredList_success() {
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        AddTagCommand addTagCommand = new AddTagCommand(null, tagsToAdd);

        for (int i = 0; i < model.getFilteredPersonList().size(); i++) {
            Person personToEdit = model.getFilteredPersonList().get(i);
            for (Tag tagToAdd : tagsToAdd) {
                personToEdit = AddTagCommand.addTag(personToEdit, tagToAdd);
            }
            expectedModel.setPerson(model.getFilteredPersonList().get(i), personToEdit);
        }

        String expectedMessage =
                String.format(AddTagCommand.MESSAGE_ADD_TAG_SUCCESS, AddTagCommand.tagString(tagsToAdd));

        assertCommandSuccess(addTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_indexAndTagSpecifiedFilteredList_success() {
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);

        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        AddTagCommand addTagCommand = new AddTagCommand(INDEX_FIRST_PERSON, tagsToAdd);

        for (Tag tagToAdd: tagsToAdd) {
            personToEdit = AddTagCommand.addTag(personToEdit, tagToAdd);
        }

        String expectedMessage =
                String.format(AddTagCommand.MESSAGE_ADD_TAG_SUCCESS, AddTagCommand.tagString(tagsToAdd))
                        + " " + String.format(AddTagCommand.MESSAGE_EDIT_PERSON_SUCCESS, personToEdit);


        expectedModel.setPerson(model.getFilteredPersonList().get(0), personToEdit);

        assertCommandSuccess(addTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_tagOnlySpecifiedFilteredList_success() {
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);

        AddTagCommand addTagCommand = new AddTagCommand(null, tagsToAdd);

        for (int i = 0; i < model.getFilteredPersonList().size(); i++) {
            Person personToEdit = model.getFilteredPersonList().get(i);
            for (Tag tagToAdd: tagsToAdd) {
                personToEdit = AddTagCommand.addTag(personToEdit, tagToAdd);
            }
            expectedModel.setPerson(model.getFilteredPersonList().get(i), personToEdit);
        }

        String expectedMessage =
                String.format(AddTagCommand.MESSAGE_ADD_TAG_SUCCESS, AddTagCommand.tagString(tagsToAdd));

        assertCommandSuccess(addTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexWithTagUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);

        Set<Tag> placeholderTag = new HashSet<>(Arrays.asList(
                new Tag("Thisshouldnotbeinthefinallist")));

        AddTagCommand addTagCommand = new AddTagCommand(outOfBoundIndex, placeholderTag);
        assertCommandFailure(addTagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
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

        AddTagCommand addTagCommand = new AddTagCommand(outOfBoundIndex, placeholderTag);
        assertCommandFailure(addTagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_multiTagsWithoutIndexFilteredList_failure() {
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);

        AddTagCommand addTagCommand = new AddTagCommand(null, multiTagsToAdd);

        for (int i = 0; i < model.getFilteredPersonList().size(); i++) {
            Person personToEdit = model.getFilteredPersonList().get(i);
            for (Tag tagToAdd: multiTagsToAdd) {
                personToEdit = AddTagCommand.addTag(personToEdit, tagToAdd);
            }
            expectedModel.setPerson(model.getFilteredPersonList().get(i), personToEdit);
        }

        String expectedMessage =
                String.format(AddTagCommand.MESSAGE_ADD_TAG_SUCCESS, AddTagCommand.tagString(multiTagsToAdd));

        assertCommandSuccess(addTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_multiTagsWithIndexSpecifiedFilteredList_failure() {
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);

        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        AddTagCommand addTagCommand = new AddTagCommand(INDEX_FIRST_PERSON, multiTagsToAdd);

        for (Tag tagToAdd: multiTagsToAdd) {
            personToEdit = AddTagCommand.addTag(personToEdit, tagToAdd);
        }

        String expectedMessage =
                String.format(AddTagCommand.MESSAGE_ADD_TAG_SUCCESS, AddTagCommand.tagString(multiTagsToAdd))
                        + " " + String.format(AddTagCommand.MESSAGE_EDIT_PERSON_SUCCESS, personToEdit);


        expectedModel.setPerson(model.getFilteredPersonList().get(0), personToEdit);

        assertCommandSuccess(addTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        final Set<Tag> defaultTags = new HashSet<>(Arrays.asList(new Tag("Default")));
        final Set<Tag> differentTags = new HashSet<>(Arrays.asList(new Tag("Different")));
        final AddTagCommand standardCommand = new AddTagCommand(INDEX_FIRST_PERSON, defaultTags);

        // same values -> returns true
        AddTagCommand commandWithSameValues = new AddTagCommand(INDEX_FIRST_PERSON, defaultTags);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AddTagCommand(INDEX_SECOND_PERSON, defaultTags)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new AddTagCommand(INDEX_FIRST_PERSON, differentTags)));
    }

}
