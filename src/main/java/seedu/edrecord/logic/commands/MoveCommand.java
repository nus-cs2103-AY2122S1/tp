package seedu.edrecord.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.edrecord.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.edrecord.commons.core.Messages;
import seedu.edrecord.commons.core.index.Index;
import seedu.edrecord.logic.commands.exceptions.CommandException;
import seedu.edrecord.model.Model;
import seedu.edrecord.model.group.Group;
import seedu.edrecord.model.module.Module;
import seedu.edrecord.model.person.Person;

/**
 * Moves an existing person in edrecord to an existing mod and class.
 */
public class MoveCommand extends Command {

    public static final String COMMAND_WORD = "mv";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Moves the person identified by the index number used"
            + "in the displayed person list to the specified module and class.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_MODULE + "MODULE "
            + PREFIX_GROUP + "GROUP\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_MODULE + "CS2103 "
            + PREFIX_GROUP + "T01";

    public static final String MESSAGE_SAME_MOD_GROUP = "%1$s already in %2$s/%3$s";
    public static final String MESSAGE_MOVE_PERSON_SUCCESS = "Successfully moved student(s) to %1$s, %2$s";

    private final List<Index> indexes;
    private final Module module;
    private final Group group;

    /**
     * @param indexes of the people in the filtered person list to move.
     * @param mod to move the person to.
     * @param grp to move the person to.
     */
    public MoveCommand(List<Index> indexes, Module mod, Group grp) {
        requireNonNull(indexes);
        requireNonNull(mod);
        requireNonNull(grp);

        this.indexes = indexes;
        this.module = mod;
        this.group = grp;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasModule(module)) {
            throw new CommandException(String.format(Module.MESSAGE_DOES_NOT_EXIST, module));
        }
        Module savedMod = model.getModule(module);
        if (!savedMod.hasGroup(group)) {
            throw new CommandException(Group.MESSAGE_DOES_NOT_EXIST);
        }

        List<Person> lastShownList = model.getFilteredPersonList();
        for (Index index : indexes) {
            if (index.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            Person personToMove = lastShownList.get(index.getZeroBased());
            if (personToMove.getModules().containsGroupInModule(savedMod, group)) {
                throw new CommandException(
                        String.format(MESSAGE_SAME_MOD_GROUP, personToMove.getName(), savedMod, group));
            }

            Module newModule = new Module(savedMod.getCode());
            personToMove.getModules().add(newModule, group);
        }

        model.setSearchFilter(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_MOVE_PERSON_SUCCESS, savedMod, group));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MoveCommand)) {
            return false;
        }

        // state check
        MoveCommand e = (MoveCommand) other;
        return indexes.equals(e.indexes)
                && module.isSameModule(e.module)
                && group.isSameGroup(group);
    }

}
