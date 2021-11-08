package seedu.address.logic.commands.teacher;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.person.teacher.TeacherInvolvementContainsKeywordsPredicate;

/**
 * Filters and lists all teachers in NewAddressBook whose involvement and/or tags contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FilterTeacherCommand extends Command {

    public static final String COMMAND_WORD = "filterTeacher";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all teachers by involvement and tag(s) "
            + "and displays them as a list with index numbers.\n"
            + "Note: You do not need both involvement and tag(s) at the same time, but either one must be present.\n"
            + "Only alphanumeric tag parameters in the search are allowed.\n"
            + "Involvement must come before tags.\n"
            + "Parameters: [INVOLVEMENT] [t/TAG]...\n"
            + "Example: " + COMMAND_WORD + " donut buddies t/gym t/swim";

    private final TeacherInvolvementContainsKeywordsPredicate predicate;

    public FilterTeacherCommand(TeacherInvolvementContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTeacherList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_TEACHERS_LISTED_OVERVIEW, model.getFilteredTeacherList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterTeacherCommand // instanceof handles nulls
                && predicate.equals(((FilterTeacherCommand) other).predicate)); // state check
    }
}

