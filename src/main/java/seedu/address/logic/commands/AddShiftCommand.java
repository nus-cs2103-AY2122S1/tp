package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.time.DayOfWeek;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Slot;
import seedu.address.model.person.exceptions.DuplicateShiftException;

/**
 * Adds a shift to a staff's schedule.
 */
public class AddShiftCommand extends Command {
    public static final String COMMAND_WORD = "addShift";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a shift to target person ";

    public static final String MESSAGE_ADD_SHIFT_SUCCESS = "New shift added to the schedule of %1$s: %1$s, %1$s";
    public static final String MESSAGE_PERSON_NOT_EXIST = "This staff does not exist in the address book";
    public static final String MESSAGE_DUPLICATE_SHIFT = "This shift already exists in the staff's schedule";

    private final Index index;
    private final DayOfWeek dayOfWeek;
    private final Slot slot;

    /**
     * Creates an AddShiftCommand to add the specified {@code Shift} to a {@code Person}.
     */
    public AddShiftCommand(Index index, DayOfWeek dayOfWeek, Slot slot) {
        requireAllNonNull(index, dayOfWeek, slot);
        this.index = index;
        this.dayOfWeek = dayOfWeek;
        this.slot = slot;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person staffToEdit = lastShownList.get(index.getZeroBased());

        if (!model.hasPerson(staffToEdit)) {
            throw new CommandException(MESSAGE_PERSON_NOT_EXIST);
        }

        try {
            model.addShift(staffToEdit, dayOfWeek, slot);
        } catch (DuplicateShiftException de) {
            throw new CommandException(MESSAGE_DUPLICATE_SHIFT);
        }

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_ADD_SHIFT_SUCCESS, staffToEdit, dayOfWeek, slot));

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
        return index.equals(command.index)
                && dayOfWeek.equals(command.dayOfWeek)
                && slot.equals(command.slot);
    }
}
