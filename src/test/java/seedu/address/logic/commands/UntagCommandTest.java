package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_STUDENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TEACHING_ASSISTANT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_WIFE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTaggedTypicalAddressBook;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

class UntagCommandTest {

    private Model generateFriendTaggedModel() {
        return new ModelManager(getTaggedTypicalAddressBook(VALID_TAG_FRIEND), new UserPrefs());
    }

    private Model generateFriendAndHusbandTaggedModel() {
        return new ModelManager(getTaggedTypicalAddressBook(VALID_TAG_FRIEND, VALID_TAG_HUSBAND), new UserPrefs());
    }

    private Model generateFriendHusbandTeachingAssistantStudentTaggedModel() {
        return new ModelManager(getTaggedTypicalAddressBook(VALID_TAG_FRIEND, VALID_TAG_HUSBAND,
                VALID_TAG_TEACHING_ASSISTANT, VALID_TAG_STUDENT), new UserPrefs());
    }

    @Test
    void execute_singleTagNoRemainingTagsUnfilteredList_success() {
        Model model = generateFriendTaggedModel();
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(firstPerson);
        Person editedPerson = personInList.withTags().build();

        UntagCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withTags(VALID_TAG_FRIEND).build();
        UntagCommand untagCommand = new UntagCommand(INDEX_FIRST_PERSON, descriptor);

        Set<Tag> removedTags = descriptor.getTags().orElse(new HashSet<Tag>());
        String expectedMessage = String.format(UntagCommand.MESSAGE_REMOVE_PERSON_SUCCESS, removedTags);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(untagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_singleTagWithRemainingTagsUnfilteredList_success() {
        Model model = generateFriendAndHusbandTaggedModel();
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(firstPerson);
        Person editedPerson = personInList.withTags(VALID_TAG_HUSBAND).build();

        UntagCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withTags(VALID_TAG_FRIEND).build();
        UntagCommand untagCommand = new UntagCommand(INDEX_FIRST_PERSON, descriptor);

        Set<Tag> removedTags = descriptor.getTags().orElse(new HashSet<Tag>());
        String expectedMessage = String.format(UntagCommand.MESSAGE_REMOVE_PERSON_SUCCESS, removedTags);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(untagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_multipleTagsNoRemainingTagsUnfilteredList_success() {
        Model model = generateFriendAndHusbandTaggedModel();
        Person secondPerson = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(secondPerson);
        Person editedPerson = personInList.withTags().build();

        UntagCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        UntagCommand untagCommand = new UntagCommand(INDEX_SECOND_PERSON, descriptor);

        Set<Tag> removedTags = descriptor.getTags().orElse(new HashSet<Tag>());
        String expectedMessage = String.format(UntagCommand.MESSAGE_REMOVE_PERSON_SUCCESS, removedTags);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(secondPerson, editedPerson);

        assertCommandSuccess(untagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_multipleTagsWithRemainingTagsUnfilteredList_success() {
        Model model = generateFriendHusbandTeachingAssistantStudentTaggedModel();
        Person thirdPerson = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(thirdPerson);
        Person editedPerson = personInList.withTags(VALID_TAG_HUSBAND).build();

        UntagCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withTags(VALID_TAG_FRIEND, VALID_TAG_STUDENT, VALID_TAG_TEACHING_ASSISTANT).build();
        UntagCommand untagCommand = new UntagCommand(INDEX_THIRD_PERSON, descriptor);

        Set<Tag> removedTags = descriptor.getTags().orElse(new HashSet<Tag>());
        String expectedMessage = String.format(UntagCommand.MESSAGE_REMOVE_PERSON_SUCCESS, removedTags);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(thirdPerson, editedPerson);

        assertCommandSuccess(untagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        Model model = generateFriendAndHusbandTaggedModel();
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList).withTags(VALID_TAG_FRIEND).build();

        UntagCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withTags(VALID_TAG_HUSBAND).build();
        UntagCommand untagCommand = new UntagCommand(INDEX_FIRST_PERSON, descriptor);

        Set<Tag> removedTags = descriptor.getTags().orElse(new HashSet<Tag>());
        String expectedMessage = String.format(UntagCommand.MESSAGE_REMOVE_PERSON_SUCCESS, removedTags);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(untagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_personNotTagged_failure() {
        Model model = generateFriendTaggedModel();
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UntagCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withTags(VALID_TAG_STUDENT).build();
        UntagCommand editCommand = new UntagCommand(INDEX_FIRST_PERSON, descriptor);

        String tagsNotFound = UntagCommand.getNotFoundTags(firstPerson.getTags(),
                descriptor.getTags().orElse(new HashSet<Tag>()));
        String message = String.format(UntagCommand.MESSAGE_TAG_NOT_IN_PERSON,
                tagsNotFound);
        assertCommandFailure(editCommand, model, message);
    }

    @Test
    void testEquals() {
        UntagCommand.EditPersonDescriptor removeFriendDescriptor = new EditPersonDescriptorBuilder()
                .withTags(VALID_TAG_FRIEND).build();
        UntagCommand.EditPersonDescriptor removeWifeDescriptor = new EditPersonDescriptorBuilder()
                .withTags(VALID_TAG_WIFE).build();

        final UntagCommand standardCommand = new UntagCommand(INDEX_THIRD_PERSON, removeFriendDescriptor);

        // same values -> returns true
        UntagCommand.EditPersonDescriptor copyDescriptor = new UntagCommand.EditPersonDescriptor(
                removeFriendDescriptor);
        UntagCommand commandWithSameValues = new UntagCommand(INDEX_THIRD_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new UntagCommand(INDEX_SECOND_PERSON, removeFriendDescriptor)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new UntagCommand(INDEX_THIRD_PERSON, removeWifeDescriptor)));
    }
}
