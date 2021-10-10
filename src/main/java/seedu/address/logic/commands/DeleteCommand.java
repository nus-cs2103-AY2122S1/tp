package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index or name from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    public static final int INVALID_INDEX = -1;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number or name used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer) OR NAME but not both\n"
            + "Examples: " + COMMAND_WORD + " " + PREFIX_INDEX + "1\n"
            + COMMAND_WORD + " " + PREFIX_NAME + "Alex Yeoh";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final Index targetIndex;
    private final Name name;

    /**
     * Constructs delete command using a targetIndex.
     *
     * @param targetIndex The index of a person to be deleted
     */
    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        this.name = null;
    }

    /**
     * Constructs delete command using a name.
     *
     * @param name The name of a person to be deleted
     */
    public DeleteCommand(Name name) {
        this.name = name;
        this.targetIndex = null;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        Person staffToDelete;

        if (targetIndex != null) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            staffToDelete = lastShownList.get(targetIndex.getZeroBased());
        } else {
            int index = getIndexByName(name, lastShownList);
            if (index == INVALID_INDEX) {
                throw new CommandException(Messages.MESSAGE_NAME_NOT_FOUND);
            }

            staffToDelete = lastShownList.get(index);
        }

        model.deletePerson(staffToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, staffToDelete));
    }

    private int getIndexByName(Name name, List<Person> lastShownList) {
        for (int i = 0; i < lastShownList.size(); i++) {
            if (lastShownList.get(i).getName().equals(name)) {
                return i;
            }
        }
        return INVALID_INDEX;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeleteCommand that = (DeleteCommand) o;
        return Objects.equals(targetIndex, that.targetIndex) && Objects.equals(name, that.name);
    }

}
