package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.EditUtil.EditPersonDescriptor;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class UntagCommand extends Command {

    public static final String COMMAND_WORD = "untag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes a tag from the "
            + "details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TAG + "friend";

    public static final String MESSAGE_REMOVE_PERSON_SUCCESS = "Removed tag(s) from %1$s: %2$s";
    public static final String MESSAGE_NOT_REMOVED = "At least one tag to be removed must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";
    public static final String MESSAGE_TAG_NOT_IN_PERSON = "%s does not have the following tags: %s";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public UntagCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_REMOVE_PERSON_SUCCESS, editedPerson.getName(),
                getRemovedTags(editPersonDescriptor)));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor)
            throws CommandException {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Birthday updatedBirthday = editPersonDescriptor.getBirthday().orElse(personToEdit.getBirthday().orElse(null));

        Set<Tag> removedTags = editPersonDescriptor.getTags().orElse(new HashSet<Tag>());
        Set<Tag> updatedTags = new HashSet<>(personToEdit.getTags());
        if (!updatedTags.containsAll(removedTags)) {
            throw new CommandException(String.format(MESSAGE_TAG_NOT_IN_PERSON, updatedName,
                    getNotFoundTags(updatedTags, removedTags)));
        }
        updatedTags.removeAll(removedTags);

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags, updatedBirthday);
    }

    /**
     * Retrieves tags that user wants to remove, but are not present in the contact.
     * @param originalTags Tags that contact are tagged with
     * @param removedTags Tags to be removed
     * @return String containing tags that are not found
     */
    public static String getNotFoundTags(Set<Tag> originalTags, Set<Tag> removedTags) {
        // filters out tags that are not found in original tags
        // map to abstract out tagNames and join them into a string
        return removedTags.stream().filter(x -> !originalTags.contains(x)).map(x -> x.tagName)
                .collect(Collectors.joining(", "));
    }

    /**
     * Retrieves tags that are removed.
     * @param editPersonDescriptor details to edit the person with
     * @return String containing tags that are removed
     */
    public static String getRemovedTags(EditPersonDescriptor editPersonDescriptor) {
        return editPersonDescriptor.getTags().orElse(new HashSet<Tag>()).stream()
                .map(tag -> tag.tagName).collect(Collectors.joining(", "));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UntagCommand)) {
            return false;
        }

        // state check
        UntagCommand e = (UntagCommand) other;
        return index.equals(e.index)
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }
}
