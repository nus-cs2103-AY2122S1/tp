package seedu.address.logic.commands;

import seedu.address.model.Model;

public class QuitMeetingCommand extends Command {
    public static final String COMMAND_WORD = "quitMeeting";

    public static final String MESSAGE_QUIT_ACKNOWLEDGEMENT = "Exiting meeting window as requested, and opening main "
            + "window";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_QUIT_ACKNOWLEDGEMENT, false, false, false, false, true);
    }
}
