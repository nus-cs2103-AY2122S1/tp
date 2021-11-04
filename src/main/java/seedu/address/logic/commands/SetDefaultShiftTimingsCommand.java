package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.Arrays;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.TimeUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.storage.DefaultShiftTimingsStorage;

public class SetDefaultShiftTimingsCommand extends Command {
    public static final String COMMAND_WORD = "setDefaultShiftTimings";
    public static final String HELP_MESSAGE = COMMAND_WORD + " Sets the default shift timings for a restaurant. Note "
            + "that the morning shift must start during or before noon, while the afternoon shift must start during or "
            + "after noon. Also, the duration of the shift cannot be zero\n\n"
            + "Parameters:\n"
            + COMMAND_WORD + " MORNING_START_TIME MORNING_SHIFT_END_TIME "
            + "AFTERNOON_START_TIME AFTERNOON_END_TIME]\n\n"
            + "Examples:\n"
            + COMMAND_WORD + " 10:00 16:00 17:00 22:00\n"
            + COMMAND_WORD + " 09:00 15:00 18:00 23:00\n\n"
            + "Currently, the default shift timings are:\n"
            + DefaultShiftTimingsStorage.getTimings();

    private final LocalTime[] timings;

    public SetDefaultShiftTimingsCommand(LocalTime[] timings) {
        this.timings = timings;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            DefaultShiftTimingsStorage.update(timings);
            // TimeUtil will not update if DefaultShiftTimingsStorage does not update to keep consistency
            TimeUtil.updateTimings(timings);
        } catch (FileNotFoundException e) {
            throw new CommandException(Messages.FILE_NOT_FOUND + "\n" + DefaultShiftTimingsStorage.FILEPATH);
        }

        return new CommandResult("Default shift timings successfully updated!\n\n"
                + DefaultShiftTimingsStorage.getTimings());

    }

    /**
     * Returns the LocalTime[] of timings.
     *
     * @return LocalTime[] of timings.
     */
    public LocalTime[] getTimings() {
        return this.timings;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        return other == this
                || !(other instanceof SetDefaultShiftTimingsCommand)
                || Arrays.equals(timings, ((SetDefaultShiftTimingsCommand) other).getTimings());
    }
}
