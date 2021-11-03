package seedu.fast.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.fast.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.logging.Logger;

import seedu.fast.commons.core.LogsCenter;
import seedu.fast.model.Model;

/**
 * Lists all persons in FAST to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Displays all your clients saved in FAST. This command can be used to return to the whole client "
        + "list after a 'find' or 'sort'. \n\n"
        + "Example: \n" + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Listed all clients";

    private final Logger logger = LogsCenter.getLogger(getClass());

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        logger.info("-----List Command: Success-----");

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
