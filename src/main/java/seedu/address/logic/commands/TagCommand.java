package seedu.address.logic.commands;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

public class TagCommand extends Command {
    public static final String COMMAND_WORD = "tag";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": adds/removes the specified tag(s) for the required contact.\n"
            + "Parameters: INDEX (must be a positive integer) a/TAG [r/TAG] [MORE_TAGS]..\n"
            + "Example: " + COMMAND_WORD + " 1 a/friends ";

    public static final String MESSAGE_TAGGED_PERSON_SUCCESS = "Successfully added/removed tag(s) to %1$s!";
    public static final String MESSAGE_ADD_TAG_SUCCESS = "Successfully added tag(s) to %1$s!";
    public static final String MESSAGE_REMOVE_TAG_SUCCESS = "Successfully removed tag(s) to %1$s!";
    public static final String MESSAGE_MISSING_ADD_AND_REMOVE_TAG_ARGS = "Tags to be added and removed are missing!\n"
            + MESSAGE_USAGE;
    public static final String MESSAGE_MISSING_ADD_TAG_ARGS = "Tags to be added are missing!\n" + MESSAGE_USAGE;
    public static final String MESSAGE_MISSING_REMOVE_TAG_ARGS = "Tags to be removed are missing!\n" + MESSAGE_USAGE;
    public static final String MESSAGE_TAG_TO_REMOVE_DOES_NOT_EXIST = "Tag(s) to be removed doesn't exist!";
    public static final String MESSAGE_TAG_TO_ADD_ALREADY_EXISTS = "Tag(s) to be added already exists!";

    private final Index targetIndex;
    private final ArrayList<Tag> toAdd;
    private final ArrayList<Tag> toRemove;

    /**
     * @param index of the person in the filtered person list to edit
     * @param toAdd tags to add to the person's existing tags
     * @param toRemove tags to remove from the person's existing tags
     */
    public TagCommand(Index index, ArrayList<Tag> toAdd, ArrayList<Tag> toRemove) {
        this.targetIndex = index;
        this.toAdd = toAdd;
        this.toRemove = toRemove;
    }

    private ArrayList<Tag> getInvalidRemoveTags(Set<Tag> newTags) {
        ArrayList<Tag> invalidRemoveTags = new ArrayList<>();
        for (Tag tag : toRemove) {
            if (!newTags.contains(tag)) {
                invalidRemoveTags.add(tag);
            }
        }
        return invalidRemoveTags;
    }

    private Set<Tag> getTagsAfterRemove(Set<Tag> newTags) throws CommandException {
        ArrayList<Tag> invalidRemoveTags = getInvalidRemoveTags(newTags);
        if (invalidRemoveTags.size() == toRemove.size()) {
            throw new CommandException(MESSAGE_TAG_TO_REMOVE_DOES_NOT_EXIST);
        }
        newTags.removeIf(toRemove::contains);
        return newTags;
    }

    private Set<Tag> getTagsAfterAdd(Set<Tag> newTags) throws CommandException {
        if (newTags.containsAll(toAdd)) {
            throw new CommandException(MESSAGE_TAG_TO_ADD_ALREADY_EXISTS);
        }
        newTags.addAll(toAdd);
        return newTags;
    }

    public String getTagSuccessMessage(Person editedPerson, boolean tagsAdded, boolean tagsRemoved) {
        String successMessage = "";
        if (tagsAdded && tagsRemoved) {
            successMessage = String.format(MESSAGE_TAGGED_PERSON_SUCCESS, editedPerson);
        } else if (tagsAdded) {
            successMessage = String.format(MESSAGE_ADD_TAG_SUCCESS, editedPerson);
        } else if (tagsRemoved) {
            successMessage = String.format(MESSAGE_REMOVE_TAG_SUCCESS, editedPerson);
        }
        return successMessage;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(targetIndex.getZeroBased());

        Set<Tag> newTags = new HashSet<>(personToEdit.getTags());
        if (!toRemove.isEmpty()) {
            newTags = getTagsAfterRemove(newTags);
        }
        if (!toAdd.isEmpty()) {
            newTags = getTagsAfterAdd(newTags);
        }

        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getTelegram(), personToEdit.getGithub(),
                personToEdit.getPhone(), personToEdit.getEmail(), personToEdit.getAddress(), newTags,
                personToEdit.isFavorite(), personToEdit.getProfilePicture(), personToEdit.getGitStats());

        model.setPerson(personToEdit, editedPerson);
        if (model.getPersonListControl() != null) {
            model.getPersonListControl().refreshPersonListUI();
        }
        if (model.getPersonListControl() != null) {
            model.setSelectedIndex(model.getFilteredPersonList().indexOf(editedPerson));
            model.getPersonListControl().refreshPersonListUI();
        }
        return new CommandResult(getTagSuccessMessage(editedPerson, !toAdd.isEmpty(), !toRemove.isEmpty()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagCommand // instanceof handles nulls
                && targetIndex.equals(((TagCommand) other).targetIndex))
                && toAdd.containsAll(((TagCommand) other).toAdd)
                && toRemove.containsAll(((TagCommand) other).toRemove); // state check
    }
}
