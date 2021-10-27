package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.util.CommandUtil;
import seedu.address.model.Model;
import seedu.address.model.person.Phone;
import seedu.address.model.reservation.Remark;
import seedu.address.model.reservation.Reservation;
import seedu.address.model.reservation.ReservationsManager;
import seedu.address.model.reservation.exception.ReservationException;
import seedu.address.model.table.Table;
import seedu.address.model.tag.Tag;

/**
 * Represents the command to add reservation
 */
public class ReserveCommand extends Command {
    public static final String COMMAND_WORD = "addr";
    public static final String MESSAGE_USAGE = String.format(
            "%1$s: Adds a new reservation with customer's phone number, number of people, time and remark and tags.\n"
            + "Parameters: NUMBER_OF_PEOPLE (must be a positive integer) %2$sPHONE "
            + "%3$sDATE_TIME (has to be on the hour, i.e. hh00) "
            + "[%4$sREMARK] "
            + "[%5$sTAG] ...\n"
            + "Example: %1$s 2 %2$s98765432 %3$s2021-12-24 1900 %4$sbirthday party %5$s10PercentOff",
            CommandUtil.formatCommandWord(COMMAND_WORD),
            PREFIX_PHONE, PREFIX_TIME, PREFIX_REMARK, PREFIX_TAG
    );
    public static final String MESSAGE_SUCCESS = "New reservation added: %1$s";
    public static final String MESSAGE_RESERVATION_EXISTS = "Reservation already exist: %1$s";
    public static final String MESSAGE_CUSTOMER_MISSING =
            "No customer with phone number %1$s exist.\nUnable to create reservation.";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private Phone phone;
    private int numberOfPeople;
    private LocalDateTime dateTime;
    private Remark remark;
    private Set<Tag> tags = new HashSet<>();

    /**
     * Creates a command to add a reservation
     */
    public ReserveCommand(Phone phone, int numberOfPeople, LocalDateTime dateTime, Remark remark, Set<Tag> tags) {
        requireAllNonNull(phone, dateTime, tags);
        this.phone = phone;
        this.numberOfPeople = numberOfPeople;
        this.dateTime = dateTime;
        this.remark = remark;
        this.tags.addAll(tags);
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
            Reservation reservation = new Reservation(phone, numberOfPeople, dateTime, tableToBeAssigned, remark, tags);
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
                && dateTime.equals(that.dateTime)
                && tags.equals(that.tags);
    }
}
