package seedu.anilist.logic.parser;

public enum Action {
    ADD, DELETE, DEFAULT;

    private static final String AVAILABLE_ACTIONS = "add, delete";
    public static final String MESSAGE_INVALID_ACTION_FORMAT = "%1$s is not a valid action. These are the available actions:\n"
            + AVAILABLE_ACTIONS;

    public boolean isValidAction(String test) {
        switch (test) {
        case "add" :
        case "delete" :
            return true;
        default :
            return false;
        }
    }
}
