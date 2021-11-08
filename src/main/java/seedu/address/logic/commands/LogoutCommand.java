package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.COMPARATOR_SORT_MEMBERS_BY_ID_ASC;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEMBERS;

import seedu.address.commons.status.LoginStatus;
import seedu.address.model.Model;

/**
 * Logs out as a manager.
 */
public class LogoutCommand extends Command {

    /**
     * Stands for logout command.
     */
    public static final String COMMAND_WORD = "logout";

    /**
     * Stands for the message of logout successfully.
     */
    public static final String MESSAGE_SUCCESS = "Logged out successfully!";

    /**
     * Stands for the message of logout status.
     */
    public static final String MESSAGE_ALREADY_IN_STATUS = "You are already in STAFF login status!";

    /**
     * Overrides and executes the model.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult related to logout command.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (LoginStatus.getLoginStatus() == LoginStatus.STAFF) {
            return new CommandResult(MESSAGE_ALREADY_IN_STATUS);
        }
        LoginStatus.setLoginStatus(LoginStatus.STAFF);
        model.updateSortedMemberList(COMPARATOR_SORT_MEMBERS_BY_ID_ASC);
        model.updateFilteredMemberList(PREDICATE_SHOW_ALL_MEMBERS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
