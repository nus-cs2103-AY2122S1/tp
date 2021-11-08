package seedu.track2gather.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.track2gather.commons.core.Messages.MESSAGE_PREDICATE_SHOW_NON_CALLED;
import static seedu.track2gather.model.Model.PREDICATE_SHOW_NON_CALLED;

import seedu.track2gather.model.Model;

/**
 * Displays a filtered list of all persons who have not been called in the current SHN enforcement session.
 */
public class ScheduleCommand extends Command {
    public static final String COMMAND_WORD = "schedule";
    public static final String MESSAGE_SUCCESS = MESSAGE_PREDICATE_SHOW_NON_CALLED;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_NON_CALLED);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
