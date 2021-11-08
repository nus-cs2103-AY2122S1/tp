package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.interview.Interview;

public class FilterInterviewPastCommand extends FilterInterviewCommand {

    public static final String MESSAGE_SUCCESS =
            "Showing %d applicants with interviews that have already passed. ";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredPersonList((person) -> {
            Interview interview = person.getInterview().orElse(Interview.EMPTY_INTERVIEW);
            return !interview.isEmptyInterview() && interview.hasInterviewPassed();
        });

        return new CommandResult(String.format(MESSAGE_SUCCESS, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterInterviewPastCommand); // instanceof handles nulls
    }
}
