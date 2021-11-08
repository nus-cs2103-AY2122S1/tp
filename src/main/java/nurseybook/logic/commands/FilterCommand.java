package nurseybook.logic.commands;

import static java.util.Objects.requireNonNull;
import static nurseybook.logic.parser.CliSyntax.PREFIX_TAG;

import nurseybook.commons.core.Messages;
import nurseybook.model.Model;
import nurseybook.model.tag.ElderlyHasTagPredicate;

/**
 * Finds and lists all elderlies in the nursey book who has all the argument tags.
 * Tag matching is case insensitive.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";
    public static final String[] PARAMETERS = { PREFIX_TAG + "TAG", "[" + PREFIX_TAG + "MORE_TAGS]..." };

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all elderly with all specified tags"
            + "(case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: "
            + String.join(" ", PARAMETERS)
            + "\nExample: " + COMMAND_WORD + " " + PREFIX_TAG + "diabetes";

    private final ElderlyHasTagPredicate predicate;

    public FilterCommand(ElderlyHasTagPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredElderlyList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_ELDERLIES_LISTED_OVERVIEW, model.getFilteredElderlyList().size()),
                CommandResult.ListDisplayChange.ELDERLY);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterCommand // instanceof handles nulls
                && predicate.equals(((FilterCommand) other).predicate)); // state check
    }
}
