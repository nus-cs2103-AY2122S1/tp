package seedu.tracker.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_ACADEMIC_YEAR;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_SEMESTER;

import seedu.tracker.model.Model;
import seedu.tracker.model.module.ModuleInSpecificSemesterPredicate;

/**
 * View all the modules taken in a specific semester.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": View modules in a specific semester."
            + "\nParameters: "
            + PREFIX_ACADEMIC_YEAR + "YEAR "
            + PREFIX_SEMESTER + "SEMESTER ";

    public static final String MESSAGE_SUCCESS_ONE = "1 module is taken in this semester.";
    public static final String MESSAGE_SUCCESS_TWO_OR_MORE = "%1$s modules are taken in this semester.";
    public static final String MESSAGE_SUCCESS_ZERO = "Oops, no module is taken in this semester.";

    private final ModuleInSpecificSemesterPredicate predicate;

    /**
     * Creates an ViewCommand.
     */
    public ViewCommand(ModuleInSpecificSemesterPredicate predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredModuleList(predicate);
        int resultListSize = model.getFilteredModuleList().size();
        String messageSuccess = MESSAGE_SUCCESS_TWO_OR_MORE;
        if (resultListSize == 0) {
            messageSuccess = MESSAGE_SUCCESS_ZERO;
        }
        if (resultListSize == 1) {
            messageSuccess = MESSAGE_SUCCESS_ONE;
        }
        return new CommandResult(
                String.format(messageSuccess, resultListSize));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && predicate.equals(((ViewCommand) other).predicate)); // state check
    }

}
