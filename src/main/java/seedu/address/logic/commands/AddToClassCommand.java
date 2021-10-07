package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUITION_CLASS;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.tuition.TuitionClass;

public class AddToClassCommand extends Command {
    public static final String COMMAND_WORD = "addtoclass";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add students to existing class. "
            + "Parameters: "
            + PREFIX_STUDENT + "STUDENT_INDEX"
            + PREFIX_TUITION_CLASS + "CLASS_INDEX";
    public static final String MESSAGE_SUCCESS = "New student %1$s is added to class: %2$s";
    private static final String MESSAGE_STUDENT_EXISTS = "Student %1$s is already in the class";
    private static final String MESSAGE_STUDENT_NOT_FOUND = "This student is not found.";
    private static final String MESSAGE_CLASS_NOT_FOUND = "This tuition class is not found.";
    private static final String MESSAGE_CLASS_IS_FULL = "Cannot add student as the class limit has been exceeded.";
    private Index studentIndex;
    private Index classIndex;

    /**
     * Constructor for AddToClass command.
     * @param studentIndex index of student to be added.
     * @param classIndex index of class to be added to.
     */
    public AddToClassCommand(Index studentIndex, Index classIndex) {
        this.classIndex = classIndex;
        this.studentIndex = studentIndex;
    }

    /**
     * Adds an existing student to an existing class.
     * @param model {@code Model} which the command should operate on.
     * @return a CommandResult to be shown to users.
     * @throws CommandException if the student or class is not found, or the student is in the class already.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person studentToAdd = model.getStudent(studentIndex);
        Person studentToChange = model.getStudent(studentIndex);
        TuitionClass tuitionClass = model.getTuitionClass(classIndex);

        if (studentToAdd == null) {
            throw new CommandException(MESSAGE_STUDENT_NOT_FOUND);
        }

        if (tuitionClass == null) {
            throw new CommandException(MESSAGE_CLASS_NOT_FOUND);
        }
        boolean isClassFull = tuitionClass.isFull();
        if (isClassFull) {
            throw new CommandException(MESSAGE_CLASS_IS_FULL);
        }

        TuitionClass modifiedClass = model.addToClass(tuitionClass, studentToAdd);
        if (modifiedClass == null) {
            throw new CommandException(String.format(MESSAGE_STUDENT_EXISTS, studentToAdd.getName().toString()));
        }
        studentToAdd.addClass(modifiedClass);
        studentToAdd.addTag(new Tag(modifiedClass.getName().getName()));
        updateModel(model, tuitionClass, modifiedClass, studentToAdd, studentToChange);
        return new CommandResult(String.format(MESSAGE_SUCCESS, studentToAdd.getName().fullName, modifiedClass));
    }

    private void updateModel(Model model, TuitionClass tuitionClass,
                             TuitionClass modifiedClass, Person studentToAdd, Person studentToChange) {
        model.updateTuitionClassInPersonObject(modifiedClass);
        model.setPerson(studentToChange, studentToAdd);
        model.setTuition(tuitionClass, modifiedClass);
    }
}
