package safeforhall.logic.commands.add;

import static java.util.Objects.requireNonNull;

import safeforhall.logic.commands.Command;
import safeforhall.logic.commands.CommandResult;
import safeforhall.logic.commands.exceptions.CommandException;
import safeforhall.logic.parser.CliSyntax;
import safeforhall.model.Model;
import safeforhall.model.event.Event;

public class AddEventCommand extends Command {
    public static final String COMMAND_WORD = "add";

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
     * Creates an AddEventCommand to add the specified {@code Event}
     */
    public AddEventCommand(Event event) {
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
                || (other instanceof AddEventCommand // instanceof handles nulls
                && toAdd.equals(((AddEventCommand) other).toAdd));
    }
}
