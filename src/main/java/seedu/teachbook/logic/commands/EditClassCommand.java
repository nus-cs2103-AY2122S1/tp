package seedu.teachbook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.teachbook.commons.core.Messages.MESSAGE_DUPLICATE_CLASS;
import static seedu.teachbook.commons.core.index.DefaultIndices.INDEX_LIST_ALL;
import static seedu.teachbook.commons.core.index.DefaultIndices.INDEX_NO_CLASS;

import seedu.teachbook.logic.commands.exceptions.CommandException;
import seedu.teachbook.model.Model;
import seedu.teachbook.model.classobject.Class;
import seedu.teachbook.model.classobject.ClassName;

/**
 * Edits the name of the curently selected class. Students present in the class remains unchanged.
 */
public class EditClassCommand extends Command {

    public static final String COMMAND_WORD = "editClass";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the name of the currently selected class.\n"
            + "Parameter: CLASS_NAME (case-sensitive)\n"
            + "Example: " + COMMAND_WORD + " 4E5";

    public static final String MESSAGE_SUCCESS = "Edited Class: %1$s";
    public static final String MESSAGE_NO_CLASS_SELECTED = "Select a class before editing class!";

    private final ClassName updatedClassName;

    public EditClassCommand(ClassName updatedClassName) {
        this.updatedClassName = updatedClassName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.getCurrentlySelectedClassIndex().equals(INDEX_NO_CLASS)
                || model.getCurrentlySelectedClassIndex().equals(INDEX_LIST_ALL)) {
            throw new CommandException(MESSAGE_NO_CLASS_SELECTED);
        }

        Class newClass = new Class(updatedClassName);
        if (model.hasClass(newClass)) { // simply comparing class name
            throw new CommandException(MESSAGE_DUPLICATE_CLASS);
        }

        model.setClassName(updatedClassName);
        model.commitTeachBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, updatedClassName));
    }
}
