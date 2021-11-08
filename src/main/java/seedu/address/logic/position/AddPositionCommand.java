package seedu.address.logic.position;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.address.logic.Command;
import seedu.address.logic.CommandResult;
import seedu.address.logic.candidate.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.position.Position;

public class AddPositionCommand extends Command {

    public static final String COMMAND_WORD = "add_p";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a position to the HR Manager.\n"
            + "Parameters: "
            + PREFIX_TITLE + "TITLE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TITLE + "Accountant";

    public static final String MESSAGE_SUCCESS = "New position added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This position already exists in the HR Manager";

    private final Position toAdd;

    /**
     * Creates an AddPositionCommand to add the specified {@code Person}
     */
    public AddPositionCommand(Position position) {
        requireNonNull(position);
        toAdd = position;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPosition(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPosition(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), CommandResult.CommandType.POSITION);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPositionCommand // instanceof handles nulls
                && toAdd.equals(((AddPositionCommand) other).toAdd));
    }



}
