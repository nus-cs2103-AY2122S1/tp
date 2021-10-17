package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY_SHIFT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.time.DayOfWeek;
import java.util.List;

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
            + "by\nthe index number used in the displayed staff list or\nthe name of staff." + "\n"
            + "Parameters: "
            + "[" + PREFIX_INDEX + "INDEX] or "
            + "[" + PREFIX_NAME + "NAME] + "
            + "[" + PREFIX_DAY_SHIFT + "DAY AND\nSLOT OF A SHIFT]" + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_INDEX + "1 "
            + PREFIX_DAY_SHIFT + "monday-1" + "\n"
            + "OR: " + COMMAND_WORD + " "
            + PREFIX_NAME + "JOE "
            + PREFIX_DAY_SHIFT + "TUESDAY-0";

    public static final String MESSAGE_DELETE_SHIFT_SUCCESS = "Shift deleted from the schedule of %s: %s, %s.";
    public static final String MESSAGE_SHIFT_DOESNT_EXIST = "The shift that you are trying to delete does not exist!";

    private final Index index;
    private final Name name;
    private final DayOfWeek dayOfWeek;
    private final Slot slot;

    /**
     * Creates a DeleteShiftCommand to add the specified {@code Shift} to a {@code Person}.
     */
    public DeleteShiftCommand(Index index, Name name, String shiftDateAndSlot) {
        requireNonNull(shiftDateAndSlot);
        this.index = index;
        this.name = name;
        String[] strings = shiftDateAndSlot.split("-");
        dayOfWeek = DayOfWeek.valueOf(strings[0].toUpperCase());
        slot = Slot.getSlotByOrder(strings[1]);
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

        try {
            model.deleteShift(staffToEdit, dayOfWeek, slot);
        } catch (NoShiftException e) {
            throw new CommandException(MESSAGE_SHIFT_DOESNT_EXIST);
        }

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_DELETE_SHIFT_SUCCESS, staffToEdit.getName(), dayOfWeek, slot));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteShiftCommand)) {
            return false;
        }

        // state check
        DeleteShiftCommand command = (DeleteShiftCommand) other;
        return ((index == null && command.index == null) || (index != null && index.equals(command.index)))
                && ((name == null && command.name == null) || (name != null && name.equals(command.name)))
                && dayOfWeek.equals(command.dayOfWeek)
                && slot.equals(command.slot);
    }
}
