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
 * Executor for a PersonUntagCommand.
 */
public class PersonUntagExecutor extends PersonExecutor {
    public static final String MESSAGE_TAG_PERSON_SUCCESS = "Removed Tags from %1$s";
    public static final String MESSAGE_TAGS_NOT_FOUND = "The person does not have any of these tag(s)";

    private final Set<Tag> tags;

    /**
     * Constructor for a PersonUntagExecutor instance.
     *
     * @param index Index of the person to be edited.
     * @param tags Tags to add.
     */
    public PersonUntagExecutor(Index index, Set<Tag> tags) {
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
            throw new ExecuteException(MESSAGE_TAGS_NOT_FOUND);
        }

        model.setPerson(person, taggedPerson);
        return new CommandResult(String.format(MESSAGE_TAG_PERSON_SUCCESS, taggedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToTag} with tags removed, if they
     * are present. If the tags are not present in the tagged person, it will return the same person.
     */
    private static Person createTaggedPerson(Person personToTag, Set<Tag> tags) {
        assert personToTag != null;

        Set<Tag> updatedTags = new HashSet<>(personToTag.getTags());
        updatedTags.removeAll(tags);

        return new Person(personToTag.getName(), personToTag.getPhone(), personToTag.getEmail(),
                personToTag.getNote(), updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonUntagExecutor)) {
            return false;
        }

        PersonUntagExecutor e = (PersonUntagExecutor) other;

        // state check
        return super.equals(other) && tags.equals(e.tags);
    }
}
