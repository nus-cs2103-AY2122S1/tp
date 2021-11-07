package seedu.academydirectory.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.academydirectory.commons.core.Messages;
import seedu.academydirectory.model.VersionedModel;
import seedu.academydirectory.model.student.InformationContainsKeywordsPredicate;

/**
 * Filters and lists all students in Academy Directory whose name or tag(s) contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String HELP_MESSAGE = "### Locating students by name: `filter`\n"
            + "\n"
            + "Filters and displays students whose names or tags contain any of the given keywords.\n"
            + "\n"
            + "Format: `filter KEYWORD [MORE_KEYWORDS]`\n"
            + "\n"
            + "* The search is case-insensitive. e.g `stream` will match `Stream`\n"
            + "* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`\n"
            + "* Name and tag(s) will searched.\n"
            + "* Only full words will be matched e.g. `Han` will not match `Hans`\n"
            + "* Students matching at least one keyword will be returned (i.e. `OR` search).\n"
            + "  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`."
            + " `stream mission` will return students with `stream` or `mission` tag.\n"
            + "\n"
            + "Examples:\n"
            + "* `filter John` returns `john` and `John Doe`\n"
            + "* `filter alex mission` returns `Alex Yeoh` and students with `mission` tag<br>";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all students by names or tags.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Type in `help filter` for more details\n";

    private final InformationContainsKeywordsPredicate predicate;

    public FilterCommand(InformationContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(VersionedModel model) {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, model.getFilteredStudentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterCommand // instanceof handles nulls
                && predicate.equals(((FilterCommand) other).predicate)); // state check
    }
}
