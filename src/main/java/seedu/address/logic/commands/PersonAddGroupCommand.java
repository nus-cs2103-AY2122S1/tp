package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.SuperGroup;
import seedu.address.model.person.Person;

public class PersonAddGroupCommand extends Command {

    public static final String COMMAND_WORD = "person_add_group";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to a group"
        + "Parameters: "
        + PREFIX_NAME + "NAME "
        + PREFIX_GROUP + "GROUP\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_NAME + "John Doe "
        + PREFIX_GROUP + "Team";

    public static final String MESSAGE_SUCCESS = "Added person to %s";

    public static final String MESSAGE_DUPLICATE_GROUP = "This person is already in the group";

    protected String personName;

    protected String groupName;

    /**
     * Creates an PersonAddGroupCommand to add the specified {@code Person}
     */
    public PersonAddGroupCommand(String personName, String groupName) {
        this.personName = personName;
        this.groupName = groupName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person personToEdit = model.findPerson(personName);
        if (personToEdit.getSuperGroups().contains(groupName)) {
            throw new CommandException(MESSAGE_DUPLICATE_GROUP);
        }
        SuperGroup superGroup = model.findSuperGroup(groupName);
        if (superGroup == null) {
            superGroup = new SuperGroup(groupName);
            model.addSuperGroup(superGroup);
        }
        superGroup.addPerson(personToEdit);
        personToEdit.addSuperGroup(superGroup);
        model.setPerson(personToEdit, personToEdit);
        return new CommandResult(String.format(MESSAGE_SUCCESS, groupName));
    }
}
