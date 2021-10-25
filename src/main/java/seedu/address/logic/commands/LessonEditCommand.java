package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.lesson.Date;
import seedu.address.model.lesson.Homework;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.LessonRates;
import seedu.address.model.lesson.MakeUpLesson;
import seedu.address.model.lesson.RecurringLesson;
import seedu.address.model.lesson.Subject;
import seedu.address.model.lesson.TimeRange;
import seedu.address.model.person.Person;
import seedu.address.model.util.PersonUtil;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CANCEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOMEWORK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNCANCEL;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

/**
 * Edits the details of an existing person in the address book.
 */
public class LessonEditCommand extends UndoableCommand {

    public static final String COMMAND_ACTION = "Edit Lesson";

    public static final String COMMAND_WORD = "ledit";

    public static final String COMMAND_PARAMETERS = "INDEX (must be a positive integer) "
            + "LESSON_INDEX (must be a positive integer)\n"
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_TIME + "HHmm-HHmm] "
            + "[" + PREFIX_SUBJECT + "SUBJECT] "
            + "[" + PREFIX_RATES + "RATE] "
            + "[" + PREFIX_HOMEWORK + "HOMEWORK]... "
            + "[" + PREFIX_CANCEL + "CANCEL_DATE]... "
            + "[" + PREFIX_UNCANCEL + "UNCANCEL_DATE]...";

    public static final String COMMAND_EXAMPLE = COMMAND_WORD + " 1 1 "
            + PREFIX_HOMEWORK + "Textbook Pg2 "
            + PREFIX_SUBJECT + "Biology "
            + PREFIX_CANCEL + "5 oct 2021 "
            + PREFIX_UNCANCEL + "12 oct 2021";


    public static final String COMMAND_FORMAT = COMMAND_WORD + " " + COMMAND_PARAMETERS;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the lesson identified by lesson index"
            + " of the student identified by the index number used in the displayed student list.\n"
            + "All fields of a lesson can be edited, with the exception of the start date.\n"
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: " + COMMAND_PARAMETERS + "\n"
            + "Example: " + COMMAND_EXAMPLE;

    public static final String MESSAGE_EDIT_LESSON_SUCCESS = "Edited lesson for student %1$s:\n%2$s\nto %3$s";
    public static final String MESSAGE_CLASHING_LESSON = "This edit will result in clashes with an existing lesson.";
    public static final String MESSAGE_NOT_EDITED = "You must be provide at least one field to edit!";
    public static final String MESSAGE_ATTEMPT_TO_EDIT_TYPE = "You cannot edit the type of a lesson. Please add another"
            + " lesson if you wish to do so.";
    public static final String MESSAGE_INVALID_CANCEL_DATE =
            "Cannot cancel! This lesson does not occur on the given date."; // todo
    public static final String MESSAGE_INVALID_UNCANCEL_DATE =
            "Cannot uncancel! This lesson does not have cancelled dates that occur on the given date."; // todo
    private final Index personIndex;
    private final Index lessonIndex;
    private final EditLessonDescriptor editLessonDescriptor;
    private Person personBeforeLessonEdit;
    private Person personAfterLessonEdit;

    /**
     * @param personIndex of the person in the filtered person list to edit.
     * @param lessonIndex to edit.
     */
    public LessonEditCommand(Index personIndex, Index lessonIndex, EditLessonDescriptor editLessonDescriptor) {
        requireNonNull(personIndex);
        requireNonNull(lessonIndex);

        this.personIndex = personIndex;
        this.lessonIndex = lessonIndex;
        this.editLessonDescriptor = editLessonDescriptor;
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, Set<Lesson> updatedLessons) {
        assert personToEdit != null;

        return PersonUtil.createdEditedPerson(personToEdit, updatedLessons);
    }

