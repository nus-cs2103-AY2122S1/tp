package seedu.notor.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_SUBGROUP;

import seedu.notor.logic.commands.exceptions.CommandException;
import seedu.notor.model.Model;
import seedu.notor.model.exceptions.DuplicateItemException;
import seedu.notor.model.group.SubGroup;
import seedu.notor.model.group.SuperGroup;
import seedu.notor.model.person.Person;

public class PersonAddSubGroupCommand implements Command {

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
        try {
            Person personToEdit = model.findPerson(personName);
            SuperGroup superGroup = model.findSuperGroup(groupName);
            superGroup.addSubGroup(subGroupName);
            SubGroup subGroup = superGroup.findSubGroup(subGroupName);
            subGroup.addPerson(personToEdit);
            personToEdit.addSubGroup(subGroup);
            superGroup.findSubGroup(subGroupName).addPerson(personToEdit);
            model.setPerson(personToEdit, personToEdit);
            return new CommandResult(String.format(MESSAGE_SUCCESS, groupName + "_" + subGroupName));
        } catch (DuplicateItemException e) {
            throw new CommandException(MESSAGE_DUPLICATE_GROUP);
        }
    }
}
