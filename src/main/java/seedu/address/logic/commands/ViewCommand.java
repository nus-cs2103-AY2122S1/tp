package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.function.Predicate;

import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Represents a command to view.
 *
 */
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";
    public static final String HELP_MESSAGE = ": Command to view a staff in the list view\n"
            + "Used with search queries\n"
            + "e.g. " + COMMAND_WORD + " " + "[" + PREFIX_TAG + "]" + " [" + PREFIX_NAME + "]\n"
            + "OR " + COMMAND_WORD + " " + "[" + PREFIX_EMAIL + "]" + " [" + PREFIX_ADDRESS + "]";

    private static final String DEFAULT_COMMAND = "Staff(s) displayed: %1$s";
    private Predicate<Person> testCondition;



    /**
     * Constructor of a view command
     *
     * @param testCondition the test condition to go by
     */
    public ViewCommand(Predicate<Person> testCondition) {
        this.testCondition = testCondition;
    }



    private CommandResult defaultResult(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(testCondition);
        //places the person on the list view
        return new CommandResult(String.format(DEFAULT_COMMAND,
                model.getFilteredPersonList().toString()));
    }

    @Override
    public CommandResult execute(Model model) {
        return defaultResult(model);
    }


}
