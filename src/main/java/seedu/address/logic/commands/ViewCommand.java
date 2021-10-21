package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.model.Model;
import seedu.address.model.person.predicates.PersonContainsFieldsPredicate;

/**
 * Represents a command to view.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays a filtered list of staff that have the "
            + "input parameters.\n\n"
            + "Parameters:\n"
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_INDEX + "INDEX] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_SALARY + "SALARY] "
            + "[" + PREFIX_STATUS + "STATUS] "
            + "[" + PREFIX_ROLE + "ROLE]... "
            + "[" + PREFIX_TAG + "TAG]...\n\n"
            + "Example:\n" + COMMAND_WORD + " "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

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
