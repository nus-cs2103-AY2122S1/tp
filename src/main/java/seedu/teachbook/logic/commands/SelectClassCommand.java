package seedu.teachbook.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.teachbook.commons.core.Messages;
import seedu.teachbook.commons.core.index.GeneralIndex;
import seedu.teachbook.logic.commands.exceptions.CommandException;
import seedu.teachbook.model.Model;
import seedu.teachbook.model.classobject.ClassNameDescriptor;
import seedu.teachbook.model.classobject.exceptions.NoClassWithNameException;

/**
 * Select a class identified using its name, after which UI will display the student list of the selected class
 * on the student list panel, and operations can be performed on the displayed student list.
 * The class being selected cannot be selected again.
 * {@code AddCommand} and {@code EditClassCommand} can only execute when there is a class being selected.
 *
 */
public class SelectClassCommand extends Command {

    public static final String COMMAND_WORD = "select";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Navigates to the class "
            + "with the specified class name (case-sensitive).\n"
            + "Parameter: CLASS_NAME (case-sensitive)\n"
            + "Example: " + COMMAND_WORD + " 4E2";

    public static final String MESSAGE_SELECT_CLASS_SUCCESS = "Currently displaying all students from Class: %1$s";
    public static final String MESSAGE_CLASS_ALREADY_SELECTED = "Already displaying students from Class: %1$s\n"
            + "Use \"list\" to display the full list.";

    private final ClassNameDescriptor newClassName;

    /**
     * Creates a {@code SelectClassCommand} to select the class whose name is described by the specified
     * {@code ClassNameDescriptor}.
     *
     * @param newClassName class name descriptor of the class to be selected.
     */
    public SelectClassCommand(ClassNameDescriptor newClassName) {
        this.newClassName = newClassName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        GeneralIndex newClassIndex;

        try {
            newClassIndex = model.getIndexOfClass(newClassName);
        } catch (NoClassWithNameException exception) {
            throw new CommandException(Messages.MESSAGE_CLASS_DOES_NOT_EXIST);
        }

        if (model.getIndexOfClass(newClassName).equals(model.getCurrentlySelectedClassIndex())) {
            throw new CommandException(String.format(MESSAGE_CLASS_ALREADY_SELECTED, newClassName));
        }
        model.updateCurrentlySelectedClass(newClassIndex);
        model.commitTeachBook();
        return new CommandResult(String.format(MESSAGE_SELECT_CLASS_SUCCESS, newClassName),
                false, false, true, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SelectClassCommand // instanceof handles nulls
                && newClassName.equals(((SelectClassCommand) other).newClassName)); // state check
    }
}
