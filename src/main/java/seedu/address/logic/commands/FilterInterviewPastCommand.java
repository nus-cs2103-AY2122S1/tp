package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.interview.Interview;

public class FilterInterviewPastCommand extends FilterInterviewCommand {

    public static final String MESSAGE_SUCCESS =
            "Showing applicants with interviews that have already passed. ";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredPersonList((person) -> {
            Interview interview = person.getInterview().get();
            return !interview.isEmptyInterview() && interview.hasInterviewPassed();
        });

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
