package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * TODO
 */
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "student";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows full information on a student of "
            + "a specified index number.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Viewing details of student: ";

    private final Index index;

    /**
     * Constructor for a command to view a student.
     * @param index Index of student to be viewed.
     */
    public ViewCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    /**
     * TODO
     * @param model {@code Model} which the command should operate on.
     * @return
     * @throws CommandException
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> personList = model.getFilteredPersonList();

        if (index.getOneBased() > personList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person person = personList.get(index.getZeroBased());

        Person.setMostRecentTo(person);

        return new CommandResult(MESSAGE_SUCCESS + person.getName(),
                CommandResult.UiAction.SHOW_STUDENT_PAGE);
    }

}
