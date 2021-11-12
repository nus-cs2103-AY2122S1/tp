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

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Slot;
import seedu.address.model.person.exceptions.DuplicateShiftException;

/**
 * Adds a shift to a staff's schedule.
 */
public class AddShiftCommand extends Command {

    public static final String COMMAND_WORD = "addShift";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a shift to the staff identified "
            + "by the index number used in the displayed staff list or the name of staff. the period over"
            + "which the shift is active over is optional. Date inputs are used to indicate the period of"
            + "the shift to add and they are optional."
            + SHIFT_PERIOD_PARSING_DEFAULT + "\n\n"
            + "Parameters:\n"
            + PREFIX_DASH_INDEX + " INDEX or "
            + PREFIX_DASH_NAME + " NAME "
            + PREFIX_DAY_SHIFT + "DAY_AND_SLOT "
            + DATE_RANGE_INPUT + "\n\n"
            + "Example:\n" + COMMAND_WORD + " "
            + PREFIX_DASH_INDEX + " 1 "
            + PREFIX_DAY_SHIFT + "monday-1\n"
            + COMMAND_WORD + " "
            + PREFIX_DASH_NAME + " JOE "
            + PREFIX_DAY_SHIFT + "TUESDAY-0 " + PREFIX_DATE + "2020-01-01";

    public static final String MESSAGE_ADD_SHIFT_SUCCESS = "New shift added to the schedule of %s: %s, %s.";
    public static final String MESSAGE_DUPLICATE_SHIFT = "This shift already exists in the staff's schedule.";

    private final Index index;
    private final Name name;
    private final DayOfWeek dayOfWeek;
    private final Slot slot;
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Creates an AddShiftCommand to add the specified {@code Shift} to a {@code Person}.
     */
    public AddShiftCommand(Index index, Name name, String shiftDateAndSlot, LocalDate startDate, LocalDate endDate) {
        requireNonNull(shiftDateAndSlot);
        this.index = index;
        this.name = name;
        String[] strings = shiftDateAndSlot.split("-");
        dayOfWeek = DayOfWeek.valueOf(strings[0].toUpperCase());
        slot = Slot.getSlotByOrder(strings[1]);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        //check if the input dates contain the dayOfWeek
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
            model.addShift(staffToEdit, dayOfWeek, slot, startDate, endDate);
        } catch (DuplicateShiftException de) {
            throw new CommandException(MESSAGE_DUPLICATE_SHIFT);
        }

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_ADD_SHIFT_SUCCESS, staffToEdit.getName(), dayOfWeek, slot));

    }



    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddShiftCommand)) {
            return false;
        }

        // state check
        AddShiftCommand command = (AddShiftCommand) other;
        return ((index == null && command.index == null) || (index != null && index.equals(command.index)))
                && ((name == null && command.name == null) || (name != null && name.equals(command.name)))
                && dayOfWeek.equals(command.dayOfWeek)
                && slot.equals(command.slot)
                && startDate.equals(command.startDate)
                && endDate.equals(command.endDate);
    }
}
