package seedu.address.logic.commands.modulelesson;


import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_LESSONS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.modulelesson.LessonDay;
import seedu.address.model.modulelesson.LessonTime;
import seedu.address.model.modulelesson.ModuleLesson;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Remark;

/**
 * Edits the details of an existing lesson in contHACKS.
 */
public class EditModuleLessonCommand extends Command {

    public static final String MESSAGE_USAGE = "editc: Edits the details of the lesson identified "
            + "by the index number used in the displayed lesson list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_MODULE_CODE + "MODULE_CODE LESSON_CODE] "
            + "[" + PREFIX_LESSON_DAY + "DAY] "
            + "[" + PREFIX_LESSON_TIME + "START_TIME END_TIME] "
            + "[" + PREFIX_REMARK + "REMARK]\n"
            + "Example: edit 1 "
            + PREFIX_MODULE_CODE + "CS2103T T09 "
            + PREFIX_LESSON_DAY + "2 "
            + PREFIX_LESSON_TIME + "10:00 12:00";

    public static final String MESSAGE_EDIT_LESSON_SUCCESS = "Edited lesson: %1$s";
    public static final String MESSAGE_DUPLICATE_LESSON = "Unable to add: A lesson with the same module code and"
            + " lesson code already exists in contHACKS";
    public static final String MESSAGE_OVERLAPPING_LESSON = "Warning: Another lesson with overlapping timings exists.\n"
            + "Edited lesson: %1$s";
    public static final String MESSAGE_NO_FIELD_PROVIDED = "At least one field to edit must be provided.";
    public static final String MESSAGE_NO_LESSON_CODE_PROVIDED = "Lesson code is compulsory.";
    public static final String MESSAGE_MORE_THAN_ONE_LESSON_CODE = "One Lesson should only have one Lesson Code.";

    private final Index index;
    private final EditLessonDescriptor editLessonDescriptor;

    /**
     * @param index of the lesson in the filtered lesson list to edit
     * @param editLessonDescriptor details to edit the lesson with
     */
    public EditModuleLessonCommand(Index index, EditLessonDescriptor editLessonDescriptor) {
        requireNonNull(index);
        requireNonNull(editLessonDescriptor);

        this.index = index;
        this.editLessonDescriptor = new EditLessonDescriptor(editLessonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<ModuleLesson> lastShownList = model.getFilteredModuleLessonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_LESSON_DISPLAYED_INDEX);
        }

        ModuleLesson lessonToEdit = lastShownList.get(index.getZeroBased());
        ModuleLesson editedModuleLesson = createEditedLesson(lessonToEdit, editLessonDescriptor);

        if (!lessonToEdit.isSameModuleLesson(editedModuleLesson) && model.hasModuleLesson(editedModuleLesson)) {
            throw new CommandException(MESSAGE_DUPLICATE_LESSON);
        }

        CommandResult result;

        // delete such that it won't be checking against itself
        model.deleteLesson(lessonToEdit);
        if (model.hasModuleLessonClashingWith(editedModuleLesson)) {
            result = new CommandResult(String.format(MESSAGE_OVERLAPPING_LESSON, editedModuleLesson));
            model.addLesson(lessonToEdit);
        } else {
            result = new CommandResult(String.format(MESSAGE_EDIT_LESSON_SUCCESS, editedModuleLesson));
        }

        model.setModuleLesson(lessonToEdit, editedModuleLesson);
        model.updateFilteredModuleLessonList(PREDICATE_SHOW_ALL_LESSONS);
        return result;
    }

    /**
     * Creates and returns a {@code ModuleLesson} with the details of {@code moduleLessonToEdit}
     * edited with {@code editLessonDescriptor}.
     */
    private static ModuleLesson createEditedLesson(ModuleLesson moduleLessonToEdit,
                                                   EditLessonDescriptor editLessonDescriptor) {
        assert moduleLessonToEdit != null;

        ModuleCode updatedModuleCode = editLessonDescriptor.getModuleCode()
                .orElse(moduleLessonToEdit.getModuleCode());
        LessonDay updatedLessonDay = editLessonDescriptor.getLessonDay().orElse(moduleLessonToEdit.getDay());
        LessonTime updatedLessonStartTime = editLessonDescriptor.getLessonStartTime()
                .orElse(moduleLessonToEdit.getLessonStartTime());
        LessonTime updatedLessonEndTime = editLessonDescriptor.getLessonEndTime()
                .orElse(moduleLessonToEdit.getLessonEndTime());
        Remark updatedRemark = editLessonDescriptor.getRemark().orElse(moduleLessonToEdit.getRemark());

        return new ModuleLesson(updatedModuleCode, updatedLessonDay,
                updatedLessonStartTime, updatedLessonEndTime, updatedRemark);
    }

    @Override
    public boolean equals(Object o) {
        // short circuit if same object
        if (o == this) {
            return true;
        }

        // instanceof handles null
        if (!(o instanceof EditModuleLessonCommand)) {
            return false;
        }

        // state check
        EditModuleLessonCommand c = (EditModuleLessonCommand) o;
        return index.equals(c.index)
                && editLessonDescriptor.equals(c.editLessonDescriptor);
    }
    /**
     * Stores the details to edit the lesson with. Each non-empty field
     * value will replace the corresponding field value of the lesson.
     */
    public static class EditLessonDescriptor {
        private ModuleCode code;
        private LessonDay day;
        private LessonTime startTime;
        private LessonTime endTime;
        private Remark remark;

        public EditLessonDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditLessonDescriptor(EditLessonDescriptor toCopy) {
            setModuleCode(toCopy.code);
            setLessonDay(toCopy.day);
            setLessonStartTime(toCopy.startTime);
            setLessonEndTime(toCopy.endTime);
            setRemark(toCopy.remark);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(code, day, startTime, endTime, remark);
        }

        public void setModuleCode(ModuleCode code) {
            this.code = code;
        }

        public Optional<ModuleCode> getModuleCode() {
            return Optional.ofNullable(code);
        }

        public void setLessonDay(LessonDay day) {
            this.day = day;
        }

        public Optional<LessonDay> getLessonDay() {
            return Optional.ofNullable(day);
        }

        public void setLessonStartTime(LessonTime startTime) {
            this.startTime = startTime;
        }

        public Optional<LessonTime> getLessonStartTime() {
            return Optional.ofNullable(startTime);
        }

        public void setLessonEndTime(LessonTime endTime) {
            this.endTime = endTime;
        }

        public Optional<LessonTime> getLessonEndTime() {
            return Optional.ofNullable(endTime);
        }

        public void setRemark(Remark remark) {
            this.remark = remark;
        }

        public Optional<Remark> getRemark() {
            return Optional.ofNullable(remark);
        }

        @Override
        public boolean equals(Object o) {
            // short circuit if same object
            if (o == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(o instanceof EditLessonDescriptor)) {
                return false;
            }

            // state check
            EditLessonDescriptor e = (EditLessonDescriptor) o;

            return getModuleCode().equals(e.getModuleCode())
                    && getLessonDay().equals(e.getLessonDay())
                    && getLessonStartTime().equals(e.getLessonStartTime())
                    && getLessonEndTime().equals(e.getLessonEndTime())
                    && getRemark().equals(e.getRemark());
        }
    }
}
