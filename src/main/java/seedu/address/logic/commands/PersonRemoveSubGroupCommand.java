package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBGROUP;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.SubGroup;
import seedu.address.model.person.Person;

public class PersonRemoveSubGroupCommand extends Command {

    public static final String COMMAND_WORD = "person_rm_subgroup";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes a person from a subgroup"
        + "Parameters: "
        + PREFIX_NAME + "NAME "
        + PREFIX_GROUP + "GROUP"
        + PREFIX_SUBGROUP + "SUBGROUP\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_NAME + "John Doe "
        + PREFIX_GROUP + "Orbital "
        + PREFIX_SUBGROUP + "Artemis";

    public static final String MESSAGE_SUCCESS = "Removed person from %s";
    public static final String MESSAGE_NOT_IN_SUBGROUP = "This person is not in the subgroup";

    protected String personName;

    protected String groupName;

    protected String subGroupName;

    /**
     * Creates an PersonRemoveSubGroupCommand to add the specified {@code Person}
     */
    public PersonRemoveSubGroupCommand(String personName, String groupName, String subGroupName) {
        this.personName = personName;
        this.groupName = groupName;
        this.subGroupName = subGroupName;
    }



    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person personToEdit = model.findPerson(personName);
        if (!personToEdit.getSubGroups().contains(groupName + "_" + subGroupName)) {
            throw new CommandException(MESSAGE_NOT_IN_SUBGROUP);
        }
        personToEdit.getSubGroups().remove(groupName + "_" + subGroupName);
        SubGroup subGroup = model.findSuperGroup(groupName).findSubGroup(subGroupName);
        if (subGroup == null) {
            throw new CommandException(MESSAGE_NOT_IN_SUBGROUP);
        }
        subGroup.getPeople().remove(personName);
        model.setPerson(personToEdit, personToEdit);
        return new CommandResult(String.format(MESSAGE_SUCCESS, groupName + "_" + subGroupName));
    }
}
