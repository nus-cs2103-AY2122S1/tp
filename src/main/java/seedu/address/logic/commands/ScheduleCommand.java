package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ViewingType;

public class ScheduleCommand extends Command {

    public static final String COMMAND_WORD = "schedule";

    public static final String MESSAGE_SUCCESS = "Showing schedule";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateLessonWithAttendeesList();
        model.setViewingType(ViewingType.SCHEDULE);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
