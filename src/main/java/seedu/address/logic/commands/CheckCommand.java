package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.enums.EnumTypeOfCheck;
import seedu.address.model.Model;
import seedu.address.model.reservation.ListContainsReservationPredicate;
import seedu.address.model.reservation.PersonContainsReservationPredicate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

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

    // Used to convert LocalDate to LocalDateTime when user inputs only date to query
    public static final LocalTime DEFAULT_TIME = LocalTime.parse("00:00");

    private final ListContainsReservationPredicate predicate;
    private final LocalDate date;
    private final LocalTime time;
    private final EnumTypeOfCheck typeOfCheck;

    public CheckCommand(ListContainsReservationPredicate predicate
            , LocalDate date
            , LocalTime time
            , EnumTypeOfCheck typeOfCheck) {
        requireAllNonNull(predicate, date, typeOfCheck);
        this.predicate = predicate;
        this.date = date;
        this.time = time;
        this.typeOfCheck = typeOfCheck;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredReservationList(predicate);
        model.updateFilteredPersonList(new PersonContainsReservationPredicate(model.getFilteredReservationList()));
        return new CommandResult(String.format(getDisplayMessage(typeOfCheck)
                , model.getFilteredReservationList().size()
                , convertToLocalDateTime(date, time)));
    }

    private LocalDateTime convertToLocalDateTime(LocalDate date, LocalTime time) {
        return LocalDateTime.of(date, (Optional.ofNullable(time).orElse(DEFAULT_TIME)));
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
