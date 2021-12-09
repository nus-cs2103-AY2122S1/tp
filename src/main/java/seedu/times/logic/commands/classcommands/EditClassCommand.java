package seedu.times.logic.commands.classcommands;

import static java.util.Objects.requireNonNull;
import static seedu.times.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.times.logic.parser.CliSyntax.PREFIX_CLASSTIMING;
import static seedu.times.logic.parser.CliSyntax.PREFIX_CLASS_NAME;
import static seedu.times.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.times.logic.parser.CliSyntax.PREFIX_RATE;

import java.util.List;
import java.util.Optional;

import seedu.times.commons.core.Messages;
import seedu.times.commons.core.index.Index;
import seedu.times.commons.util.CollectionUtil;
import seedu.times.logic.commands.Command;
import seedu.times.logic.commands.CommandResult;
import seedu.times.logic.commands.exceptions.CommandException;
import seedu.times.model.Model;
import seedu.times.model.tuitionclass.ClassName;
import seedu.times.model.tuitionclass.ClassTiming;
import seedu.times.model.tuitionclass.Location;
import seedu.times.model.tuitionclass.Rate;
import seedu.times.model.tuitionclass.StudentNameList;
import seedu.times.model.tuitionclass.TuitionClass;
import seedu.times.model.tuitionclass.exceptions.DuplicateClassException;
import seedu.times.model.tuitionclass.exceptions.OverlappingClassException;
import seedu.times.ui.TabName;

/**
 * Edits a class in Timestable.
 */
public class EditClassCommand extends Command {

    public static final String COMMAND_WORD = "editclass";

    public static final String MESSAGE_EDIT_CLASS_SUCCESS = "Edited class: %1$s";
    public static final String MESSAGE_NO_FIELD_PROVIDED = "At least one field to editclass must be provided.";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in this class:\n";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the class identified "
            + "by the index number used in the displayed person list "
            + "(adding or removing students is done with separate commands!)\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_CLASS_NAME + "CLASS NAME] "
            + "[" + PREFIX_CLASSTIMING + "CLASS TIMING] "
            + "[" + PREFIX_RATE + "RATE] "
            + "[" + PREFIX_LOCATION + "LOCATION]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_CLASSTIMING + "MON 13:30-15:30 "
            + PREFIX_RATE + "100";


    private final Index index;
    private final EditClassDescriptor editClassDescriptor;

    /**
     * Constructs a new EditClassCommand.
     *
     * @param index of class to be edited.
     * @param editClassDescriptor descriptor of new class.
     */
    public EditClassCommand(Index index, EditClassDescriptor editClassDescriptor) {
        requireAllNonNull(index, editClassDescriptor);
        this.index = index;
        this.editClassDescriptor = editClassDescriptor;
    }

    /**
     * Creates EditedClass.
     *
     * @param classToEdit to be editted.
     * @param editClassDescriptor to replace.
     * @return edited TuitionClass.
     */
    public static TuitionClass createEditedClass(TuitionClass classToEdit, EditClassDescriptor editClassDescriptor) {
        assert classToEdit != null;

        ClassName className = editClassDescriptor.getClassName().orElse(classToEdit.getClassName());
        ClassTiming classTiming = editClassDescriptor.getClassTiming().orElse(classToEdit.getClassTiming());
        Location location = editClassDescriptor.getLocation().orElse(classToEdit.getLocation());
        Rate rate = editClassDescriptor.getRate().orElse(classToEdit.getRate());
        StudentNameList studentNameList = editClassDescriptor.getStudentList().orElse(classToEdit.getStudentList());

        return new TuitionClass(className, classTiming, location, rate, studentNameList);

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<TuitionClass> lastShownClassList = model.getFilteredTuitionClassList();
        if (index.getZeroBased() >= lastShownClassList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLASS_DISPLAYED_INDEX);
        }

        TuitionClass classToEdit = lastShownClassList.get(index.getZeroBased());
        TuitionClass editedClass = createEditedClass(classToEdit, editClassDescriptor);

        try {
            model.setClass(classToEdit, editedClass);
            model.updateFilteredClassList(Model.PREDICATE_SHOW_ALL_CLASS);
        } catch (OverlappingClassException | DuplicateClassException e) {
            throw new CommandException(e.getMessage());
        }

        updateView(TabName.CLASSES);
        return new CommandResult(String.format(MESSAGE_EDIT_CLASS_SUCCESS, editedClass));
    }

    /**
     * Stores the names of the student and the class involved in an addToClassCommand.
     */
    public static class EditClassDescriptor {
        private ClassName className;
        private ClassTiming classTiming;
        private Location location;
        private Rate rate;
        private StudentNameList studentNameList;

        public EditClassDescriptor() {
        }

        /**
         * Constructs a copy of the given EditClassDescriptor.
         *
         * @param toCopy The EditClassDescriptor to copy.
         */
        public EditClassDescriptor(EditClassDescriptor toCopy) {
            setClassName(toCopy.className);
            setClassTiming(toCopy.classTiming);
            setLocation(toCopy.location);
            setRate(toCopy.rate);
            setStudentList(toCopy.studentNameList);
        }

        public Optional<ClassName> getClassName() {
            return Optional.ofNullable(className);
        }

        public void setClassName(ClassName className) {
            this.className = className;
        }

        public Optional<ClassTiming> getClassTiming() {
            return Optional.ofNullable(classTiming);
        }

        public void setClassTiming(ClassTiming classTiming) {
            this.classTiming = classTiming;
        }

        public Optional<Location> getLocation() {
            return Optional.ofNullable(location);
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public Optional<Rate> getRate() {
            return Optional.ofNullable(rate);
        }

        public void setRate(Rate rate) {
            this.rate = rate;
        }

        public Optional<StudentNameList> getStudentList() {
            return Optional.ofNullable(studentNameList);
        }

        public void setStudentList(StudentNameList studentNameList) {
            this.studentNameList = studentNameList;
        }

        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(className, classTiming, rate, location, studentNameList);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            if (!(other instanceof EditClassDescriptor)) {
                return false;
            }

            EditClassDescriptor e = (EditClassDescriptor) other;
            return getClassName().equals(e.getClassName())
                    && getClassTiming().equals(e.getClassTiming())
                    && getRate().equals(e.getRate())
                    && getLocation().equals(e.getLocation())
                    && getStudentList().equals(e.getStudentList());
        }
    }

    @Override
    public boolean equals(Object o) {
        return this == o
                || ((o instanceof EditClassCommand)
                    && index.equals(((EditClassCommand) o).index)
                    && editClassDescriptor.equals(((EditClassCommand) o).editClassDescriptor));
    }
}
