package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.model.Model;
import seedu.address.model.person.PersonContainsFieldsPredicate;

/**
 * Represents a command to view.
 */
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";
    public static final String HELP_MESSAGE = COMMAND_WORD + ": Command to view a staff in the list view\n"
            + "Used with search queries\n"
            + "e.g. " + COMMAND_WORD + " " + "[" + PREFIX_TAG + "TAG]" + " [" + PREFIX_NAME + "NAME]\n"
            + "OR " + COMMAND_WORD + " " + "[" + PREFIX_EMAIL + "EMAIL]" + " [" + PREFIX_ADDRESS + "ADDRESS]";

    public static final String DEFAULT_COMMAND = "Staff(s) displayed: %1$s";
    private PersonContainsFieldsPredicate testCondition;



    /**
     * Constructor of a view command
     *
     * @param testCondition the test condition to go by
     */
    public ViewCommand(PersonContainsFieldsPredicate testCondition) {
        this.testCondition = testCondition;
    }



    private CommandResult getDefaultResult(Model model) {
        requireNonNull(model);
        //places the person on the list view
        model.updateFilteredPersonList(testCondition);
        return new CommandResult(String.format(DEFAULT_COMMAND,
                model.getFilteredPersonList().toString()));
    }

    @Override
    public CommandResult execute(Model model) {
        return getDefaultResult(model);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand
                && testCondition.equals(((ViewCommand) other).testCondition));
    }
}
