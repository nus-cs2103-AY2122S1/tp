package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.FILE_NOT_FOUND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;

import java.io.IOException;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.storage.RoleReqStorage;

public class SetRoleReqCommand extends Command {
    public static final String COMMAND_WORD = "setRoleReq";
    public static final String SUCCESS_MESSAGE = "Role requirements successfully updated:\n\n";
    private static final String HELP_MESSAGE = COMMAND_WORD + " Sets the minimum number of staff required for "
            + "the specified role.\n\n"
            + "Parameters:\n"
            + PREFIX_ROLE + "ROLE-NUMBER\n\n"
            + "Examples:\n"
            + COMMAND_WORD + " " + PREFIX_ROLE + "kitchen-1 " + PREFIX_ROLE + "bartender-1\n"
            + COMMAND_WORD + " " + PREFIX_ROLE + "floor-3\n\n"
            + "Currently, the role requirements per shift are:\n"
            + "%s";

    private final Set<String> roleReqList;

    public SetRoleReqCommand(Set<String> roleReqList) {
        this.roleReqList = roleReqList;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Person> staffs = model.getUnFilteredPersonList();
        for (String roleReq : roleReqList) {
            String[] roleReqSplit = roleReq.split("-");

            try {
                RoleReqStorage.update(roleReqSplit[0], Integer.parseInt(roleReqSplit[1]));
            } catch (IOException e) {
                throw new CommandException(FILE_NOT_FOUND + RoleReqStorage.FILEPATH);
            }
        }
        return new CommandResult(SUCCESS_MESSAGE + RoleReqStorage.getRoleReqs());
    }

    /**
     * Returns the Help Message with the updated role requirements.
     *
     * @return the Help Message with the updated role requirements.
     */
    public static String getHelpMessage() {
        return String.format(HELP_MESSAGE, RoleReqStorage.getRoleReqs());
    }

    /**
     * Returns the Set of Role Requirements.
     *
     * @return roleReqList.
     */
    public Set<String> getRoleReqList() {
        return this.roleReqList;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        // short circuit if same object
        return other == this
                || !(other instanceof SetRoleReqCommand)
                || this.roleReqList.equals(((SetRoleReqCommand) other).getRoleReqList());
    }
}
