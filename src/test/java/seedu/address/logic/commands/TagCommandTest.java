package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class TagCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), null);

    //User enters an index greater than size of list
    @Test
    public void execute_invalidIndexProvided_failure() {
        TagCommand tagCommand = new TagCommand(Index.fromOneBased(8),
                new ArrayList<>(Arrays.asList(new Tag("teammates"))),
                new ArrayList<>()
                );

        assertCommandFailure(tagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    //User attempts to add a tag that already exists for the specified contact
    @Test
    public void execute_duplicateTagToBeAdded_failure() {
        //Only adding duplicate tag
        TagCommand tagCommand1 = new TagCommand(Index.fromOneBased(2),
                new ArrayList<>(Arrays.asList(new Tag("friends"))),
                new ArrayList<>()
        );
        assertCommandFailure(tagCommand1, model, TagCommand.MESSAGE_TAG_TO_ADD_ALREADY_EXISTS);

        //Adding duplicate tag and removing valid tag
        TagCommand tagCommand2 = new TagCommand(Index.fromOneBased(2),
                new ArrayList<>(Arrays.asList(new Tag("friends"))),
                new ArrayList<>(Arrays.asList(new Tag("owesmoney")))
        );
        assertCommandFailure(tagCommand2, model, TagCommand.MESSAGE_TAG_TO_ADD_ALREADY_EXISTS);
    }

    //User attempts to remove a tag that doesn't exist for the specified contact
    @Test
    public void execute_nonExistentTagToBeRemoved_failure() {
        //Only removing a non-existent tag
        TagCommand tagCommand1 = new TagCommand(Index.fromOneBased(2),
                new ArrayList<>(),
                new ArrayList<>(Arrays.asList(new Tag("colleague")))
        );
        assertCommandFailure(tagCommand1, model, TagCommand.MESSAGE_TAG_TO_REMOVE_DOES_NOT_EXIST);

        //Adding valid tag and removing a non-existent tag
        TagCommand tagCommand2 = new TagCommand(Index.fromOneBased(2),
                new ArrayList<>(Arrays.asList(new Tag("teammate"))),
                new ArrayList<>(Arrays.asList(new Tag("colleague")))
        );
        assertCommandFailure(tagCommand2, model, TagCommand.MESSAGE_TAG_TO_REMOVE_DOES_NOT_EXIST);
    }

    //User adds a single new tag to a specified contact.
    @Test
    public void uniqueTagToBeAdded_success() {
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(), null);
        Person fourthPerson = model.getFilteredPersonList().get(Index.fromOneBased(4).getZeroBased());
        PersonBuilder personInList = new PersonBuilder(fourthPerson);
        Person editedPerson = personInList.withTags("friends", "teammate").build();
        expectedModel.setPerson(fourthPerson, editedPerson);

        TagCommand tagCommand1 = new TagCommand(Index.fromOneBased(4),
                new ArrayList<>(Arrays.asList(new Tag("teammate"))),
                new ArrayList<>()
        );
        assertCommandSuccess(tagCommand1, model, String.format(TagCommand.MESSAGE_ADD_TAG_SUCCESS, editedPerson),
                expectedModel);
    }

    //User adds multiple new tags to a specified contact.
    @Test
    public void uniqueTagsToBeAdded_success() {
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(), null);
        Person fourthPerson = model.getFilteredPersonList().get(Index.fromOneBased(4).getZeroBased());
        PersonBuilder personInList = new PersonBuilder(fourthPerson);
        Person editedPerson = personInList.withTags("friends", "teammate", "2103T").build();
        expectedModel.setPerson(fourthPerson, editedPerson);

        TagCommand tagCommand1 = new TagCommand(Index.fromOneBased(4),
                new ArrayList<>(Arrays.asList(new Tag("teammate"), new Tag("2103T"))),
                new ArrayList<>()
        );
        assertCommandSuccess(tagCommand1, model, String.format(TagCommand.MESSAGE_ADD_TAG_SUCCESS, editedPerson),
                expectedModel);
    }

    //User removes a single valid tag from a specified contact.
    @Test
    public void validTagToBeRemoved_success() {
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(), null);
        Person fourthPerson = model.getFilteredPersonList().get(Index.fromOneBased(2).getZeroBased());
        PersonBuilder personInList = new PersonBuilder(fourthPerson);
        Person editedPerson = personInList.withTags("owesmoney", "cca").build();
        expectedModel.setPerson(fourthPerson, editedPerson);

        TagCommand tagCommand1 = new TagCommand(Index.fromOneBased(2),
                new ArrayList<>(),
                new ArrayList<>(Arrays.asList(new Tag("friends")))
        );
        assertCommandSuccess(tagCommand1, model, String.format(TagCommand.MESSAGE_REMOVE_TAG_SUCCESS, editedPerson),
                expectedModel);
    }

    //User removes multiple valid tags from a specified contact.
    @Test
    public void validTagsToBeRemoved_success() {
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(), null);
        Person fourthPerson = model.getFilteredPersonList().get(Index.fromOneBased(2).getZeroBased());
        PersonBuilder personInList = new PersonBuilder(fourthPerson);
        Person editedPerson = personInList.withTags("owesmoney").build();
        expectedModel.setPerson(fourthPerson, editedPerson);

        TagCommand tagCommand1 = new TagCommand(Index.fromOneBased(2),
                new ArrayList<>(),
                new ArrayList<>(Arrays.asList(new Tag("friends"), new Tag("cca")))
        );
        assertCommandSuccess(tagCommand1, model, String.format(TagCommand.MESSAGE_REMOVE_TAG_SUCCESS, editedPerson),
                expectedModel);
    }

    //User specifies tag(s) to be added and removed from a specified contact
    @Test
    public void validTagsToBeAddedAndRemoved_success() {
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(), null);
        Person fourthPerson = model.getFilteredPersonList().get(Index.fromOneBased(5).getZeroBased());
        PersonBuilder personInList = new PersonBuilder(fourthPerson);
        Person editedPerson = personInList.withTags("friends", "teammate").build();
        expectedModel.setPerson(fourthPerson, editedPerson);

        TagCommand tagCommand1 = new TagCommand(Index.fromOneBased(5),
                new ArrayList<>(Arrays.asList(new Tag("friends"), new Tag("teammate"))),
                new ArrayList<>(Arrays.asList(new Tag("family")))
        );
        assertCommandSuccess(tagCommand1, model, String.format(TagCommand.MESSAGE_TAGGED_PERSON_SUCCESS, editedPerson),
                expectedModel);
    }


    @Test
    public void equals() {
        final TagCommand standardCommand = new TagCommand(INDEX_FIRST_PERSON,
                new ArrayList<>(Arrays.asList(new Tag("friends"), new Tag("teammate"))),
                new ArrayList<>(Arrays.asList(new Tag("family")))
        );

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // same values and order of tags to be added and removed-> returns true
        final TagCommand commandWithSameTagsAndOrder = new TagCommand(INDEX_FIRST_PERSON,
                new ArrayList<>(Arrays.asList(new Tag("friends"), new Tag("teammate"))),
                new ArrayList<>(Arrays.asList(new Tag("family")))
        );
        assertTrue(standardCommand.equals(commandWithSameTagsAndOrder));

        // same values but different order of tags to be added and removed-> returns true
        final TagCommand commandWithSameTagsDifferentOrder = new TagCommand(INDEX_FIRST_PERSON,
                new ArrayList<>(Arrays.asList(new Tag("teammate"), new Tag("friends"))),
                new ArrayList<>(Arrays.asList(new Tag("family")))
        );
        assertTrue(standardCommand.equals(commandWithSameTagsDifferentOrder));

        // different tags to be added but same tags to be removed-> returns false
        final TagCommand commandWithDifferentAddTagsSameRemoveTag = new TagCommand(INDEX_FIRST_PERSON,
                new ArrayList<>(Arrays.asList(new Tag("colleague"), new Tag("friends"))),
                new ArrayList<>(Arrays.asList(new Tag("family")))
        );
        assertFalse(standardCommand.equals(commandWithDifferentAddTagsSameRemoveTag));

        // different tags to be added amd removed-> returns false
        final TagCommand commandWithDifferentAddAndRemoveTags = new TagCommand(INDEX_FIRST_PERSON,
                new ArrayList<>(Arrays.asList(new Tag("colleague"), new Tag("friends"))),
                new ArrayList<>(Arrays.asList(new Tag("cca")))
        );
        assertFalse(standardCommand.equals(commandWithDifferentAddAndRemoveTags));

        //same add and remove tags, but different index -> returns false
        final TagCommand commandWithDifferentIndex = new TagCommand(INDEX_SECOND_PERSON,
                new ArrayList<>(Arrays.asList(new Tag("friends"), new Tag("teammate"))),
                new ArrayList<>(Arrays.asList(new Tag("family")))
        );
        assertFalse(standardCommand.equals(commandWithDifferentIndex));
    }
}
