package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.EditUtil.EditPersonDescriptor;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_WIFE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getNoTagTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTaggedTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for TagCommand.
 */
class TagCommandTest {

    private EditPersonDescriptor descriptorAddFriend = new EditPersonDescriptorBuilder()
            .withTags(VALID_TAG_FRIEND).build();
    private EditPersonDescriptor descriptorAddHusband = new EditPersonDescriptorBuilder()
            .withTags(VALID_TAG_HUSBAND).build();
    private EditPersonDescriptor descriptorAddBoth = new EditPersonDescriptorBuilder()
            .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build();

    private Model generateNoTagModel() {
        return new ModelManager(getNoTagTypicalAddressBook(), new UserPrefs());
    }
    private Model generateDefaultTagModel() {
        return new ModelManager(getTaggedTypicalAddressBook(VALID_TAG_WIFE), new UserPrefs());
    }

    @Test
    public void execute_singleTagNoExistingUnfilteredList_success() {
        Model model = generateNoTagModel();
        Person defaultFirst = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(defaultFirst).withTags(VALID_TAG_FRIEND).build();

        TagCommand tagCommand = new TagCommand(INDEX_FIRST_PERSON, descriptorAddFriend);

        String expectedMessage = String.format(TagCommand.MESSAGE_TAG_ADD_SUCCESS,
                editedPerson.getName(), editedPerson.getTags());

        Model expectedModel = generateNoTagModel();
        expectedModel.setPerson(defaultFirst, editedPerson);

        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_singleTagPreExistingTagUnfilteredList_success() {
        Model model = generateDefaultTagModel();
        Person defaultFirst = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(defaultFirst).withTags(VALID_TAG_WIFE, VALID_TAG_FRIEND).build();

        TagCommand tagCommand = new TagCommand(INDEX_FIRST_PERSON, descriptorAddFriend);

        String expectedMessage = String.format(TagCommand.MESSAGE_TAG_ADD_SUCCESS,
                editedPerson.getName(), editedPerson.getTags());

        Model expectedModel = generateDefaultTagModel();
        expectedModel.setPerson(defaultFirst, editedPerson);

        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_multipleTagNoExistingTagUnfilteredList_success() {
        Model model = generateNoTagModel();
        Person defaultFirst = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(defaultFirst).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build();


        TagCommand tagCommand = new TagCommand(INDEX_FIRST_PERSON, descriptorAddBoth);

        String expectedMessage = String.format(TagCommand.MESSAGE_TAG_ADD_SUCCESS,
                editedPerson.getName(), editedPerson.getTags());

        Model expectedModel = generateNoTagModel();
        expectedModel.setPerson(defaultFirst, editedPerson);

        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_multipleTagPreExistingTagUnfilteredList_success() {
        Model model = generateDefaultTagModel();
        Person defaultFirst = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(defaultFirst)
                .withTags(VALID_TAG_WIFE, VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build();

        TagCommand tagCommand = new TagCommand(INDEX_FIRST_PERSON, descriptorAddBoth);

        String expectedMessage = String.format(TagCommand.MESSAGE_TAG_ADD_SUCCESS,
                editedPerson.getName(), editedPerson.getTags());

        Model expectedModel = generateDefaultTagModel();
        expectedModel.setPerson(defaultFirst, editedPerson);

        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        Model model = generateNoTagModel();
        Person defaultFirst = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList).withTags(VALID_TAG_FRIEND).build();

        TagCommand tagCommand = new TagCommand(INDEX_FIRST_PERSON,
                new EditPersonDescriptorBuilder().withTags(VALID_TAG_FRIEND).build());

        String expectedMessage = String.format(TagCommand.MESSAGE_TAG_ADD_SUCCESS,
                editedPerson.getName(), editedPerson.getTags());

        Model expectedModel = generateNoTagModel();
        expectedModel.setPerson(defaultFirst, editedPerson);

        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Model model = generateNoTagModel();
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        TagCommand tagCommand = new TagCommand(outOfBoundIndex, descriptorAddFriend);

        assertCommandFailure(tagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        Model model = generateNoTagModel();
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        TagCommand tagCommand = new TagCommand(outOfBoundIndex, descriptorAddFriend);

        assertCommandFailure(tagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final TagCommand standardCommand = new TagCommand(INDEX_FIRST_PERSON, descriptorAddFriend);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(descriptorAddFriend);
        TagCommand commandWithSameValues = new TagCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new TagCommand(INDEX_SECOND_PERSON, descriptorAddFriend)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_PERSON, descriptorAddHusband)));
    }
}
