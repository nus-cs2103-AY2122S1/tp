package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nationality;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.SocialHandle;
import seedu.address.model.person.TutorialGroup;
import seedu.address.model.tag.Tag;

/**
 * Delete tags of persons in the current list.
 */
public class DeleteTagCommand extends Command {

    public static final String COMMAND_WORD = "deletet";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Delete tags of persons in the displayed list.\n"
            + " - 'deletet all' deletes all tags for everyone.\n"
            + " - 'deletet INDEX' deletes all tags for the person identified by the index number used in the displayed "
            + "person list.\n"
            + " - 'deletet all [t/TAG1 t/TAG2...]' deletes the specified tags for everyone."
            + " - 'deletet INDEX [t/TAG1 t/TAG2...]' deletes the specified tags for the person identified by the index "
            + "number used in the displayed person list.\n"
            + "Parameters: "
            + "INDEX (must be a positive integer or the word 'all') "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TAG + "CS2103Teammate";

    public static final String MESSAGE_DELETED_ALL_TAG_SUCCESS = "Deleted All Tags.";
    public static final String MESSAGE_DELETED_TAG_SUCCESS = "Deleted Tag: %1$s";
    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_TAG_NOT_DELETED = "There are no tags to delete.";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT_INVALID_INDEX = String.format(
            MESSAGE_INVALID_COMMAND_FORMAT, "A valid index was not entered. \n%1$s");

    private final Index index;
    private final Set<Tag> tags;
    private Set<Tag> removedTags;

    /**
     * @param index of the person in the filtered person list to delete tags.
     */
    public DeleteTagCommand(Index index, Set<Tag> tags) {
        this.index = index;
        this.tags = tags;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (isPresent(index) && isPresent(tags)) {
            return executeSomeTagsSomeone(model);
        }
        if (isPresent(index) && isEmpty(tags)) {
            return executeAllTagsSomeone(model);
        }
        if (isEmpty(index) && isPresent(tags)) {
            return executeSomeTagsEveryone(model);
        }
        return executeAllTagsEveryone(model);
    }

    /**
     * Delete all tags of all person
     */
    private CommandResult executeAllTagsEveryone(Model model) throws CommandException {
        executeEveryone(model, null);
        return new CommandResult(MESSAGE_DELETED_ALL_TAG_SUCCESS);
    }

