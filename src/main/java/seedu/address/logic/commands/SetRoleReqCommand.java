package seedu.address.logic.commands;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.storage.RoleReqStorage;

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

    private String role;
    private int numMinStaff;

    public SetRoleReqCommand(String role, int minStaff) {
        this.role = role;
        this.numMinStaff = minStaff;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Person> staffs = model.getUnFilteredPersonList();
        RoleReqStorage.update(role, numMinStaff);

    }
}
