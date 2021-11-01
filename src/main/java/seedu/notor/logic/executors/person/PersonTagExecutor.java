package seedu.notor.logic.executors.person;

import static seedu.notor.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Set;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.model.person.Person;
import seedu.notor.model.tag.Tag;

/**
 * Executor for a PersonTagCommand.
 */
public class PersonTagExecutor extends PersonExecutor {
    public static final String MESSAGE_TAG_PERSON_SUCCESS = "Added Tags to %1$s";
    public static final String MESSAGE_ALL_TAGS_ADDED_DUPLICATES = "The person already has these tags";

    private final Set<Tag> tags;

    /**
     * Constructor for a PersonTagExecutor instance.
     *
     * @param index Index of the person to be edited.
     * @param tags Tags to add.
     */
    public PersonTagExecutor(Index index, Set<Tag> tags) {
        super(index);
        requireAllNonNull(index, tags);
        this.tags = tags;
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        checkPersonList();
        Person person = super.getPerson();
        Person taggedPerson = createTaggedPerson(person, tags);

        if (person.equals(taggedPerson) && model.hasPerson(taggedPerson)) {
            throw new ExecuteException(MESSAGE_ALL_TAGS_ADDED_DUPLICATES);
        }

        model.setPerson(person, taggedPerson);
        return new CommandResult(String.format(MESSAGE_TAG_PERSON_SUCCESS, taggedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToTag} with tags added. If the
     * tags are already present, it will return the same person.
     */
    private static Person createTaggedPerson(Person personToTag, Set<Tag> tags) {
        assert personToTag != null;

        Set<Tag> updatedTags = new HashSet<>(personToTag.getTags());
        updatedTags.addAll(tags);

        return new Person(personToTag.getName(), personToTag.getPhone(), personToTag.getEmail(),
                personToTag.getNote(), updatedTags, personToTag.getDisplaySuperGroups(),
                    personToTag.getDisplaySubGroups());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonTagExecutor)) {
            return false;
        }

        PersonTagExecutor e = (PersonTagExecutor) other;

        // state check
        return super.equals(other) && tags.equals(e.tags);
    }
}
