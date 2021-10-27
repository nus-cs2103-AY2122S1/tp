package seedu.address.logic.commands.followupaction;

/**
 * Singleton class representing the case where there is no follow-up action to be taken.
 */
public class NoFollowUpAction implements CommandFollowUpAction {

    private static final NoFollowUpAction SINGLE_INSTANCE = new NoFollowUpAction();

    private NoFollowUpAction() {}

    public static NoFollowUpAction getInstance() {
        return SINGLE_INSTANCE;
    }

    @Override
    public void execute() {}

}
