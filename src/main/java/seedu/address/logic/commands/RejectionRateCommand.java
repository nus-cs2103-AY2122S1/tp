package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.address.model.Calculator.INVALID_REJECTION_RATE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.position.Position;

/**
 * Returns the rejection rate of a given position to the user.
 */
public class RejectionRateCommand extends Command {


    public static final String COMMAND_WORD = "rate-position";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Returns the rejection rate of position specified.\n"
            + "Parameters: "
            + PREFIX_POSITION + "POSITION "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_POSITION + "software engineer";

    public static final String MESSAGE_NO_SUCH_POSITION = "There is no such position in MrTechRecruiter";

    public static final String MESSAGE_NO_CURRENT_APPLICANTS = "There are currently no applicants for this position.";

    private static float rejectionRate;

    public static final String MESSAGE_SUCCESS = "Rejection rate for %1$s = " + rejectionRate;

    private final Position position;

    /**
     * Creates an RejectionRateCommand to get the specified rejection rate.
     */
    public RejectionRateCommand(Position dummyPosition) {
        this.position = dummyPosition;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasPosition(position)) {
            throw new CommandException(MESSAGE_NO_SUCH_POSITION);
        }

        rejectionRate = model.calculateRejectionRate(position);

        if (rejectionRate == INVALID_REJECTION_RATE) {
            return new CommandResult(MESSAGE_NO_CURRENT_APPLICANTS);
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
