package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tuition.TuitionClass;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

public class AddToClassCommand extends Command{
    public static final String COMMAND_WORD = "addtoclass";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add students to existing class. "
            + "Parameters: "
            + PREFIX_STUDENT + "STUDENT_INDEX"
            + PREFIX_TUITION_CLASS + "CLASS_INDEX";
    private static final String MESSAGE_STUDENT_NOT_FOUND = "This student is not found.";
    private static final String MESSAGE_CLASS_NOT_FOUND = "This tuition class is not found.";
    public static final String MESSAGE_SUCCESS = "New student %1$s is added to class: %2$s";
    private static final String MESSAGE_STUDENT_EXISTS = "Student %1$s is already in the class";

    private Index studentIndex, classIndex;

    public AddToClassCommand(Index studentIndex, Index classIndex) {
        this.classIndex = classIndex;
        this.studentIndex = studentIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person studentToAdd = model.getStudentIndex(studentIndex);
        TuitionClass tuitionClass = model.getTuitionClassIndex(classIndex);

        if (studentToAdd == null) {
            throw new CommandException(MESSAGE_STUDENT_NOT_FOUND);
        }

        if (tuitionClass == null) {
            throw new CommandException(MESSAGE_CLASS_NOT_FOUND);
        }

        TuitionClass modifiedClass = model.addToClass(tuitionClass, studentToAdd);
        if (modifiedClass == null) {
            throw new CommandException(String.format(MESSAGE_STUDENT_EXISTS, studentToAdd));
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, studentToAdd.getName().fullName, modifiedClass));
    }
}
