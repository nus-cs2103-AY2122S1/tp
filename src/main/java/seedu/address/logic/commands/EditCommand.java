package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_FRIEND_ID;
import static seedu.address.logic.parser.CliSyntax.FLAG_FRIEND_NAME;
import static seedu.address.logic.parser.CliSyntax.FLAG_GAME_OLD;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_FRIENDS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.FriendId;
import seedu.address.model.friend.FriendName;
import seedu.address.model.friend.gamefriendlink.GameFriendLink;

/**
 * Edits the details of an existing friend in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the friend identified "
            + "by the index number used in the displayed friends list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + FLAG_FRIEND_ID + "FRIEND_ID] "
            + "[" + FLAG_FRIEND_NAME + "NAME] "
            + "[" + FLAG_GAME_OLD + "GAME]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + FLAG_GAME_OLD + "Apex Legends "
            + FLAG_GAME_OLD + "CSGO "
            + FLAG_FRIEND_NAME + "John Lim";

    public static final String MESSAGE_EDIT_FRIEND_SUCCESS = "Edited Friend: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_FRIEND = "A friend with this friendId already exists in the"
            + "gitGud friend's list.";

    private final Index index;
    private final EditFriendDescriptor editFriendDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editFriendDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditFriendDescriptor editFriendDescriptor) {
        requireNonNull(index);
        requireNonNull(editFriendDescriptor);

        this.index = index;
        this.editFriendDescriptor = new EditFriendDescriptor(editFriendDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Friend> lastShownList = model.getFilteredFriendsList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Friend friendToEdit = lastShownList.get(index.getZeroBased());
        Friend editedFriend = createEditedPerson(friendToEdit, editFriendDescriptor);

        if (!friendToEdit.equals(editedFriend) && model.hasFriend(editedFriend)) {
            throw new CommandException(MESSAGE_DUPLICATE_FRIEND);
        }

        model.setFriend(friendToEdit, editedFriend);
        model.updateFilteredFriendsList(PREDICATE_SHOW_ALL_FRIENDS);
        return new CommandResult(String.format(MESSAGE_EDIT_FRIEND_SUCCESS, editedFriend));
    }

    /**
     * Creates and returns a {@code Friend} with the details of {@code friendToEdit}
     * edited with {@code EditFriendDescriptor}.
     */
    private static Friend createEditedPerson(Friend friendToEdit, EditFriendDescriptor editFriendDescriptor) {
        assert friendToEdit != null;

        FriendId updatedFriendId = editFriendDescriptor.getFriendId().orElse(friendToEdit.getFriendId());
        FriendName updatedFriendName = editFriendDescriptor.getFriendName().orElse(friendToEdit.getName());
        Set<GameFriendLink> updatedGames = editFriendDescriptor.getGames().orElse(friendToEdit.getGames());

        return new Friend(updatedFriendId, updatedFriendName, updatedGames);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editFriendDescriptor.equals(e.editFriendDescriptor);
    }

    /**
     * Stores the details to edit the friend with. Each non-empty field value will replace the
     * corresponding field value of the friend.
     */
    public static class EditFriendDescriptor {
        private FriendName friendName;
        private FriendId friendId;
        private Set<GameFriendLink> games;

        public EditFriendDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditFriendDescriptor(EditFriendDescriptor toCopy) {
            setFriendId(toCopy.friendId);
            setFriendName(toCopy.friendName);
            setGames(toCopy.games);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(friendId, friendName, games);
        }

        public void setFriendId(FriendId friendId) {
            this.friendId = friendId;
        }

        public Optional<FriendId> getFriendId() {
            return Optional.ofNullable(friendId);
        }

        public void setFriendName(FriendName friendName) {
            this.friendName = friendName;
        }

        public Optional<FriendName> getFriendName() {
            return Optional.ofNullable(friendName);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setGames(Set<GameFriendLink> gameSet) {
            this.games = (gameSet != null) ? new HashSet<>(gameSet) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<GameFriendLink>> getGames() {
            return (games != null) ? Optional.of(Collections.unmodifiableSet(games)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditFriendDescriptor)) {
                return false;
            }

            // state check
            EditFriendDescriptor e = (EditFriendDescriptor) other;

            return getFriendId().equals(e.getFriendId())
                    && getFriendName().equals(e.getFriendName())
                    && getGames().equals(e.getGames());
        }
    }
}
