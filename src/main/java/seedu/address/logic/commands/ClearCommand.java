package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.EzFoodie;
import seedu.address.model.Model;

/**
 * Clears the ezFoodie.
 */
public class ClearCommand extends Command {

    /**
     * Stands for clean command word.
     */
    public static final String COMMAND_WORD = "clear";

    /**
     * Stands for success message for clear command.
     */
    public static final String MESSAGE_SUCCESS = "ezFoodie has been cleared!";


    /**
     * Executes the model in the clear command.
     *
     * @param model {@code Model} which the command should operate on.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setEzFoodie(new EzFoodie());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
