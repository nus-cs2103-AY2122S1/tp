package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.FILE_NOT_FOUND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;

import java.io.FileNotFoundException;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.storage.RoleReqStorage;

public class SetRoleReqCommand extends Command {
    public static final String COMMAND_WORD = "setRoleReq";
    public static final String HELP_MESSAGE = COMMAND_WORD + "Sets the minimum number of staff required for "
            + "the specified role.\n\n"
            + "Parameters:\n"
            + PREFIX_ROLE + "ROLE-NUMBER\n\n"
            + "Examples:\n"
            + COMMAND_WORD + " " + PREFIX_ROLE + "kitchen-1 bartender-1\n"
            + COMMAND_WORD + " " + PREFIX_ROLE + "floor-3\n\n"
            + "Currently, the role requirements per shift are:\n"
            + String.format("%s", RoleReqStorage.getRoleReqs());

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
            } catch (FileNotFoundException e) {
                throw new CommandException(FILE_NOT_FOUND + RoleReqStorage.FILEPATH);
            }
        }
        return new CommandResult("Role requirements successfully updated:\n\n"
                + RoleReqStorage.getRoleReqs());
    }

    public static String getHelpMessage() {
        return String.format(HELP_MESSAGE, RoleReqStorage.getRoleReqs());
    }

}
