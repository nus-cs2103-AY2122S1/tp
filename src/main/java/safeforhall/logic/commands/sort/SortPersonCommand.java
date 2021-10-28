package safeforhall.logic.commands.sort;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import safeforhall.logic.commands.Command;
import safeforhall.logic.commands.CommandResult;
import safeforhall.logic.commands.exceptions.CommandException;
import safeforhall.model.Model;
import safeforhall.model.person.Person;

public class SortPersonCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String PARAMETERS = "";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sort residents by alphabetical order\n"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_SUCCESS = "Residents have successfully been sorted";

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateSortedPersonList(new PersonComparator());
        return new CommandResult(MESSAGE_SUCCESS);
    }

    public class PersonComparator implements Comparator<Person> {
        @Override
        public int compare(Person p1, Person p2) {
            return p1.getName().compareTo(p2.getName());
        }
    }
}