    /**
     * Creates and returns a {@code Lesson} with the details of {@code lessonToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Lesson createEditedLesson(Lesson lessonToEdit, EditLessonDescriptor editLessonDescriptor)
            throws CommandException {
        assert lessonToEdit != null;
        Date startDate = lessonToEdit.getStartDate();
        Date updatedDate = editLessonDescriptor.getDate().orElse(startDate);
        // update start date -> reset cancelled date list
        // get cancelled dates and filter only relevant ones

        TimeRange updatedTimeRange = editLessonDescriptor.getTimeRange().orElse(lessonToEdit.getTimeRange());
        Subject updatedSubject = editLessonDescriptor.getSubject().orElse(lessonToEdit.getSubject());
        Set<Homework> updatedHomeworkSet = editLessonDescriptor.getHomeworkSet().orElse(lessonToEdit.getHomework());
        LessonRates updatedRate = editLessonDescriptor.getRate().orElse(lessonToEdit.getLessonRates());

        // filter out dates after start date
        Set<Date> cancelledDates = lessonToEdit.getCancelledDates().stream().filter(
                date -> updatedDate.isSameDayOfWeek(startDate) && !updatedDate.isAfter(date)).collect(
                Collectors.toSet());

        Lesson lessonBeforeUpdateCancelledDates = lessonToEdit.isRecurring() ? new RecurringLesson(updatedDate,
                updatedTimeRange, updatedSubject, updatedHomeworkSet, updatedRate, cancelledDates) : new MakeUpLesson(
                updatedDate, updatedTimeRange, updatedSubject, updatedHomeworkSet, updatedRate, cancelledDates);
        Set<Date> updatedCancelledDates = createEditedCancelledDates(lessonBeforeUpdateCancelledDates,
                editLessonDescriptor);
        return lessonBeforeUpdateCancelledDates.createUpdatedCancelledDatesLesson(updatedCancelledDates);
    }

    private static Set<Date> createEditedCancelledDates(Lesson lesson, EditLessonDescriptor editLessonDescriptor)
            throws CommandException {
        Set<Date> validCancelledDates = new HashSet<>(lesson.getCancelledDates());

        Set<Date> datesToCancel = new HashSet<>(editLessonDescriptor.getCancelledDates().orElse(new HashSet<>()));
        Set<Date> datesToUncancel = new HashSet<>(editLessonDescriptor.getUncancelledDates().orElse(new HashSet<>()));

        // remove dates that appear in both cancelled and uncancelled input
        Set<Date> duplicates = new HashSet<>(datesToCancel);
        duplicates.retainAll(datesToUncancel);
        datesToCancel.removeAll(duplicates);
        datesToUncancel.removeAll(duplicates);

        // Process cancelled dates
        for (Date date : datesToCancel) {
            // check if date is an existing lesson date
            if (!lesson.hasLessonOnDate(date)) {
                throw new CommandException(MESSAGE_INVALID_CANCEL_DATE);
            }
            // Add to cancelled dates
            validCancelledDates.add(date);
        }

        // Check uncancelled dates
        for (Date date : datesToUncancel) {
            // check if date is an existing lesson date
            if (!validCancelledDates.contains(date)) {
                throw new CommandException(MESSAGE_INVALID_UNCANCEL_DATE);
            }
            // Remove from cancelled dates
            validCancelledDates.remove(date);
        }
        return validCancelledDates;
    }

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        personBeforeLessonEdit = getPerson(lastShownList);

        List<Lesson> lessonList = personBeforeLessonEdit.getLessons().stream().sorted().collect(Collectors.toList());
        Lesson lessonToEdit = getLesson(lessonList);
        Lesson editedLesson = createEditedLesson(lessonToEdit, editLessonDescriptor);
        Set<Lesson> updatedLessons = createUpdatedLessons(lessonList, editedLesson, lessonToEdit);

        personAfterLessonEdit = createEditedPerson(personBeforeLessonEdit, updatedLessons);

        model.setPerson(personBeforeLessonEdit, personAfterLessonEdit);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(
                String.format(MESSAGE_EDIT_LESSON_SUCCESS, personAfterLessonEdit.getName(), lessonToEdit, editedLesson),
                personAfterLessonEdit);
    }

    private Person getPerson(List<Person> personList) throws CommandException {
        if (personIndex.getZeroBased() >= personList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        return personList.get(personIndex.getZeroBased());
    }

    private Lesson getLesson(List<Lesson> lessonList) throws CommandException {
        if (lessonIndex.getZeroBased() >= lessonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
        }

        return lessonList.get(lessonIndex.getZeroBased());
    }

    private Set<Lesson> createUpdatedLessons(List<Lesson> lessonList, Lesson edited, Lesson toEdit)
            throws CommandException {
        /*
        Check if the edited lesson clashes with any existing lessons apart from the one to be
        edited.
         */
        if (model.hasClashingLesson(edited, toEdit)) {
            throw new CommandException(MESSAGE_CLASHING_LESSON);
        }
        Set<Lesson> updatedLessons = new TreeSet<>(
                lessonList.stream().map(lesson -> lesson.equals(toEdit) ? edited : lesson).collect(Collectors.toSet()));

