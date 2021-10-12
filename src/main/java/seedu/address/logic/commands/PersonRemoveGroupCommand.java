package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Arrays;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class PersonRemoveGroupCommand extends Command {

    public static final String COMMAND_WORD = "person_rm_group";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes a person from a group"
        + "Parameters: "
        + PREFIX_NAME + "NAME "
        + PREFIX_GROUP + "GROUP\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_NAME + "John Doe "
        + PREFIX_GROUP + "Team";

    public static final String MESSAGE_SUCCESS = "Removed person from %s";

    public static final String MESSAGE_NOT_IN_GROUP = "This person is not in the group";

    protected String personName;

    protected String groupName;

    /**
     * Creates an PersonRemoveGroupCommand to add the specified {@code Person}
     */
    public PersonRemoveGroupCommand(String personName, String groupName) {
        this.personName = personName;
        this.groupName = groupName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person personToEdit = model.findPerson(personName);
        if (!personToEdit.getSuperGroups().contains(groupName)) {
            throw new CommandException(MESSAGE_NOT_IN_GROUP);
        }
        personToEdit.getSuperGroups().remove(groupName);
        System.out.println(Arrays.toString(personToEdit.getSubGroups().toArray()));
        personToEdit.getSubGroups().removeIf(subGroup -> subGroup.split("_")[0].equals(groupName));
        model.setPerson(personToEdit, personToEdit);
        return new CommandResult(String.format(MESSAGE_SUCCESS, groupName));
    }
}
