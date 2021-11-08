package seedu.smartnus.logic.commands;

import seedu.smartnus.commons.core.theme.Theme;
import seedu.smartnus.logic.commands.exceptions.CommandException;
import seedu.smartnus.model.Model;

public class ThemeCommand extends Command {

    public static final String COMMAND_WORD = "theme";
    public static final String LIGHT_KEYWORD = "light";
    public static final String DARK_KEYWORD = "dark";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " light/dark";
    public static final String MESSAGE_SUCCESS = "Changed the theme to: ";
    public static final String MESSAGE_NO_CHANGE = "Theme is already: ";

    private Theme theme;

    public ThemeCommand(Theme theme) {
        this.theme = theme;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (model.getTheme().equals(theme)) {
            return new CommandResult(MESSAGE_NO_CHANGE + theme);
        }
        model.setTheme(theme);
        return new CommandResult(MESSAGE_SUCCESS + theme);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ThemeCommand // instanceof handles nulls
                && theme.equals(((ThemeCommand) other).theme)); // state check
    }
}
