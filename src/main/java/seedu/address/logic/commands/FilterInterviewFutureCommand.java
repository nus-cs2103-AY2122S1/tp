package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.interview.Interview;

/**
 * Filters the displayed list to show applicants with upcoming interviews in the future.
 */
public class FilterInterviewFutureCommand extends FilterInterviewCommand {

    public static final String MESSAGE_SUCCESS =
            "Showing %d applicants with upcoming interviews in the future. ";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredPersonList((person) -> {
            Interview interview = person.getInterview().orElse(Interview.EMPTY_INTERVIEW);
            return !interview.isEmptyInterview() && !interview.hasInterviewPassed();
        });

        return new CommandResult(String.format(MESSAGE_SUCCESS, model.getFilteredPersonList().size()));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterInterviewFutureCommand); // instanceof handles nulls
    }
}
