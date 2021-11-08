package seedu.track2gather.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.track2gather.commons.core.Messages.MESSAGE_PREDICATE_SHOW_ALL_PERSONS;
import static seedu.track2gather.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.track2gather.model.Model;

/**
 * Shows a list of all persons.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = MESSAGE_PREDICATE_SHOW_ALL_PERSONS;


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
