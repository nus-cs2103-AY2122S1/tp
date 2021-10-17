package seedu.fast.logic.commands;

import static seedu.fast.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Set;

import seedu.fast.commons.core.Messages;
import seedu.fast.commons.core.index.Index;
import seedu.fast.logic.commands.exceptions.CommandException;
import seedu.fast.model.Model;
import seedu.fast.model.person.Person;
import seedu.fast.model.person.Remark;
import seedu.fast.model.tag.Tag;

/**
 * Edits the tag(s) of an existing person in FAST.
 */
public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the tag(s) of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing tags will not be overwritten by the input.\n\n"
            + "Parameters: \nINDEX (must be a postive integer) "
            + "t/ [TAGNAME]\n\n"
            + "Example: \n" + COMMAND_WORD + " 1 "
            + "add/Friend";

    public static final String MESSAGE_ADD_TAG_SUCCESS = "Added tag to Person: %1$s";
    public static final String MESSAGE_ADD_TAG_FAILURE = "Failed to add the tag to Person: %1$s";

    private final Index index;
    private Tag tag;

    /**
     * Construct for a {@code RemarkCommand}
     *
     * @param index index of the person in the filtered person list to edit the remark
     * @param tag remark of the person to be updated to
     */
    public TagCommand(Index index, Tag tag) {
        requireAllNonNull(index, tag);

        this.index = index;
        this.tag = tag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        Set<Tag> newTags = personToEdit.getTags();
        try {
            boolean isSuccess = newTags.(tag);
            assert(isSuccess);
        } catch () {

        }

        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getRemark(), personToEdit.getTags(), personToEdit.getAppointment(),
                personToEdit.getCount());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether
     * the remark is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        return String.format(MESSAGE_ADD_TAG_SUCCESS, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemarkCommand)) {
            return false;
        }

        // state check
        TagCommand e = (TagCommand) other;
        return index.equals(e.index) && tag.equals(e.tag);
    }

}
