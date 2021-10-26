package seedu.address.logic.commands;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.storage.RoleReqStorage;

import java.io.FileNotFoundException;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

public class SetRoleReqCommand extends Command {
    public static final String COMMAND_WORD = "SetRoleReq";
    public static final String HELP_MESSAGE = COMMAND_WORD + "Sets the minimum number of staff required for "
            + "the specified role.\n\n"
            + "Parameters:\n"
            + PREFIX_ROLE + "ROLE\n\n"
            + "Examples:\n"
            + COMMAND_WORD + " " + PREFIX_ROLE + "kitchen" + " 1\n"
            + COMMAND_WORD + " " + PREFIX_ROLE + "floor" + " 3\n";

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
            }
        }
        return new CommandResult("Role requirements successfully updated:\n\n"
                + RoleReqStorage.getRoleReqs());
    }
}
