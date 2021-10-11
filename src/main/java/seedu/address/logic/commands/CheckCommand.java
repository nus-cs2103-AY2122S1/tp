package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.enums.EnumTypeOfCheck;
import seedu.address.model.Model;
import seedu.address.model.reservation.ListContainsReservationPredicate;
import seedu.address.model.reservation.PersonContainsReservationPredicate;

/**
 * Checks if there is an available slot for reservation in the specified date and time.
 */
public class CheckCommand extends Command {

    public static final String COMMAND_WORD = "check";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Checks if there is an available slot for reservation "
            + "at the date/time specified. \n"
            + "Parameters: Date (format: YYYY-MM-DD HHMM or YYYY-MM-DD or HHMM) \n"
            + "Example: " + COMMAND_WORD + " 2021-12-25 1900";

    private final ListContainsReservationPredicate predicate;
    private final LocalDate date;
    private final LocalTime time;
    private final EnumTypeOfCheck typeOfCheck;

    /**
     * Constructs a new CheckCommand object
     * @param predicate contains the query date and/or time used to filter the reservation list
     */
    public CheckCommand(ListContainsReservationPredicate predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
        this.date = predicate.getDate();
        this.time = predicate.getTime();
        this.typeOfCheck = predicate.getTypeOfCheck();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredReservationList(predicate);
        model.updateFilteredPersonList(new PersonContainsReservationPredicate(model.getFilteredReservationList()));
        return new CommandResult(String.format(getDisplayMessage(typeOfCheck),
                model.getFilteredReservationList().size(),
                convertToLocalDateTime(date, time)));
    }

    private LocalDateTime convertToLocalDateTime(LocalDate date, LocalTime time) {
        return LocalDateTime.of(date, time);
    }

    private String getDisplayMessage(EnumTypeOfCheck e) {
        String message = "";
        switch (e) {
        case Date:
            message = Messages.MESSAGE_RESERVATIONS_LISTED_DATE_ONLY;
            break;
        case Time:
            message = Messages.MESSAGE_RESERVATIONS_LISTED_TIME_ONLY;
            break;
        case DateTime:
            message = Messages.MESSAGE_RESERVATIONS_LISTED_DATETIME;
            break;
        default:
            assert false;
        }
        return message;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CheckCommand // instanceof handles nulls
                && predicate.equals(((CheckCommand) other).predicate)); // state check
    }
}
