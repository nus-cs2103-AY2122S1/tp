package seedu.anilist.logic.commands;

/**
 * Represents what specific action to take in a command.
 */
public enum Action {
    ADD, DELETE, DEFAULT;

    private static final String AVAILABLE_ACTIONS = "add, delete";
    public static final String MESSAGE_INVALID_ACTION_FORMAT = "%1$s is not a valid action. "
            + "These are the available actions:\n" + AVAILABLE_ACTIONS;

    /**
     * Returns true if a given string is a valid action.
     */
    public static boolean isValidAction(String test) {
        switch (test) {
        case "add" :
        case "delete" :
            return true;
        default :
            return false;
        }
    }

    /**
     * Returns an Action from the given string.
     */
    public static Action actionFromString(String actionString) {
        assert actionString != null;

        switch (actionString) {
        case "add" :
            return Action.ADD;
        case "delete" :
            return Action.DELETE;
        default:
            return Action.DEFAULT;
        }
    }
}
