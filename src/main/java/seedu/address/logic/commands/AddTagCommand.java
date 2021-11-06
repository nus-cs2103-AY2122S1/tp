package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
 * Add tags for persons in the current list.
 */
public class AddTagCommand extends Command {

    public static final String COMMAND_WORD = "addt";

    public static final String MESSAGE_USAGE = "\n" + COMMAND_WORD + ": Add tags to people.\n\n"
            + "Formats: \n"
            + " - 'addt all t/TAG' adds tags for everyone.\n"
            + " - 'addt INDEX t/TAG' adds tags for the person identified by the index number used in the displayed "
            + "person list.\n\n"
            + "Parameters: "
            + "INDEX (must be a positive integer or the word 'all') "
            + PREFIX_TAG + "TAG...\n\n"
            + "Note:\n"
            + " - Tags must be alphanumeric.\n\n"
            + "Example: "
            + COMMAND_WORD + " 1 "
            + PREFIX_TAG + "CS2103Teammate";

    public static final String MESSAGE_ADD_TAG_SUCCESS = "Added Tag: %1$s";
    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_TAG_NOT_ADDED = "There are no tags to add.";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT_INVALID_INDEX = String.format(
            MESSAGE_INVALID_COMMAND_FORMAT, "A valid index was not entered. \n%1$s");
    public static final String MESSAGE_INVALID_COMMAND_FORMAT_TAGS_ABSENT = String.format(
            MESSAGE_INVALID_COMMAND_FORMAT, "No tag was entered. \n%1$s");

    private final Index index;
    private final Set<Tag> tag;
    private Set<Tag> addedTags;

    /**
     * @param index of the person in the filtered person list to add tags.
     */
    public AddTagCommand(Index index, Set<Tag> tag) {
        this.index = index;
        this.tag = tag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        assert tag != null;
        assert !tag.isEmpty();

        if (isPresent(index)) {
            return executeSomeone(model, tag);
        }
        return executeEveryone(model, tag);
    }

    /**
     * Add tags for all person in the current list.
     */
    private CommandResult executeEveryone(Model model, Set<Tag> tag) throws CommandException {
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
        addedTags = new HashSet<>();

        int totalCounter = 0;
        duplicateCounter = 0;
        for (Tag currTag : tag) {
            int currCounter = duplicateCounter;
            for (int i = startIndex; i < endIndex; i++) {
                totalCounter++;
                Person personToEdit = personsToEdit.get(i);
                Person editedPerson = addTag(personToEdit, currTag);

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
                addedTags.add(currTag);
            }
            if (duplicateCounter >= totalCounter) {
                throw new CommandException(MESSAGE_TAG_NOT_ADDED);
            }
        }
        for (int i = startIndex; i < endIndex; i++) {
            model.setPerson(initialPersons.get(i), personsToEdit.get(i));
        }

        return new CommandResult(String.format(MESSAGE_ADD_TAG_SUCCESS, tagString(addedTags)));

    }

    /**
     * Add tags for one person in the current list.
     */
    private CommandResult executeSomeone(Model model, Set<Tag> tag) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size() || index.getZeroBased() < 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        int duplicateCounter = 0;
        addedTags = new HashSet<>();

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person currPersonToEdit = lastShownList.get(index.getZeroBased());

        for (Tag currTag : tag) {
            Person editedPerson = addTag(currPersonToEdit, currTag);

            Set<Tag> oldTag = currPersonToEdit.getTags();
            Set<Tag> newTag = editedPerson.getTags();
            boolean isEqualTags = oldTag.equals(newTag);
            if (isEqualTags) {
                duplicateCounter++;
                continue;
            }
            addedTags.add(currTag);
            currPersonToEdit = editedPerson;
        }
        if (duplicateCounter >= tag.size()) {
            throw new CommandException(MESSAGE_TAG_NOT_ADDED);
        }
        model.setPerson(personToEdit, currPersonToEdit);

        return new CommandResult(String.format(MESSAGE_ADD_TAG_SUCCESS, tagString(addedTags)) + " "
                + String.format(MESSAGE_EDIT_PERSON_SUCCESS, currPersonToEdit));
    }

    /**
     * Creates and returns a {@code Person} with tags added.
     */
    public static Person addTag(Person personToEdit, Tag tag) {
        assert personToEdit != null;

        Name name = personToEdit.getName();
        Phone phone = personToEdit.getPhone();
        Email email = personToEdit.getEmail();
        Nationality nationality = personToEdit.getNationality();
        TutorialGroup tutorialGroup = personToEdit.getTutorialGroup();
        Gender gender = personToEdit.getGender();
        Remark remark = personToEdit.getRemark();
        Set<Tag> tags = personToEdit.getTags();
        Set<SocialHandle> socialHandle = personToEdit.getSocialHandles();

        Set<Tag> editedTags = new HashSet<>(tags);
        editedTags.add(tag);
        editedTags = Collections.unmodifiableSet(editedTags);

        return new Person(name, phone, email, nationality,
                tutorialGroup, gender, remark, editedTags, socialHandle);
    }

    /**
     * Generate a string representation of tags.
     */
    public static String tagString(Set<Tag> tags) {
        final StringBuilder builder = new StringBuilder();
        if (!tags.isEmpty()) {
            tags.forEach(builder::append);
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
        if (!(other instanceof AddTagCommand)) {
            return false;
        }

        // state check
        AddTagCommand e = (AddTagCommand) other;
        if (index == null && tag == null) {
            return (index == e.index) && (tag == e.tag);
        }
        if (index == null && tag != null) {
            return (index == e.index) && tag.equals(e.tag);
        }
        if (index != null && tag == null) {
            return index.equals(e.index) && (tag == e.tag);
        }
        return index.equals(e.index) && tag.equals(e.tag);
    }
}
