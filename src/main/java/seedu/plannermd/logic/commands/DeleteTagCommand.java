package seedu.plannermd.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.plannermd.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.plannermd.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.plannermd.commons.core.index.Index;
import seedu.plannermd.logic.commands.exceptions.CommandException;
import seedu.plannermd.model.Model;
import seedu.plannermd.model.person.Person;
import seedu.plannermd.model.tag.Tag;

/**
 * Deletes a tag from an existing person in the plannermd.
 */
public class DeleteTagCommand extends Command {

    public static final String COMMAND_WORD = "tag -d";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a tag from the person identified "
            + "by the index number used in the displayed person list.\n"
            + "Parameters: "
            + PREFIX_ID + "ID (must be a positive integer) "
            + PREFIX_TAG + "TAG\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_ID + "1 "
            + PREFIX_TAG + "healthy";

    public static final String MESSAGE_DELETE_TAG_SUCCESS = "Deleted tag from Patient: %1$s";
    public static final String MESSAGE_INVALID_TAG = "The tag does not exist.";

    private final Index index;
    private final Tag tag;

    /**
     * @param index of the person in the filtered person list for deleting a tag
     * @param tag   the tag to be deleted
     */
    public DeleteTagCommand(Index index, Tag tag) {
        requireNonNull(index);
        requireNonNull(tag);

        this.index = index;
        this.tag = tag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Set<Tag> existingTags = new HashSet<>(personToEdit.getTags());

        if (!existingTags.contains(tag)) {
            throw new CommandException(MESSAGE_INVALID_TAG);
        }

        existingTags.remove(tag);
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), existingTags
        );

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_DELETE_TAG_SUCCESS, editedPerson));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteTagCommand)) {
            return false;
        }

        // state check
        DeleteTagCommand c = (DeleteTagCommand) other;
        return index.equals(c.index)
                && tag.equals(c.tag);
    }
}
