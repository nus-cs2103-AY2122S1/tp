package safeforhall.logic.commands;

import safeforhall.logic.commands.exceptions.CommandException;
import safeforhall.logic.parser.CliSyntax;
import safeforhall.model.Model;
import safeforhall.model.event.Event;

import static java.util.Objects.requireNonNull;

public class EaddCommand extends Command {
    public static final String COMMAND_WORD = "eadd";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an event to the address book. "
            + "Parameters: "
            + CliSyntax.PREFIX_NAME + "NAME "
            + CliSyntax.PREFIX_DATE + "DATE "
            + CliSyntax.PREFIX_VENUE + "VENUE "
            + CliSyntax.PREFIX_CAPACITY + "CAPACITY "

            + "Example: " + COMMAND_WORD + " "
            + CliSyntax.PREFIX_NAME + "Football "
            + CliSyntax.PREFIX_DATE + "03-01-2021 "
            + CliSyntax.PREFIX_VENUE + "NUS field "
            + CliSyntax.PREFIX_CAPACITY + "5 ";

    public static final String MESSAGE_SUCCESS = "New event added: %1$s";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in the address book";

    private final Event toAdd;

    /**
     * Creates an EaddCommand to add the specified {@code Event}
     */
    public EaddCommand(Event event) {
        requireNonNull(event);
        toAdd = event;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasEvent(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }

        model.addEvent(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EaddCommand // instanceof handles nulls
                && toAdd.equals(((EaddCommand) other).toAdd));
    }
}
