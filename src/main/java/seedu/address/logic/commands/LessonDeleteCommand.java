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
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.AcadLevel;
import seedu.address.model.person.AcadStream;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Fee;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.School;
import seedu.address.model.tag.Tag;

/**
 * Deletes a Lesson from an existing person in the address book.
 */

public class LessonDeleteCommand extends UndoableCommand {

    public static final String COMMAND_ACTION = "Delete Lesson";

    public static final String COMMAND_WORD = "ldelete";

    public static final String COMMAND_PARAMETERS = "INDEX (must be a positive integer) "
            + "LESSON_INDEX (must be a positive integer)";

    public static final String COMMAND_FORMAT = COMMAND_WORD + " " + COMMAND_PARAMETERS;

    public static final String COMMAND_EXAMPLE = COMMAND_WORD + " 1 " + "1";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes the lesson identified by lesson index"
            + " of the student identified by the index number used in the displayed student list.\n"
            + "Parameters: " + COMMAND_PARAMETERS + "\n"
            + "Example: " + COMMAND_EXAMPLE;

    public static final String MESSAGE_DELETE_LESSON_SUCCESS = "Deleted Lesson: %1$s\nfor student: %2$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This student already exists in the address book.";

    private final Index index;
    private final Index lessonIndex;
    private Person personBeforeLessonDelete;
    private Person personAfterLessonDelete;

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
    public CommandResult executeUndoableCommand() throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        personBeforeLessonDelete = lastShownList.get(index.getZeroBased());

        Set<Lesson> lessons = new TreeSet<>(personBeforeLessonDelete.getLessons());
        if (lessonIndex.getZeroBased() >= lessons.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
        }

        List<Lesson> lessonList = lessons.stream().sorted().collect(Collectors.toList());
        Lesson toRemove = lessonList.get(lessonIndex.getZeroBased());

        personAfterLessonDelete = createEditedPerson(personBeforeLessonDelete, lessonList, toRemove);

        if (!personBeforeLessonDelete.isSamePerson(personAfterLessonDelete)
                && model.hasPerson(personAfterLessonDelete)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personBeforeLessonDelete, personAfterLessonDelete);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_DELETE_LESSON_SUCCESS, toRemove, personAfterLessonDelete));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * Removes specified {@code Lesson} from the updatedLessons for this person.
     */
    private static Person createEditedPerson(Person personToEdit, List<Lesson> updatedLessons, Lesson toRemove) {
        assert personToEdit != null;

        Name updatedName = personToEdit.getName();
        Phone updatedPhone = personToEdit.getPhone();
        Email updatedEmail = personToEdit.getEmail();
        Phone updatedParentPhone = personToEdit.getParentPhone();
        Email updatedParentEmail = personToEdit.getParentEmail();
        Address updatedAddress = personToEdit.getAddress();
        School updatedSchool = personToEdit.getSchool();
        AcadStream updatedAcadStream = personToEdit.getAcadStream();
        AcadLevel updatedAcadLevel = personToEdit.getAcadLevel();
        Fee updatedOutstandingFee = personToEdit.getFee();
        Remark updatedRemark = personToEdit.getRemark();
        Set<Tag> updatedTags = personToEdit.getTags();

        updatedLessons.remove(toRemove);
        TreeSet<Lesson> updatedLessonSet = new TreeSet<>(updatedLessons);

        return new Person(updatedName, updatedPhone, updatedEmail, updatedParentPhone, updatedParentEmail,
                updatedAddress, updatedSchool, updatedAcadStream, updatedAcadLevel, updatedOutstandingFee,
                updatedRemark, updatedTags, updatedLessonSet);
    }

    @Override
    protected void undo() {
        requireNonNull(model);

        model.setPerson(personAfterLessonDelete, personBeforeLessonDelete);
    }

    @Override
    protected void redo() {
        requireNonNull(model);

        try {
            executeUndoableCommand();
        } catch (CommandException ce) {
            throw new AssertionError(MESSAGE_REDO_FAILURE);
        }
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
