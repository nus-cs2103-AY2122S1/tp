package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Person;
import seedu.address.model.util.PersonUtil;

/**
 * Deletes a Lesson from an existing person in the address book.
 */
public class LessonDeleteCommand extends Command {
    public static final String COMMAND_WORD = "ldelete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes the lesson identified by lesson index"
            + " of the student identified by the index number used in the displayed student list.\n"
            + "Parameters: INDEX (must be a positive integer) LESSON_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 " + "1";

    public static final String MESSAGE_DELETE_LESSON_SUCCESS = "Deleted Lesson: %1$s\nfor student: %2$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This student already exists in the address book.";

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

        Set<Lesson> lessons = new TreeSet<>(personToEdit.getLessons());
        if (lessonIndex.getZeroBased() >= lessons.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
        }

        List<Lesson> lessonList = lessons.stream().sorted().collect(Collectors.toList());
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
     * Removes specified {@code Lesson} from the updatedLessons for this person.
     */
    private static Person createEditedPerson(Person personToEdit,
                                             List<Lesson> updatedLessons, Lesson toRemove) {
        assert personToEdit != null;

        updatedLessons.remove(toRemove);
        TreeSet<Lesson> updatedLessonSet = new TreeSet<>(updatedLessons);

        return PersonUtil.createdEditedPerson(personToEdit, updatedLessonSet);
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
