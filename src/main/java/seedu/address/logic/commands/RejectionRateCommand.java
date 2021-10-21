package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;

/**
 * Returns the rejection rate of a given position to the user.
 */
public class RejectionRateCommand extends Command {

    public static final String COMMAND_WORD = "rate";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Returns the rejection rate of position specified.\n"
            + "Parameters: "
            + PREFIX_POSITION + "POSITION \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_POSITION + "software engineer";

    public static final String MESSAGE_SUCCESS = "Rejection rate for %1$s = %2$d%%";

    public static final String MESSAGE_NO_SUCH_POSITION = "There is no such position in MrTechRecruiter";

    public static final String MESSAGE_NO_CURRENT_APPLICANTS = "There are currently no applicants for this position.";

    private int rejectionRate;

    private final Position toAdd;

    /**
     * Creates an RejectionRateCommand to get the specified rejection rate.
     */
    public RejectionRateCommand(Position dummyPosition) {
        this.toAdd = dummyPosition;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasPosition(toAdd)) {
            throw new CommandException(MESSAGE_NO_SUCH_POSITION);
        }

        rejectionRate = model.calculateRejectionRate(toAdd);

        if (rejectionRate == -1) {
            return new CommandResult(MESSAGE_NO_CURRENT_APPLICANTS);
        }

        Title positionName = toAdd.getTitle();

        return new CommandResult(String.format(MESSAGE_SUCCESS, positionName, rejectionRate));
    }
}
