package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Filters specific tutors from the match list according to certain parameters.
 * Keyword matching is case-insensitive.
 */
public class FilterCommand extends Command {
    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all the tutor names that contain "
            + "the specified search parameters (case-insensitive) and displays them in the matched tutors list.\n"
            + "Parameters: [n/NAME] [g/GENDER] [q/QUALIFICATION]\n"
            + "Example: " + COMMAND_WORD + " q/3";

    public static final String MESSAGE_FILTER_FAILED = "Failed to filter as match list is empty";

    private final Predicate<Person> predicate;

    /** Creates a FilterCommand to search for the specified {@code Tutor} */
    public FilterCommand(Predicate<Person> predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.getMatchedTutorList().isEmpty()) {
            model.clearMatchedTutor();
            throw new CommandException(String.format(MESSAGE_FILTER_FAILED));
        }

        model.filterMatchedTutor(predicate);
        return new CommandResult(String.format(Messages.MESSAGE_TUTORS_LISTED_OVERVIEW,
                model.getMatchedTutorList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterCommand // instanceof handles nulls
                && predicate.equals(((FilterCommand) other).predicate)); // state check
    }
}
