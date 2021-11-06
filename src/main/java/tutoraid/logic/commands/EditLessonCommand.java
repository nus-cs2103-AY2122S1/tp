package tutoraid.logic.commands;

import static java.util.Objects.requireNonNull;
import static tutoraid.commons.core.Messages.MESSAGE_CAPACITY_LESS_THAN_STUDENTS;
import static tutoraid.logic.parser.CliSyntax.PREFIX_LESSON_CAPACITY;
import static tutoraid.logic.parser.CliSyntax.PREFIX_LESSON_NAME;
import static tutoraid.logic.parser.CliSyntax.PREFIX_LESSON_PRICE;
import static tutoraid.logic.parser.CliSyntax.PREFIX_LESSON_TIMING;

import java.util.List;
import java.util.Optional;

import tutoraid.commons.core.Messages;
import tutoraid.commons.core.index.Index;
import tutoraid.commons.util.CollectionUtil;
import tutoraid.logic.commands.exceptions.CommandException;
import tutoraid.model.Model;
import tutoraid.model.lesson.Capacity;
import tutoraid.model.lesson.Lesson;
import tutoraid.model.lesson.LessonName;
import tutoraid.model.lesson.Price;
import tutoraid.model.lesson.Students;
import tutoraid.model.lesson.Timing;

public class EditLessonCommand extends EditCommand {

    public static final String COMMAND_FLAG = "-l";

    public static final String MESSAGE_USAGE = String.format("%1$s %2$s: Edits the details of the lesson specified."
                    + "Existing values will be overwritten by the input values."
                    + "\nParameters: "
                    + "\nLESSON INDEX (must be a positive integer)"
                    + "  [%3$sLESSON NAME]"
                    + "  [%4$sLESSON CAPACITY]"
                    + "  [%5$sLESSON PRICE]"
                    + "  [%6$sLESSON TIMING]"
                    + "\nExample:"
                    + "\n%1$s %2$s 1 %3$sMaths 2 %4$s30 %5$s110 %6$sTue 1100-1300",
            COMMAND_WORD, COMMAND_FLAG, PREFIX_LESSON_NAME, PREFIX_LESSON_CAPACITY, PREFIX_LESSON_PRICE,
            PREFIX_LESSON_TIMING);

    public static final String MESSAGE_EDIT_LESSON_SUCCESS =
            "Successfully edited %1$s. Showing %1$s and the students in this class.";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_LESSON = "This lesson already exists in TutorAid.";
    public static final String MESSAGE_NOT_CHANGED = "Warning: Attempted to edit %s but the provided field(s) did not "
            + "contain any changes.";

    private final Index targetIndex;
    private final EditLessonDescriptor editLessonDescriptor;

    /**
     * @param targetIndex          Index of the lesson in the filtered lesson list that is to be edited
     * @param editLessonDescriptor details to edit the lesson with
     */
    public EditLessonCommand(Index targetIndex, EditLessonDescriptor editLessonDescriptor) {
        requireNonNull(targetIndex);
        requireNonNull(editLessonDescriptor);

        this.targetIndex = targetIndex;
        this.editLessonDescriptor = new EditLessonDescriptor(editLessonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Lesson> lastShownLessonList = model.getFilteredLessonList();
        if (targetIndex.getZeroBased() >= lastShownLessonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
        }
        Lesson lessonToEdit = lastShownLessonList.get(targetIndex.getZeroBased());
        Lesson editedLesson = createEditedLesson(lessonToEdit, editLessonDescriptor);

        if (!lessonToEdit.isSameLesson(editedLesson) && model.hasLesson(editedLesson)) {
            throw new CommandException(MESSAGE_DUPLICATE_LESSON);
        }

        if (lessonToEdit.equals(editedLesson)) {
            throw new CommandException(String.format(MESSAGE_NOT_CHANGED, editedLesson.toNameString()));
        }

        Capacity newCapacity = editedLesson.getCapacity();
        Students currStudents = lessonToEdit.getStudents();
        if (newCapacity.getCapacity() < currStudents.numberOfStudents()) {
            throw new CommandException(MESSAGE_CAPACITY_LESS_THAN_STUDENTS);
        }
        lessonToEdit.replace(editedLesson);
        model.viewLesson(lessonToEdit);
        model.updateFilteredStudentList(student -> student.hasLesson(lessonToEdit));
        return new CommandResult(String.format(MESSAGE_EDIT_LESSON_SUCCESS, lessonToEdit.toNameString()));
    }

    /**
     * Creates and returns a {@code Lesson} with the details of {@code lessonToEdit}
     * edited with {@code editLessonDescriptor}.
     */
    private static Lesson createEditedLesson(Lesson lessonToEdit, EditLessonDescriptor editLessonDescriptor) {
        requireNonNull(lessonToEdit);
        LessonName updatedLessonName = editLessonDescriptor.getLessonName().orElse(lessonToEdit.getLessonName());
        Capacity updatedCapacity = editLessonDescriptor.getCapacity().orElse(lessonToEdit.getCapacity());
        Price updatedPrice = editLessonDescriptor.getPrice().orElse(lessonToEdit.getPrice());
        Timing updatedTiming = editLessonDescriptor.getTiming().orElse(lessonToEdit.getTiming());

        return new Lesson(updatedLessonName, updatedCapacity, updatedPrice, updatedTiming);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditLessonCommand)) {
            return false;
        }

        // state check
        EditLessonCommand e = (EditLessonCommand) other;
        return targetIndex.equals(e.targetIndex)
                && editLessonDescriptor.equals(e.editLessonDescriptor);
    }

    /**
     * Stores the details to edit the lesson with. Each non-empty field value will replace the
     * corresponding field value of the lesson.
     */
    public static class EditLessonDescriptor {
        private LessonName lessonName;
        private Timing timing;
        private Price price;
        private Capacity capacity;

        public EditLessonDescriptor() {
        }

        /**
         * Copy constructor.
         */
        public EditLessonDescriptor(EditLessonDescriptor toCopy) {
            setLessonName(toCopy.lessonName);
            setTiming(toCopy.timing);
            setPrice(toCopy.price);
            setCapacity(toCopy.capacity);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(lessonName, timing, price, capacity);
        }

        public void setLessonName(LessonName lessonName) {
            this.lessonName = lessonName;
        }

        public Optional<LessonName> getLessonName() {
            return Optional.ofNullable(lessonName);
        }

        public void setTiming(Timing timing) {
            this.timing = timing;
        }

        public Optional<Timing> getTiming() {
            return Optional.ofNullable(timing);
        }

        public void setPrice(Price price) {
            this.price = price;
        }

        public Optional<Price> getPrice() {
            return Optional.ofNullable(price);
        }

        public void setCapacity(Capacity capacity) {
            this.capacity = capacity;
        }

        public Optional<Capacity> getCapacity() {
            return Optional.ofNullable(capacity);
        }

        /**
         * Returns true if both descriptors represent lessons that have the same identity and data fields.
         * This defines a stronger notion of equality between two lessons.
         */
        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            if (!(other instanceof EditLessonDescriptor)) {
                return false;
            }

            EditLessonDescriptor otherDescriptor = (EditLessonDescriptor) other;
            return otherDescriptor.getLessonName().equals(getLessonName())
                    && otherDescriptor.getTiming().equals(getTiming())
                    && otherDescriptor.getPrice().equals(getPrice())
                    && otherDescriptor.getCapacity().equals(getCapacity());
        }
    }


}
