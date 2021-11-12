package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CAPACITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.facility.Facility;

/**
 * Adds a facility to SportsPA.
 */
public class AddFacilityCommand extends Command {
    public static final String COMMAND_WORD = "addf";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a facility to SportsPA.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_LOCATION + "LOCATION "
            + PREFIX_TIME + "TIME "
            + PREFIX_CAPACITY + "CAPACITY\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Court 1 "
            + PREFIX_LOCATION + "University Sports Hall "
            + PREFIX_TIME + "1130 "
            + PREFIX_CAPACITY + "5 ";
    public static final String MESSAGE_SUCCESS = "New facility added: %1$s";
    public static final String MESSAGE_DUPLICATE_FACILITY = "This facility already exists in SportsPA.";

    private final Facility facility;

    /**
     * Creates an AddFacilityCommand to add the specified facility.
     *
     * @param facility Facility to be added.
     */
    public AddFacilityCommand(Facility facility) {
        requireNonNull(facility);
        this.facility = facility;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasFacility(facility)) {
            throw new CommandException(MESSAGE_DUPLICATE_FACILITY);
        }

        model.addFacility(facility);
        return new CommandResult(String.format(MESSAGE_SUCCESS, facility),
                false, true, false);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj == this)
                || (obj instanceof AddFacilityCommand
                && facility.equals(((AddFacilityCommand) obj).facility));
    }
}
