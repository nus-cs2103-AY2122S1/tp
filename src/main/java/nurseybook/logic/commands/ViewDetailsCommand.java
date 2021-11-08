package nurseybook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import nurseybook.commons.core.Messages;
import nurseybook.commons.core.index.Index;
import nurseybook.logic.commands.exceptions.CommandException;
import nurseybook.model.Model;
import nurseybook.model.person.Elderly;

public class ViewDetailsCommand extends Command {
    public static final String COMMAND_WORD = "viewDetails";
    public static final String[] PARAMETERS = { Index.VALID_INDEX_CRITERIA };

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows the full details of the elderly identified by the index number"
            + " used in the displayed elderly list.\n"
            + "Parameters: "
            + String.join(" ", PARAMETERS)
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Showing %s's details";

    private final Index targetIndex;

    public ViewDetailsCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Elderly> lastShownList = model.getFilteredElderlyList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ELDERLY_DISPLAYED_INDEX);
        }

        Elderly elderlyOfInterest = lastShownList.get(targetIndex.getZeroBased());
        model.setElderlyOfInterest(elderlyOfInterest);
        return new CommandResult(String.format(MESSAGE_SUCCESS, elderlyOfInterest.getName()), true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewDetailsCommand // instanceof handles nulls
                && targetIndex.equals(((ViewDetailsCommand) other).targetIndex)); // state check
    }

}
