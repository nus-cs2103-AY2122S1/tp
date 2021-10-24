package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.lesson.Date;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.MakeUpLesson;
import seedu.address.model.lesson.RecurringLesson;
import seedu.address.model.person.Person;
import seedu.address.model.util.PersonUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Contains integration tests (interaction with the Model) and unit tests for LessonDeleteCommand.
 */

public class LessonCancelCommand extends UndoableCommand {

    public static final String COMMAND_ACTION = "Delete Lesson";

    public static final String COMMAND_WORD = "cancel";

    public static final String COMMAND_PARAMETERS =
            "INDEX (must be a positive integer) " + "LESSON_INDEX (must be a positive integer)" + " date/DATE";

    public static final String COMMAND_FORMAT = COMMAND_WORD + " " + COMMAND_PARAMETERS;

    public static final String COMMAND_EXAMPLE = COMMAND_WORD + " 1 " + "1";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Cancels the lesson identified by lesson index" +
            " of the student identified by the index number used in the displayed student list.\n" + "Parameters: " +
            COMMAND_PARAMETERS + "\n" + "Example: " + COMMAND_EXAMPLE;

    public static final String MESSAGE_DELETE_LESSON_SUCCESS = "Cancelled Lesson: %1$s\nfor student: %2$s";

    public static final String MESSAGE_INVALID_LESSON_DATE = "This lesson does not occur on the given date."; // todo

    private final Index studentIndex;
    private final Index lessonIndex;
    private final Optional<Date> cancelledDate;

    private Person personBeforeLessonCancel;
    private Person personAfterLessonCancel;

    /**
     * @param studentIndex of the person in the filtered person list to delete lesson from
     */
    public LessonCancelCommand(Index studentIndex, Index lessonIndex, Optional<Date> cancelledDate) {
        requireAllNonNull(studentIndex, lessonIndex, cancelledDate);
        this.studentIndex = studentIndex;
        this.lessonIndex = lessonIndex;
        this.cancelledDate = cancelledDate;
    }

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        personBeforeLessonCancel = getPerson(lastShownList);

        List<Lesson> lessonList = personBeforeLessonCancel.getLessons().stream().sorted().collect(Collectors.toList());
        Lesson selectedLesson  = getSelectedLesson(lessonList);
        Lesson lessonAfterCancelled = createCancelledLesson(selectedLesson);

        lessonList.set(lessonIndex.getZeroBased(), lessonAfterCancelled);

        personAfterLessonCancel = createEditedPerson(personBeforeLessonCancel, lessonList);

        model.setPerson(personBeforeLessonCancel, personAfterLessonCancel);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_DELETE_LESSON_SUCCESS, lessonAfterCancelled, personAfterLessonCancel));
    }

    private Person getPerson(List<Person> personList) throws CommandException {
        if (studentIndex.getZeroBased() >= personList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

         return personList.get(studentIndex.getZeroBased());
    }

    private Lesson getSelectedLesson(List<Lesson> lessonList) throws CommandException {
        if (lessonIndex.getZeroBased() >= lessonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
        }

        return lessonList.get(lessonIndex.getZeroBased());
    }

    private Lesson createCancelledLesson(Lesson lessonToCancel) throws CommandException {
        Date date = cancelledDate.orElse(lessonToCancel.getStartDate()); // todo: change to getStartDate
        // check if date is correct
        if (!lessonToCancel.hasLessonOnDate(date)) {
            throw new CommandException(MESSAGE_INVALID_LESSON_DATE);
        }
        Set<Date> updatedCancelledDates = new HashSet<>(lessonToCancel.getCancelledDates());
        updatedCancelledDates.add(date);

        // Create new lesson with updated cancelled dates
        return lessonToCancel.createUpdatedCancelledDatesLesson(updatedCancelledDates);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * Removes specified {@code Lesson} from the updatedLessons for this person.
     */
    private Person createEditedPerson(Person personToEdit, List<Lesson> updatedLessons) {
        assert personToEdit != null;

        TreeSet<Lesson> updatedLessonSet = new TreeSet<>(updatedLessons);
        return PersonUtil.createdEditedPerson(personToEdit, updatedLessonSet);
    }


    @Override
    protected void undo() {
        requireNonNull(model);

        model.setPerson(personAfterLessonCancel, personBeforeLessonCancel);
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
        if (!(other instanceof LessonCancelCommand)) {
            return false;
        }

        // state check
        LessonCancelCommand e = (LessonCancelCommand) other;
        return studentIndex.equals(e.studentIndex)
                && lessonIndex.equals(e.lessonIndex)
                && cancelledDate.equals(e.cancelledDate);
    }
}
