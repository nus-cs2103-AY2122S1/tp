package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Represents a command to view.
 *
 */
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";
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
