package seedu.track2gather.logic.commands;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import seedu.track2gather.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.track2gather.logic.commands.exceptions.CommandException;
import seedu.track2gather.model.Model;
import seedu.track2gather.model.person.Person;
import seedu.track2gather.model.person.attributes.Period;
import seedu.track2gather.model.person.attributes.ShnPeriod;

/**
 * Shifts all persons’ SHN end dates by the specified number of days.
 */
public class TShiftCommand extends Command {

    public static final String COMMAND_WORD = "tshift";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shifts all persons' SHN end date "
            + "by the specified number of days.\n"
            + "Parameters: [PLUS_MINUS_SIGN]DAYS\n"
            + "Example: " + COMMAND_WORD + " 3";

    public static final String MESSAGE_SUCCESS = "The SHN end dates of all currently displayed person(s) have been "
            + "shifted by %d day(s).\n";
    public static final String MESSAGE_TSHIFT_BY_ZERO = "Number of days to shift the SHN end dates by should not be 0.";
    public static final String MESSAGE_BEYOND_LIMIT = "Magnitude of shift should not be larger than %d days.";
    public static final int MAX_ABS_DAYS_VALUE = 90;

    private final int days;

    /**
     * @param days to shift all persons' {@code ShnPeriod} end date by.
     */
    public TShiftCommand(int days) {
        this.days = days;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (days == 0) {
            throw new CommandException(MESSAGE_TSHIFT_BY_ZERO);
        }
        if (Math.abs(days) > MAX_ABS_DAYS_VALUE) {
            throw new CommandException(String.format(MESSAGE_BEYOND_LIMIT, MAX_ABS_DAYS_VALUE));
        }

        List<Person> lastShownList = model.getFilteredPersonList();

        for (Person personToEdit : lastShownList) {
            EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
            editPersonDescriptor.setShnPeriod(this.shiftShnPeriodEndDate(personToEdit.getShnPeriod()));
            Person editedPerson = EditCommand.createEditedPerson(personToEdit, editPersonDescriptor);
            model.setPerson(personToEdit, editedPerson);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, days));
    }

    /**
     * Shifts the end date of the specified {@code ShnPeriod} by number of days specified by {@code days} attribute
     * of the {@code TShiftCommand} object.
     * @param shnPeriod that will have its end date shifted.
     * @return A new {@code ShnPeriod} object with its end date shifted.
     */
    public ShnPeriod shiftShnPeriodEndDate(ShnPeriod shnPeriod) {
        return new ShnPeriod(shnPeriod.value.map(period -> {
            LocalDate startDate = period.getStartDate();
            LocalDate endDate = period.getEndDate();

            assert days != 0;

            LocalDate newEndDate = Collections.max(List.of(endDate.plusDays(days), startDate.plusDays(1)));

            return new Period(startDate, newEndDate);
        }));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TShiftCommand // instanceof handles nulls
                && days == (((TShiftCommand) other).days));
    }
}
