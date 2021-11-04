package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.DATE_RANGE_INPUT;
import static seedu.address.commons.core.Messages.SHIFT_PERIOD_PARSING_DEFAULT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY_SHIFT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Period;
import seedu.address.model.person.Person;
import seedu.address.model.person.Slot;

/**
 * Swaps shifts between 2 staffs.
 */
public class SwapShiftCommand extends Command {
    public static final String COMMAND_WORD = "swapShift";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": swaps shifts between 2 staffs identified "
            + "using their names. You have to input exactly 2 names and 2 shifts. The period within which the "
            + "shifts are active over is optional. Date input is used to indicate the period when the shift is active. "
            + SHIFT_PERIOD_PARSING_DEFAULT + "\n\n"
            + "NOTE: The staff identified using the first name is associated with the first shift and the staff "
            + "identified using the second name is associated with the second shift. Do take note of the order!\n\n"
            + "Parameters: (2 of each)\n"
            + PREFIX_DASH_NAME + " NAME\n"
            + PREFIX_DAY_SHIFT + "DAYOFWEEK-SLOTNUMBER "
            + DATE_RANGE_INPUT + "\n\n"
            + "Examples:\n"
            + COMMAND_WORD + " -n Alex Yeoh d/monday-1 -n David Li d/friday-0\n\n"
            + COMMAND_WORD + " -n Alex Yeoh -n David Li d/tuesday-0 d/wednesday-1";

    public static final String MESSAGE_SWAP_SHIFT_SUCCESS = "Successfully swapped the given shifts between %s and %s!";
    public static final String NON_UNIQUE_NAMES = "The 2 names provided are not unique!";
    public static final String NON_UNIQUE_SHIFTS = "The 2 shifts provided are not unique!";
    public static final String STAFF_NOT_FOUND = "%s is not one of your staff!";
    public static final String SHIFT_CANT_SWAP = "%s does not have the shift %s-%s";
    public static final String SHIFT_ALREADY_EXISTS = "%s already has the shift %s-%s";

    private final Name firstStaff;
    private final Name secondStaff;
    private final DayOfWeek firstDayOfWeek;
    private final Slot firstSlot;
    private final DayOfWeek secondDayOfWeek;
    private final Slot secondSlot;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Period period;

    /**
     * Creates a SwapShiftCommand to swap shifts between 2 staffs.
     *
     * @param nameList List containing the names of 2 staffs
     * @param shiftList List containing the shifts of 2 staffs
     */
    public SwapShiftCommand(List<Name> nameList, List<String> shiftList, LocalDate startDate, LocalDate endDate) {
        firstStaff = nameList.get(0);
        secondStaff = nameList.get(1);

        String[] firstStringList = shiftList.get(0).split("-");
        firstDayOfWeek = DayOfWeek.valueOf(firstStringList[0].toUpperCase());
        firstSlot = Slot.getSlotByOrder(firstStringList[1]);

        String[] secondStringList = shiftList.get(1).split("-");
        secondDayOfWeek = DayOfWeek.valueOf(secondStringList[0].toUpperCase());
        secondSlot = Slot.getSlotByOrder(secondStringList[1]);
        this.startDate = startDate;
        this.endDate = endDate;
        this.period = new Period(startDate, endDate);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person firstStaffToEdit = model.findPersonByName(firstStaff);
        Person secondStaffToEdit = model.findPersonByName(secondStaff);

        // If the first staff does not exist
        if (firstStaffToEdit == null) {
            throw new CommandException(String.format(STAFF_NOT_FOUND, firstStaff));
        }

        // If the second staff does not exist
        if (secondStaffToEdit == null) {
            throw new CommandException(String.format(STAFF_NOT_FOUND, secondStaff));
        }

        // If the first staff does not have the shift to swap
        if (!firstStaffToEdit.isWorking(firstDayOfWeek, firstSlot.getOrder(), period)) {
            throw new CommandException(String.format(SHIFT_CANT_SWAP, firstStaff, firstDayOfWeek, firstSlot));
        }

        // If the second staff does not have the shift to swap
        if (!secondStaffToEdit.isWorking(secondDayOfWeek, secondSlot.getOrder(), period)) {
            throw new CommandException(String.format(SHIFT_CANT_SWAP, secondStaff, secondDayOfWeek, secondSlot));
        }

        // If the first staff already has the shift that he/she is trying to swap to
        if (firstStaffToEdit.isWorking(secondDayOfWeek, secondSlot.getOrder(), period)) {
            throw new CommandException(String.format(SHIFT_ALREADY_EXISTS, firstStaff, secondDayOfWeek, secondSlot));
        }

        // If the second staff already has the shift that he/she is trying to swap to
        if (secondStaffToEdit.isWorking(firstDayOfWeek, firstSlot.getOrder(), period)) {
            throw new CommandException(String.format(SHIFT_ALREADY_EXISTS, secondStaff, firstDayOfWeek, firstSlot));
        }

        model.deleteShift(firstStaffToEdit, firstDayOfWeek, firstSlot, startDate, endDate);
        model.deleteShift(secondStaffToEdit, secondDayOfWeek, secondSlot, startDate, endDate);
        model.addShift(firstStaffToEdit, secondDayOfWeek, secondSlot, startDate, endDate);
        model.addShift(secondStaffToEdit, firstDayOfWeek, firstSlot, startDate, endDate);

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SWAP_SHIFT_SUCCESS, firstStaff, secondStaff));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SwapShiftCommand that = (SwapShiftCommand) o;
        return firstStaff.equals(that.firstStaff) && secondStaff.equals(that.secondStaff)
                && firstDayOfWeek == that.firstDayOfWeek && firstSlot == that.firstSlot
                && secondDayOfWeek == that.secondDayOfWeek && secondSlot == that.secondSlot;
    }
}
