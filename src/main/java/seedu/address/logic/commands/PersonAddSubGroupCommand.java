package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBGROUP;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.SubGroup;
import seedu.address.model.group.SuperGroup;
import seedu.address.model.person.Person;

public class PersonAddSubGroupCommand extends Command {

    public static final String COMMAND_WORD = "person_add_subgroup";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to a subgroup"
        + "Parameters: "
        + PREFIX_NAME + "NAME "
        + PREFIX_GROUP + "GROUP"
        + PREFIX_SUBGROUP + "SUBGROUP\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_NAME + "John Doe "
        + PREFIX_GROUP + "Orbital "
        + PREFIX_SUBGROUP + "Artemis";

    public static final String MESSAGE_SUCCESS = "Added person to the %s";
    public static final String MESSAGE_DUPLICATE_GROUP = "This person is already in the subgroup";

    protected String personName;

    protected String groupName;

    protected String subGroupName;

    /**
     * Creates an PersonAddSubGroupCommand to add the specified {@code SubGroup}
     *
     * @param personName the name of the person to be added into the subgroup.
     * @param groupName the name of the group where the subgroup belongs to.
     * @param subGroupName the name of the subGroup.
     */
    public PersonAddSubGroupCommand(String personName, String groupName, String subGroupName) {
        this.personName = personName;
        this.groupName = groupName;
        this.subGroupName = subGroupName;
    }



    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person personToEdit = model.findPerson(personName);
        if (personToEdit.getSubGroups().contains(groupName + "_" + subGroupName)) {
            throw new CommandException(MESSAGE_DUPLICATE_GROUP);
        }

        SuperGroup superGroup = model.findSuperGroup(groupName);
        if (superGroup == null) {
            superGroup = new SuperGroup(groupName);
            model.addSuperGroup(superGroup);
        }
        SubGroup subGroup = superGroup.findSubGroup(subGroupName);
        if (subGroup == null) {
            subGroup = new SubGroup(subGroupName, superGroup.getName());
            superGroup.addSubGroup(subGroup);
        }
        superGroup.addPerson(personToEdit);
        subGroup.addPerson(personToEdit);
        personToEdit.addSuperGroup(superGroup);
        personToEdit.addSubGroup(subGroup);
        model.setPerson(personToEdit, personToEdit);
        return new CommandResult(String.format(MESSAGE_SUCCESS, groupName + "_" + subGroupName));
    }
}
