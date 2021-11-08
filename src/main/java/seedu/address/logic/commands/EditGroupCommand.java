package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REPO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_GROUPS;

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
import seedu.address.model.commons.RepoName;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;
import seedu.address.model.group.LinkYear;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing group in the address book.
 */
public class EditGroupCommand extends Command {

    public static final String COMMAND_WORD = "editGroup";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the group identified "
            + "by the index number used in the displayed group list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_GROUP + "NAME] "
            + "[" + PREFIX_YEAR + "YEAR] "
            + "[" + PREFIX_REPO + "REPO] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_GROUP + "w12-3 "
            + PREFIX_REPO + "newreponame "
            + PREFIX_YEAR + "AY2223S2 "
            + PREFIX_TAG + "tApp2 ";

    public static final String MESSAGE_EDIT_GROUP_SUCCESS = "Edited Group: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_GROUP = "This group already exists in the tApp.";

    private final Index index;
    private final EditGroupDescriptor editGroupDescriptor;

    /**
     * @param index of the group in the filtered group list to edit
     * @param editGroupDescriptor details to edit the group with
     */
    public EditGroupCommand(Index index, EditGroupDescriptor editGroupDescriptor) {
        requireNonNull(index);
        requireNonNull(editGroupDescriptor);

        this.index = index;
        this.editGroupDescriptor = new EditGroupDescriptor(editGroupDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Group> lastShownList = model.getFilteredGroupList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
        }

        Group groupToEdit = lastShownList.get(index.getZeroBased());
        Group editedGroup = createEditedGroup(groupToEdit, editGroupDescriptor);

        if (!groupToEdit.isSameGroup(editedGroup) && model.hasGroup(editedGroup)) {
            throw new CommandException(MESSAGE_DUPLICATE_GROUP);
        }

        model.setGroup(groupToEdit, editedGroup);
        model.updateFilteredGroupList(PREDICATE_SHOW_ALL_GROUPS);

        return new CommandResult(String.format(MESSAGE_EDIT_GROUP_SUCCESS, editedGroup));
    }

    /**
     * Creates and returns a {@code Group} with the details of {@code groupToEdit}
     * edited with {@code editGroupDescriptor}.
     */
    private static Group createEditedGroup(Group groupToEdit, EditGroupDescriptor editGroupDescriptor) {
        assert groupToEdit != null;

        GroupName updatedName = editGroupDescriptor.getName().orElse(groupToEdit.getName());
        LinkYear updatedYear = editGroupDescriptor.getYear().orElse(groupToEdit.getYear());
        RepoName updatedRepoName = editGroupDescriptor.getRepoName().orElse(groupToEdit.getRepoName());
        Set<Tag> updatedTags = editGroupDescriptor.getTags().orElse(groupToEdit.getTags());

        return new Group(updatedName, groupToEdit.getMembers(), updatedYear, updatedRepoName, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditGroupCommand)) {
            return false;
        }

        // state check
        EditGroupCommand e = (EditGroupCommand) other;
        return index.equals(e.index)
                && editGroupDescriptor.equals(e.editGroupDescriptor);
    }

    /**
     * Stores the details to edit the group with. Each non-empty field value will replace the
     * corresponding field value of the group
     * .
     */
    public static class EditGroupDescriptor {
        private GroupName name;
        private LinkYear year;
        private RepoName repoName;
        private Set<Tag> tags;

        public EditGroupDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditGroupDescriptor(EditGroupDescriptor toCopy) {
            setName(toCopy.name);
            setYear(toCopy.year);
            setRepoName(toCopy.repoName);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, year, repoName, tags);
        }

        public void setName(GroupName name) {
            this.name = name;
        }

        public Optional<GroupName> getName() {
            return Optional.ofNullable(name);
        }

        public void setYear(LinkYear year) {
            this.year = year;
        }

        public Optional<LinkYear> getYear() {
            return Optional.ofNullable(year);
        }

        public void setRepoName(RepoName repoName) {
            this.repoName = repoName;
        }

        public Optional<RepoName> getRepoName() {
            return Optional.ofNullable(repoName);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditGroupDescriptor)) {
                return false;
            }

            // state check
            EditGroupDescriptor e = (EditGroupDescriptor) other;

            return getName().equals(e.getName())
                    && getYear().equals(e.getYear())
                    && getRepoName().equals(e.getRepoName())
                    && getTags().equals(e.getTags());
        }
    }
}
