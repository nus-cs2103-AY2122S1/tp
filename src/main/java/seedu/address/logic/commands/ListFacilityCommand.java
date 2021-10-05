package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_FACILITIES;

import seedu.address.model.Model;

/**
 * Lists all facilities in SportsPA to the user.
 */
public class ListFacilityCommand extends Command {

    public static final String COMMAND_WORD = "listf";

    public static final String MESSAGE_SUCCESS = "Listed all facilities";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredFacilityList(PREDICATE_SHOW_ALL_FACILITIES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
