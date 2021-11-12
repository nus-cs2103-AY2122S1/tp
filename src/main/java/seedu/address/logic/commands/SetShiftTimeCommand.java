package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.DATE_RANGE_INPUT;
import static seedu.address.commons.core.Messages.SHIFT_PERIOD_PARSING_DEFAULT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY_SHIFT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SHIFT_TIME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.InvalidShiftTimeException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Slot;

public class SetShiftTimeCommand extends Command {
    public static final String COMMAND_WORD = "setShiftTime";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Set time for a shift of the staff identified "
            + "by the index number used in the displayed staff list or the name of staff.\n"
            + "If the shift does not exist, a new one will be created.\n"
            + "Notice that the startTime must be earlier than the endTime, "
            + "and if the range of time is [10:00-16:00] and [16:00-22:00] for morning "
            + "slot and afternoon slot respectively. Date input is used to indicate the period of the shift to modify."
            + SHIFT_PERIOD_PARSING_DEFAULT + "\n\n"
            + "Parameters:\n"
            + PREFIX_DASH_INDEX + " INDEX or "
            + PREFIX_DASH_NAME + " NAME "
            + PREFIX_DAY_SHIFT + "DAY_AND_SLOT "
            + PREFIX_SHIFT_TIME + "START_TIME:END_TIME "
            + DATE_RANGE_INPUT
            + "\n\n"
            + "Example:\n" + COMMAND_WORD + " "
            + PREFIX_DASH_INDEX + " 1 "
            + PREFIX_DAY_SHIFT + "monday-1 "
            + PREFIX_SHIFT_TIME + "10:00-12:00\n"
            + COMMAND_WORD + " "
            + PREFIX_DASH_NAME + " JOE "
            + PREFIX_DAY_SHIFT + "monday-1 "
            + PREFIX_SHIFT_TIME + "10:00-12:00 da/2021-11-01\n";

    public static final String MESSAGE_SET_SHIFT_TIME_SUCCESS = "%s's shift on %s %s from %s to %s is successfully "
            + "updated to: " + "From %s to %s.";
    public static final String MESSAGE_SHIFT_TIME_OUT_OF_BOUND = "The start time or end time of the shift is out"
            + " of bound.";
    public static final String MESSAGE_WRONG_TIME_ORDER = "The end time is earlier than the start time.";

    private final Index index;
    private final Name name;
    private final DayOfWeek dayOfWeek;
    private final Slot slot;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Creates an SetShiftCommand to add the specified {@code Shift} to a {@code Person}.
     */
    public SetShiftTimeCommand(Index index, Name name, String shiftDateAndSlot, LocalTime[] shiftTimes,
                               LocalDate startDate, LocalDate endDate) {
        requireNonNull(shiftDateAndSlot);
        this.index = index;
        this.name = name;
        String[] strings = shiftDateAndSlot.split("-");
        dayOfWeek = DayOfWeek.valueOf(strings[0].toUpperCase());
        slot = Slot.getSlotByOrder(strings[1]);
        this.startTime = shiftTimes[0];
        this.endTime = shiftTimes[1];
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        Person staffToEdit;

        if (index != null) {
            if (index.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            staffToEdit = lastShownList.get(index.getZeroBased());
        } else {
            if (name != null) {
                staffToEdit = model.findPersonByName(name);
            } else {
                throw new CommandException(MESSAGE_USAGE);
            }
        }

        if (staffToEdit == null || !model.hasPerson(staffToEdit)) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_SEARCHED);
        }

        if (!endTime.isAfter(startTime)) {
            throw new CommandException(MESSAGE_WRONG_TIME_ORDER);
        }

        try {
            model.setShiftTime(staffToEdit, dayOfWeek, slot, startTime, endTime, startDate, endDate);
        } catch (InvalidShiftTimeException e) {
            throw new CommandException(MESSAGE_SHIFT_TIME_OUT_OF_BOUND);
        }

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SET_SHIFT_TIME_SUCCESS, staffToEdit.getName(), dayOfWeek, slot,
                startDate, endDate, startTime, endTime));

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SetShiftTimeCommand)) {
            return false;
        }

        // state check
        SetShiftTimeCommand command = (SetShiftTimeCommand) other;
        return ((index == null && command.index == null) || (index != null && index.equals(command.index)))
                && ((name == null && command.name == null) || (name != null && name.equals(command.name)))
                && dayOfWeek.equals(command.dayOfWeek)
                && slot.equals(command.slot)
                && startTime.equals(command.startTime)
                && endTime.equals(command.endTime)
                && startDate.equals(command.startDate)
                && endDate.equals(command.endDate);
    }
}
