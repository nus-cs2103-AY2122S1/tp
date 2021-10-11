package tutoraid.logic.commands;

import static java.util.Objects.requireNonNull;

import tutoraid.model.StudentBook;
import tutoraid.model.Model;

/**
 * Clears TutorAid entries.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "TutorAid has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setStudentBook(new StudentBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
