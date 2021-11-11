package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_GROUPS;

import java.util.function.Predicate;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;

public class AddGroupCommand implements UndoableCommand {
    public static final String COMMAND_WORD = "addG";

    public static final String MESSAGE_DUPLICATE_GROUP = "This group already exists in the address book";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a group to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION]"
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Favourite Group "
            + PREFIX_DESCRIPTION + "This is a group for my summer project";

    public static final String MESSAGE_SUCCESS = "New group added: %1$s";
    public static final String MESSAGE_TEMPLATE_UNDO_SUCCESS = "Successful undo of addition of group: %1$s";

    private final Group toAdd;

    private Predicate<? super Person> personPredicate;
    private Predicate<? super Group> groupPredicate;

    /**
     * Creates an AddGroupCommand to add the specified {@code Group}
     *
     * @param group to be added to the address book.
     */
    public AddGroupCommand(Group group) {
        requireNonNull(group);
        toAdd = group;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        personPredicate = model.getFilteredPersonListPredicate();
        groupPredicate = model.getFilteredGroupListPredicate();

        if (model.hasGroup(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_GROUP);
        }

        model.addGroup(toAdd);
        model.updateFilteredGroupList(PREDICATE_SHOW_ALL_GROUPS);
        return new CommandResult.Builder(String.format(MESSAGE_SUCCESS, toAdd))
                .goToHome()
                .build();
    }

    @Override
    public CommandResult undo(Model model) throws CommandException {
        assert model.hasGroup(toAdd) : "The model must contain the group to undo its addition.";
        model.deleteGroup(toAdd);
        if (personPredicate == null) {
            personPredicate = Model.PREDICATE_SHOW_ALL_PERSONS;
        }
        model.updateFilteredPersonList(personPredicate);
        if (groupPredicate == null) {
            groupPredicate = Model.PREDICATE_SHOW_ALL_GROUPS;
        }
        model.updateFilteredGroupList(groupPredicate);
        return new CommandResult.Builder(String.format(MESSAGE_TEMPLATE_UNDO_SUCCESS, toAdd))
                .build();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddGroupCommand // instanceof handles nulls
                && toAdd.equals(((AddGroupCommand) other).toAdd));
    }
}
