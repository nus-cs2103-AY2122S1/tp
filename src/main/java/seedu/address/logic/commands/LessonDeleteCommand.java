package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;

/**
 * Deletes a Lesson from an existing person in the address book.
 */
public class LessonDeleteCommand extends Command {
    public static final String COMMAND_WORD = "ldelete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes the lesson identified by lesson index"
        + " of the person identified by the index number used in the displayed person list.\n"
        + "Parameters: INDEX (must be a positive integer) LESSON_INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1 " + "1";

    public static final String MESSAGE_DELETE_LESSON_SUCCESS = "Deleted Lesson: %1$s\nfor Person: %2$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final Index index;
    private final Index lessonIndex;

    /**
     * @param index of the person in the filtered person list to delete lesson from
     */
    public LessonDeleteCommand(Index index, Index lessonIndex) {
        requireNonNull(index);
        requireNonNull(lessonIndex);
        this.index = index;
        this.lessonIndex = lessonIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        Set<Lesson> lessons = new HashSet<>(personToEdit.getLessons());
        List<Lesson> lessonList = new HashSet<>(lessons).stream()
            .sorted(Comparator.comparing(lesson -> lesson.getDate().getLocalDate()))
            .collect(Collectors.toList());
        Lesson toRemove = lessonList.get(lessonIndex.getZeroBased());

        Person editedPerson = createEditedPerson(personToEdit, lessonList, toRemove);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_DELETE_LESSON_SUCCESS, toRemove, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     */
    private static Person createEditedPerson(Person personToEdit,
                                             List<Lesson> updatedLessons, Lesson toRemove) {
        assert personToEdit != null;

        Name updatedName = personToEdit.getName();
        Phone updatedPhone = personToEdit.getPhone();
        Email updatedEmail = personToEdit.getEmail();
        Address updatedAddress = personToEdit.getAddress();
        Remark updatedRemark = personToEdit.getRemark();
        Set<Tag> updatedTags = personToEdit.getTags();

        updatedLessons.remove(toRemove);
        HashSet<Lesson> updatedLessonSet = new HashSet<>(updatedLessons);

        return new Person(updatedName, updatedPhone, updatedEmail,
            updatedAddress, updatedRemark, updatedTags, updatedLessonSet);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LessonDeleteCommand)) {
            return false;
        }

        // state check
        LessonDeleteCommand e = (LessonDeleteCommand) other;
        return index.equals(e.index)
            && lessonIndex.equals(e.lessonIndex);
    }
}