    /**
     * Delete all tags of one person
     */
    private CommandResult executeAllTagsSomeone(Model model) throws CommandException {
        Person editedPerson = executeSomeone(model, null);
        return new CommandResult(MESSAGE_DELETED_ALL_TAG_SUCCESS + " "
                + String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Delete some tags of all person
     */
    private CommandResult executeSomeTagsEveryone(Model model) throws CommandException {
        executeEveryone(model, tags);
        final StringBuilder builder = new StringBuilder();
        if (!tags.isEmpty()) {
            tags.forEach(builder::append);
        }
        return new CommandResult(String.format(MESSAGE_DELETED_TAG_SUCCESS, tagString(removedTags)));
    }

    /**
     * Delete some tags of one person
     */
    private CommandResult executeSomeTagsSomeone(Model model) throws CommandException {
        Person editedPerson = executeSomeone(model, tags);
        return new CommandResult(String.format(MESSAGE_DELETED_TAG_SUCCESS, tagString(removedTags)) + " "
                + String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Delete tags for all person in the current list.
     */
    private void executeEveryone(Model model, Set<Tag> tag) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        int startIndex = 0;
        int endIndex = lastShownList.size();
        int duplicateCounter = 0;

        List<Person> initialPersons = new ArrayList<>();
        List<Person> personsToEdit = new ArrayList<>();
        for (int i = startIndex; i < endIndex; i++) {
            initialPersons.add(i, lastShownList.get(i));
            personsToEdit.add(i, lastShownList.get(i));
        }
        removedTags = new HashSet<>();

        if (tag == null) {
            for (int i = startIndex; i < endIndex; i++) {
                Person personToEdit = lastShownList.get(i);
                Person editedPerson = deleteTag(personToEdit, null);

                Set<Tag> oldTag = personToEdit.getTags();
                Set<Tag> newTag = editedPerson.getTags();
                boolean isEqualTags = oldTag.equals(newTag);
                if (isEqualTags) {
                    duplicateCounter++;
                    continue;
                }
                personsToEdit.set(i, editedPerson);
            }
            if (duplicateCounter >= (endIndex - startIndex)) {
                throw new CommandException(MESSAGE_TAG_NOT_DELETED);
            }
        } else {
            int totalCounter = 0;
            duplicateCounter = 0;
            for (Tag currTag : tag) {
                int currCounter = duplicateCounter;
                for (int i = startIndex; i < endIndex; i++) {
                    totalCounter++;
                    Person personToEdit = personsToEdit.get(i);
                    Person editedPerson = deleteTag(personToEdit, currTag);

                    Set<Tag> oldTag = personToEdit.getTags();
                    Set<Tag> newTag = editedPerson.getTags();
                    boolean isEqualTags = oldTag.equals(newTag);
                    if (isEqualTags) {
                        duplicateCounter++;
                        continue;
                    }
                    personsToEdit.set(i, editedPerson);
                }
                if (duplicateCounter - currCounter < endIndex - startIndex) {
                    removedTags.add(currTag);
                }
            }
            if (duplicateCounter >= totalCounter) {
                throw new CommandException(MESSAGE_TAG_NOT_DELETED);
            }
        }
        for (int i = startIndex; i < endIndex; i++) {
            model.setPerson(initialPersons.get(i), personsToEdit.get(i));
        }
    }

    /**
     * Delete tags for one person in the current list.
     */
    private Person executeSomeone(Model model, Set<Tag> tag) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size() || index.getZeroBased() < 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        int duplicateCounter = 0;
        removedTags = new HashSet<>();
        if (tag == null) {
            Person personToEdit = lastShownList.get(index.getZeroBased());
            Person editedPerson = deleteTag(personToEdit, null);

            Set<Tag> oldTag = personToEdit.getTags();
            Set<Tag> newTag = editedPerson.getTags();
            boolean isEqualTags = oldTag.equals(newTag);
            if (isEqualTags) {
                throw new CommandException(MESSAGE_TAG_NOT_DELETED);
            }
            model.setPerson(personToEdit, editedPerson);
            return editedPerson;
        } else {
            Person personToEdit = lastShownList.get(index.getZeroBased());
            Person currPersonToEdit = lastShownList.get(index.getZeroBased());

            for (Tag currTag : tag) {
                Person editedPerson = deleteTag(currPersonToEdit, currTag);

                Set<Tag> oldTag = currPersonToEdit.getTags();
                Set<Tag> newTag = editedPerson.getTags();
                boolean isEqualTags = oldTag.equals(newTag);
                if (isEqualTags) {
                    duplicateCounter++;
                    continue;
                }
                removedTags.add(currTag);
                currPersonToEdit = editedPerson;
            }
            if (duplicateCounter >= tag.size()) {
                throw new CommandException(MESSAGE_TAG_NOT_DELETED);
            }
            model.setPerson(personToEdit, currPersonToEdit);
            return currPersonToEdit;
        }

    }

    /**
     * Creates and returns a {@code Person} with the tags of {@code personToEdit} removed.
     */
    public static Person deleteTag(Person personToEdit, Tag tagToEdit) {
        assert personToEdit != null;

        Name name = personToEdit.getName();
        Phone phone = personToEdit.getPhone();
        Email email = personToEdit.getEmail();
        Nationality nationality = personToEdit.getNationality();
        TutorialGroup tutorialGroup = personToEdit.getTutorialGroup();
        Gender gender = personToEdit.getGender();
        Remark remark = personToEdit.getRemark();
        Set<Tag> tags = personToEdit.getTags();
        Set<SocialHandle> socialHandles = personToEdit.getSocialHandles();

        Set<Tag> editedTags = new HashSet<>();
        if (tagToEdit != null) {
            editedTags = new HashSet<>(
                    new ArrayList<>(tags)
                    .stream()
                    .filter(tag -> !tag.getTagName().equalsIgnoreCase(tagToEdit.getTagName()))
                    .collect(Collectors.toList())
            );
        }
        editedTags = Collections.unmodifiableSet(editedTags);
        return new Person(name, phone, email, nationality,
                tutorialGroup, gender, remark, editedTags, socialHandles);
    }

    /**
     * Generate a string representation of tags.
     */
    public static String tagString(Set<Tag> tags) {
        final StringBuilder builder = new StringBuilder();
        if (!tags.isEmpty()) {
            tags.forEach(tag -> {
                builder.append("[")
                        .append(tag.getTagName())
                        .append("]");
            });
        }
        builder.append(".");
        return builder.toString();
    }

    /**
     * Check if Object is not null.
     */
    private boolean isPresent(Object o) {
        return (o != null);
    }

    /**
     * Check if Object is null.
     */
    private boolean isEmpty(Object o) {
        return (o == null);
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
        DeleteTagCommand e = (DeleteTagCommand) other;
        if (index == null && tags == null) {
            return (index == e.index) && (tags == e.tags);
        }
        if (index == null && tags != null) {
            return (index == e.index) && tags.equals(e.tags);
        }
        if (index != null && tags == null) {
            return index.equals(e.index) && (tags == e.tags);
        }
        return index.equals(e.index) && tags.equals(e.tags);
    }
}
