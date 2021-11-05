package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.DATE_RANGE_INPUT;
import static seedu.address.commons.core.Messages.SHIFT_PERIOD_PARSING_DEFAULT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY_SHIFT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Slot;
import seedu.address.model.person.exceptions.NoShiftException;

/**
 * Deletes a shift from a staff's schedule.
 */
public class DeleteShiftCommand extends Command {
    public static final String COMMAND_WORD = "deleteShift";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": deletes a shift from the staff identified "
            + "by the index number used in the displayed staff list or the name of staff. Date input is used "
            + "to indicate the duration of the shift to delete. "
            + SHIFT_PERIOD_PARSING_DEFAULT + "\n\n"
            + "Parameters:\n"
            + "[" + PREFIX_DASH_INDEX + " INDEX] or "
            + "[" + PREFIX_DASH_NAME + " NAME] "
            + PREFIX_DAY_SHIFT + "DAY_AND_SLOT "
            + DATE_RANGE_INPUT
            + "\n\n"
            + "Examples:\n" + COMMAND_WORD + " "
            + PREFIX_DASH_INDEX + " 1 "
            + PREFIX_DAY_SHIFT + "monday-1" + "\n"
            + COMMAND_WORD + " "
            + PREFIX_DASH_NAME + " Alex Yeoh "
            + PREFIX_DAY_SHIFT + "TUESDAY-0"
            + PREFIX_DATE + "2021-01-01" + " "
            + PREFIX_DATE + "2021-01-05";

    public static final String MESSAGE_DELETE_SHIFT_SUCCESS = "Shift deleted from the schedule of %s: %s, %s.";
    public static final String MESSAGE_SHIFT_DOESNT_EXIST = "The shift that you are trying to delete does not exist!";

    private final Index index;
    private final Name name;
    private final DayOfWeek dayOfWeek;
    private final Slot slot;
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Creates a DeleteShiftCommand to add the specified {@code Shift} to a {@code Person}.
     */
    public DeleteShiftCommand(Index index, Name name, String shiftDateAndSlot, LocalDate startDate,
                              LocalDate endDate) {
        requireNonNull(shiftDateAndSlot);
        this.index = index;
        this.name = name;
        String[] strings = shiftDateAndSlot.split("-");
        dayOfWeek = DayOfWeek.valueOf(strings[0].toUpperCase());
        slot = Slot.getSlotByOrder(strings[1]);
        this.endDate = endDate;
        this.startDate = startDate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        CommandUtil.checkDateForDayOfWeek(startDate, endDate, dayOfWeek);
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

        try {
            model.deleteShift(staffToEdit, dayOfWeek, slot, startDate, endDate);
        } catch (NoShiftException e) {
            throw new CommandException(MESSAGE_SHIFT_DOESNT_EXIST);
        }

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_DELETE_SHIFT_SUCCESS, staffToEdit.getName(), dayOfWeek, slot));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeleteShiftCommand)) {
            return false;
        }
        DeleteShiftCommand that = (DeleteShiftCommand) o;
        return Objects.equals(index, that.index) && Objects.equals(name, that.name) && dayOfWeek == that.dayOfWeek
                && slot == that.slot && startDate.equals(that.startDate) && endDate.equals(that.endDate);
    }
}
