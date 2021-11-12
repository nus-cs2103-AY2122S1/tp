package seedu.teachbook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.teachbook.commons.core.Messages.MESSAGE_DUPLICATE_STUDENT;
import static seedu.teachbook.commons.core.index.DefaultIndices.INDEX_LIST_ALL;
import static seedu.teachbook.commons.core.index.DefaultIndices.INDEX_NO_CLASS;
import static seedu.teachbook.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.teachbook.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.teachbook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.teachbook.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.teachbook.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.teachbook.logic.commands.exceptions.CommandException;
import seedu.teachbook.model.Model;
import seedu.teachbook.model.student.Student;

/**
 * Adds a student to the TeachBook.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student to the currently selected class.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + "[" + PREFIX_PHONE + "PHONE_NUMBER] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Jane Doe "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "janedoe@example.com "
            + PREFIX_ADDRESS + "21 Lower Kent Ridge Road, Singapore 119077 "
            + PREFIX_TAG + "class monitor ";

    public static final String MESSAGE_ADD_STUDENT_SUCCESS = "New student added: %1$s";
    public static final String MESSAGE_NO_CLASS = "Add a class before adding any student";
    public static final String MESSAGE_LIST_ALL = "Select a class before adding any student";

    private final Student studentToAdd;

    /**
     * Creates an AddCommand to add the specified {@code Student}.
     *
     * @param studentToAdd student to be added to the TeachBook.
     */
    public AddCommand(Student studentToAdd) {
        requireNonNull(studentToAdd);
        this.studentToAdd = studentToAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.getCurrentlySelectedClassIndex().equals(INDEX_NO_CLASS)) {
            throw new CommandException(MESSAGE_NO_CLASS);
        }
        if (model.getCurrentlySelectedClassIndex().equals(INDEX_LIST_ALL)) {
            throw new CommandException(MESSAGE_LIST_ALL);
        }

        model.setClassForStudent(studentToAdd);

        if (model.hasStudent(studentToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        model.addStudent(studentToAdd);
        model.commitTeachBook();

        return new CommandResult(String.format(MESSAGE_ADD_STUDENT_SUCCESS, studentToAdd), false, false,
                true, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && studentToAdd.equals(((AddCommand) other).studentToAdd));
    }
}
