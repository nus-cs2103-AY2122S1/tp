package seedu.siasa.logic.commands.warnings;

import static java.util.Objects.requireNonNull;

import seedu.siasa.ui.MainWindow;

/**
 * Class that is responsible for displaying warnings to the user.
 */
public class Warning {

    public static final String WARNING_HEADER = "WARNING";

    /**
     * Display a warning dialog to the user, with the specified heading
     * and description.
     * @return Boolean value of the user's response to proceed with action.
     */
    public static boolean warnUser(String description) {
        requireNonNull(description);
        boolean userResponse = MainWindow.showWarning(description);
        return userResponse;
    }

}
