package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tuition.TuitionClass;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class ViewTodayTuitionCommand extends Command {
    public static final String COMMAND_WORD = "today";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows all tuition classes on today "
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Today tuition classes are shown below \n"
            + "Number of tuition classes today: ";

    /**
     * Constructor for a command to view today tuition classes.
     */
    public ViewTodayTuitionCommand() {}


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<TuitionClass> todayTuitionList = model.getTodayTuitionList();

        return new CommandResult(MESSAGE_SUCCESS + todayTuitionList.size(),
                CommandResult.UiAction.SHOW_TODAY_TUITIONS_PAGE);
    }
}
