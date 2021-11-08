package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.EditUtil.EditPersonDescriptor;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
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
import seedu.address.model.person.Pin;
import seedu.address.model.tag.Tag;

/**
 * Adds one or more new {@code Tag} objects to the details of an existing {@code Person} in the address book.
 */
public class TagCommand extends Command {
    public static final String COMMAND_WORD = "tag";
    public static final String COMMAND_DESCRIPTION = "Adds one or more Tags to the details of the contact "
            + "identified by the index number used in the displayed contact list.\n";
    public static final String COMMAND_EXAMPLE = "Parameters: INDEX (must be a positive integer) "
            + PREFIX_TAG + "TAG "
            + "[" + PREFIX_TAG + "EXTRA_TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_TAG + "ExampleTag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": " + COMMAND_DESCRIPTION + COMMAND_EXAMPLE;

    public static final String MESSAGE_TAG_ADD_SUCCESS = "Contact %1$s now has tags: %2$s";
    public static final String MESSAGE_TAG_ADD_EXISTS = "Contact %1$s already had tags: %2$s";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * Creates a {@code TagCommand} which tags the contact at the index.
     *
     * @param index of the {@code Person} in the filtered person list to edit.
     * @param editPersonDescriptor details the tags to add to the {@code Person}.
     */
    public TagCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    /**
     * Executes the {@code TagCommand} which tags the contact at the index.
     *
     * @param model {@code Model} which the command should operate on.
     * @return {@code CommandResult} regarding the status of the {@code TagCommand}.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        assert personToEdit.isSamePerson(editedPerson);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        Set<Tag> oldTags = personToEdit.getTags();
        Predicate<Tag> tagInOldTags = testTag -> oldTags.contains(testTag);

        Set<Tag> overlapTags = editPersonDescriptor.getTags().orElse(new HashSet<>()).stream()
                .filter(tagInOldTags).collect(Collectors.toSet());

        String successMessage = String.format(MESSAGE_TAG_ADD_SUCCESS, editedPerson.getName(), editedPerson.getTags());
        String existsMessage = !overlapTags.isEmpty()
                ? '\n' + String.format(MESSAGE_TAG_ADD_EXISTS, editedPerson.getName(), overlapTags)
                : "";
        return new CommandResult(successMessage + existsMessage);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * with added tags from {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(personToEdit);

        Name unchangedName = personToEdit.getName();
        Phone unchangedPhone = personToEdit.getPhone();
        Email unchangedEmail = personToEdit.getEmail();
        Address unchangedAddress = personToEdit.getAddress();
        Birthday unchangedBirthday = personToEdit.getBirthday().orElse(null);
        Pin unchangedPin = personToEdit.getPin();

        Set<Tag> existingTags = personToEdit.getTags();
        Set<Tag> addedTags = editPersonDescriptor.getTags().orElse(new HashSet<>());
        Set<Tag> updatedTags = new HashSet<>();
        updatedTags.addAll(existingTags);
        updatedTags.addAll(addedTags);

        return new Person(unchangedName, unchangedPhone, unchangedEmail, unchangedAddress,
                updatedTags,
                unchangedBirthday, unchangedPin);
    }

    /**
     * Checks if {@code other} is equal to {@code this}.
     *
     * @param other the object to check if it is equal to {@code this}.
     * @return {@code boolean} indicating if it is equal.
     */
    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagCommand)) {
            return false;
        }

        // state check
        TagCommand e = (TagCommand) other;
        return index.equals(e.index)
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }

}
