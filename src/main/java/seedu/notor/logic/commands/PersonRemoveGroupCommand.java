package seedu.notor.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.notor.logic.commands.exceptions.CommandException;
import seedu.notor.model.Model;
import seedu.notor.model.exceptions.ItemNotFoundException;
import seedu.notor.model.person.Person;

public class PersonRemoveGroupCommand implements Command {

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
        try {
            Person personToEdit = model.findPerson(personName);
            personToEdit.removeSuperGroup(groupName);
            model.findSuperGroup(groupName).removePerson(personToEdit);
            model.setPerson(personToEdit, personToEdit);
            return new CommandResult(String.format(MESSAGE_SUCCESS, groupName));
        } catch (ItemNotFoundException e) {
            throw new CommandException(MESSAGE_NOT_IN_GROUP);
        }
    }
}
