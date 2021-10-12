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

/**
 * Represents the command to add reservation
 */
public class ReserveCommand extends Command {
    public static final String COMMAND_WORD = "reserve";
    public static final String MESSAGE_USAGE = String.format(
            "%1$s: add a new reservation with customer's phone number, number of people and time.\n"
            + "Parameters: NUMBER_OF_PEOPLE %2$sPHONE (must be a positive integer) %3$sTIME\n"
            + "Example: %1$s 2 %2$s98765432 %3$s24/12/2021 1930.",
            COMMAND_WORD,
            PREFIX_PHONE, PREFIX_TIME
    );
    public static final String MESSAGE_SUCCESS = "New reservation added: %1$s";
    public static final String MESSAGE_UNSUCCESSFUL = "Reservation already exist: %1$s";

    private Phone phone;
    private int numberOfPeople;
    private LocalDateTime time;

    /**
     * Creates a command to add a reservation
     */
    public ReserveCommand(Phone phone, int numberOfPeople, LocalDateTime time) {
        requireAllNonNull(phone, time);
        this.phone = phone;
        this.numberOfPeople = numberOfPeople;
        this.time = time;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        // TODO: Check the time whether can add or not

        Reservation reservation = new Reservation(phone, numberOfPeople, time);
        if (model.hasReservation(reservation)) {
            return new CommandResult(String.format(MESSAGE_UNSUCCESSFUL, reservation));
        }
        model.addReservation(reservation);
        return new CommandResult(String.format(MESSAGE_SUCCESS, reservation));
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
                && time.equals(that.time);
    }
}
