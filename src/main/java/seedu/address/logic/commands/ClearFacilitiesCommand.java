package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Clears the facility list of SportsPA.
 */
public class ClearFacilitiesCommand extends Command {

    public static final String COMMAND_WORD = "clearf";
    public static final String MESSAGE_SUCCESS = "Facility list has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.resetFacilityList();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