        return updatedLessons;
    }

    @Override
    protected void undo() {
        requireNonNull(model);

        model.setPerson(personAfterLessonEdit, personBeforeLessonEdit);
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
        if (!(other instanceof LessonEditCommand)) {
            return false;
        }

        // state check
        LessonEditCommand e = (LessonEditCommand) other;
        return personIndex.equals(e.personIndex) && lessonIndex.equals(lessonIndex) && editLessonDescriptor.equals(
                e.editLessonDescriptor);
    }

    /**
     * Stores the details to edit the lesson with. Each non-empty field value will replace the
     * corresponding field value of the lesson.
     */
    public static class EditLessonDescriptor {

        private Date date;
        private TimeRange timeRange;
        private Subject subject;
        private Set<Homework> homeworkSet;
        private LessonRates rate;
        private Set<Date> cancelledDates;
        private Set<Date> uncancelledDates;


        // Absence of isRecurring boolean field as changes to type of lesson is disallowed.

        public EditLessonDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code homeworkSet} is used internally.
         */
        public EditLessonDescriptor(EditLessonDescriptor toCopy) {
            setDate(toCopy.date);
            setTimeRange(toCopy.timeRange);
            setSubject(toCopy.subject);
            setHomeworkSet(toCopy.homeworkSet);
            setRate(toCopy.rate);
            setCancelledDates(toCopy.cancelledDates);
            setUncancelledDates(toCopy.uncancelledDates);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(date, timeRange, subject, homeworkSet, rate, cancelledDates,
                    uncancelledDates);
        }

        public Optional<Date> getDate() {
            return Optional.ofNullable(date);
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Optional<TimeRange> getTimeRange() {
            return Optional.ofNullable(timeRange);
        }

        public void setTimeRange(TimeRange timeRange) {
            this.timeRange = timeRange;
        }

        public Optional<Subject> getSubject() {
            return Optional.ofNullable(subject);
        }

        public void setSubject(Subject subject) {
            this.subject = subject;
        }

        /**
         * Sets {@code homeworkSet} to this object's {@code homeworkSet}.
         * A defensive copy of {@code homeworkSet} is used internally.
         */
        public Optional<Set<Homework>> getHomeworkSet() {
            return (homeworkSet != null) ? Optional.of(Collections.unmodifiableSet(homeworkSet)) : Optional.empty();
        }

        /**
         * Returns an unmodifiable homework set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code homeworkSet} is null.
         */
        public void setHomeworkSet(Set<Homework> homeworkSet) {
            this.homeworkSet = (homeworkSet != null) ? new HashSet<>(homeworkSet) : null;
        }

        public Optional<LessonRates> getRate() {
            return Optional.ofNullable(rate);
        }

        public void setRate(LessonRates rate) {
            this.rate = rate;
        }

        public Optional<Set<Date>> getCancelledDates() {
            return (cancelledDates != null) ? Optional.of(Collections.unmodifiableSet(cancelledDates)) :
                    Optional.empty();
        }

        public void setCancelledDates(Set<Date> cancelledDates) {
            this.cancelledDates = (cancelledDates != null) ? new HashSet<>(cancelledDates) : null;
        }

        public Optional<Set<Date>> getUncancelledDates() {
            return (uncancelledDates != null) ? Optional.of(Collections.unmodifiableSet(uncancelledDates)) :
                    Optional.empty();
        }

        public void setUncancelledDates(Set<Date> uncancelledDates) {
            this.uncancelledDates = (uncancelledDates != null) ? new HashSet<>(uncancelledDates) : null;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditLessonDescriptor)) {
                return false;
            }

            // state check
            EditLessonDescriptor e = (EditLessonDescriptor) other;

            return getDate().equals(e.getDate()) && getTimeRange().equals(e.getTimeRange()) && getSubject().equals(
                    e.getSubject()) && getHomeworkSet().equals(e.getHomeworkSet()) && getRate().equals(e.getRate()) &&
                    getUncancelledDates().equals(e.getUncancelledDates()) && getCancelledDates().equals(
                    e.getCancelledDates());
        }
    }
}
