package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FRIEND_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalFriends.getTypicalFriendsList;
import static seedu.address.testutil.TypicalGames.getTypicalGamesList;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditFriendDescriptor;
import seedu.address.model.FriendsList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.friend.Friend;
import seedu.address.testutil.EditFriendDescriptorBuilder;
import seedu.address.testutil.FriendBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private final Model model = new ModelManager(getTypicalFriendsList(), getTypicalGamesList(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Friend editedFriend = new FriendBuilder().build();
        EditCommand.EditFriendDescriptor descriptor = new EditFriendDescriptorBuilder(editedFriend).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FRIEND_SUCCESS, editedFriend);

        Model expectedModel = new ModelManager(new FriendsList(model.getFriendsList()), getTypicalGamesList(),
                new UserPrefs());
        expectedModel.setFriend(model.getFilteredFriendsList().get(0), editedFriend);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredFriendsList().size());
        Friend lastFriend = model.getFilteredFriendsList().get(indexLastPerson.getZeroBased());

        FriendBuilder personInList = new FriendBuilder(lastFriend);
        Friend editedFriend = personInList.withFriendName(VALID_NAME_BOB).withFriendId(VALID_FRIEND_ID_BOB)
                .withGameFriendLinks().build();

        EditCommand.EditFriendDescriptor descriptor = new EditFriendDescriptorBuilder().withFriendName(VALID_NAME_BOB)
                .withFriendId(VALID_FRIEND_ID_BOB).withGames().build();
        EditCommand editCommand = new EditCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FRIEND_SUCCESS, editedFriend);

        Model expectedModel = new ModelManager(new FriendsList(model.getFriendsList()), getTypicalGamesList(),
                new UserPrefs());
        expectedModel.setFriend(lastFriend, editedFriend);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, new EditCommand.EditFriendDescriptor());
        Friend editedFriend = model.getFilteredFriendsList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FRIEND_SUCCESS, editedFriend);

        Model expectedModel = new ModelManager(new FriendsList(model.getFriendsList()), getTypicalGamesList(),
                new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Friend friendInFilteredList = model.getFilteredFriendsList().get(INDEX_FIRST_PERSON.getZeroBased());
        Friend editedFriend = new FriendBuilder(friendInFilteredList).withFriendName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditFriendDescriptorBuilder().withFriendName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FRIEND_SUCCESS, editedFriend);

        Model expectedModel = new ModelManager(new FriendsList(model.getFriendsList()), getTypicalGamesList(),
                new UserPrefs());
        expectedModel.setFriend(model.getFilteredFriendsList().get(0), editedFriend);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Friend firstFriend = model.getFilteredFriendsList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditFriendDescriptor descriptor = new EditFriendDescriptorBuilder(firstFriend).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_FRIEND);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // edit person in filtered list into a duplicate in address book
        Friend friendInList = model.getFriendsList().getFriendsList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditFriendDescriptorBuilder(friendInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_FRIEND);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFriendsList().size() + 1);
        EditCommand.EditFriendDescriptor descriptor = new EditFriendDescriptorBuilder()
                .withFriendName(VALID_NAME_BOB).build();
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
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFriendsList().getFriendsList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditFriendDescriptorBuilder().withFriendName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_PERSON, DESC_AMY);

        // same values -> returns true
        EditFriendDescriptor copyDescriptor = new EditFriendDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_PERSON, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_PERSON, DESC_BOB)));
    }

}
