package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APP_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDDATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTDATETIME;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.schedule.Appointment;
import seedu.address.model.schedule.TimePeriod;

public class EditAppCommand extends Command {

    public static final String COMMAND_WORD = "editApp";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits an existing appointment in PlaceBook. "
            + "Parameters: "
            + PREFIX_APP_INDEX + "APPOINTMENT INDEX "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_STARTDATETIME + "DATE (dd-MM-yyyy) TIME (HHmm) "
            + PREFIX_ENDDATETIME + "DATE (dd-MM-yyyy) TIME (HHmm) "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + "\nExample: " + COMMAND_WORD + " "
            + PREFIX_APP_INDEX + "1 "
            + PREFIX_ADDRESS + "Starbucks @ Raffles City "
            + PREFIX_STARTDATETIME + "14-12-2021 1600"
            + PREFIX_ENDDATETIME + "14-12-2021 1800"
            + PREFIX_DESCRIPTION + "discuss marketing strategies";

    public static final String MESSAGE_SUCCESS = "Appointment edited: %1$s";

    private final Index appIndex;
    private final Address location;
    private final TimePeriod timePeriod;
    private final String description;

    /**
     * Creates an EditAppCommand
     * @param location The location of the appointment
     * @param timePeriod The time period of the appointment
     * @param description The description of the appointment
     */
    public EditAppCommand(Index appIndex, Address location, TimePeriod timePeriod,
                          String description) {
        requireNonNull(appIndex);
        requireNonNull(location);
        requireNonNull(timePeriod);
        requireNonNull(description);

        this.appIndex = appIndex;
        this.location = location;
        this.timePeriod = timePeriod;
        this.description = description;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Appointment> lastShownList = model.getFilteredAppointmentList();

        if (appIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment appointmentToEdit = lastShownList.get(appIndex.getZeroBased());
        Appointment editedAppointment =
                new Appointment(appointmentToEdit.getClients(), location, timePeriod, description);
        if (!model.getClashingAppointments(editedAppointment).isEmpty()) {
            String clashingAppointments = model.getClashingAppointmentsAsString(editedAppointment);
            throw new CommandException(Messages.MESSAGE_APPOINTMENTS_DUPLICATE_APPOINTMENT_ADDED
                + '\n' + clashingAppointments);
        }
        model.deleteAppointment(appointmentToEdit);
        model.addAppointment(editedAppointment);
        model.updateState();

        return new CommandResult(String.format(MESSAGE_SUCCESS, appointmentToEdit));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditAppCommand)) {
            return false;
        }

        EditAppCommand ea = (EditAppCommand) other;
        return this.appIndex.equals(ea.appIndex)
                && this.location.equals(ea.location)
                && this.timePeriod.equals(ea.timePeriod)
                && this.description.equals(ea.description);
    }
}
