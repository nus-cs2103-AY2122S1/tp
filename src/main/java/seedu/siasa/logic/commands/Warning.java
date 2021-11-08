package seedu.siasa.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.siasa.ui.MainWindow;

/**
 * Class that is responsible for displaying warnings to the user.
 */
public class Warning {

    /**
     * Display a warning dialog to the user, with the specified heading
     * and description.
     * @return Boolean value of the user's response to proceed with action.
     */
    public static boolean isUserConfirmingCommand(String description) {
        requireNonNull(description);
        boolean isConfirmingCommand = MainWindow.showWarning(description);
        return isConfirmingCommand;
    }

}
