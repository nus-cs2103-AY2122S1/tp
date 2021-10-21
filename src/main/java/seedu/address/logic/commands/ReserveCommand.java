package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.time.LocalDateTime;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Phone;
import seedu.address.model.reservation.Reservation;
import seedu.address.model.reservation.ReservationsManager;
import seedu.address.model.reservation.exception.ReservationException;
import seedu.address.model.table.Table;

/**
 * Represents the command to add reservation
 */
public class ReserveCommand extends Command {
    public static final String COMMAND_WORD = "reserve";
    public static final String MESSAGE_USAGE = String.format(
            "%1$s: add a new reservation with customer's phone number, number of people and time.\n"
            + "Parameters: NUMBER_OF_PEOPLE %2$sPHONE (must be a positive integer) %3$sTIME\n"
            + "Example: %1$s 2 %2$s98765432 %3$s2021-12-24 1930.",
            COMMAND_WORD,
            PREFIX_PHONE, PREFIX_TIME
    );
    public static final String MESSAGE_SUCCESS = "New reservation added: %1$s";
    public static final String MESSAGE_RESERVATION_EXISTS = "Reservation already exist: %1$s";
    public static final String MESSAGE_CUSTOMER_MISSING =
            "No customer with phone number %1$s exist.\nUnable to create reservation.";

    private Phone phone;
    private int numberOfPeople;
    private LocalDateTime dateTime;

    /**
     * Creates a command to add a reservation
     */
    public ReserveCommand(Phone phone, int numberOfPeople, LocalDateTime dateTime) {
        requireAllNonNull(phone, dateTime);
        this.phone = phone;
        this.numberOfPeople = numberOfPeople;
        this.dateTime = dateTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.hasCustomerWithPhone(phone)) {
            throw new CommandException(String.format(MESSAGE_CUSTOMER_MISSING, phone));
        }
        ReservationsManager reservationsManager = model.getReservationsManager();
        try {
            Table tableToBeAssigned = reservationsManager.getAvailableTable(model, numberOfPeople, dateTime);
            Reservation reservation = new Reservation(phone, numberOfPeople, dateTime, tableToBeAssigned);
            if (model.hasReservation(reservation)) {
                throw new CommandException(String.format(MESSAGE_RESERVATION_EXISTS, reservation));
            }
            model.addReservation(reservation);
            return new CommandResult(String.format(MESSAGE_SUCCESS, reservation), false, false, false, false,
                    false, true);
        } catch (ReservationException e) {
            throw new CommandException(String.format(e.getMessage(), dateTime));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReserveCommand)) {
            return false;
        }

        ReserveCommand that = (ReserveCommand) o;
        return phone.equals(that.phone)
                && numberOfPeople == that.numberOfPeople
                && dateTime.equals(that.dateTime);
    }
}
