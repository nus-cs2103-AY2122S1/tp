package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;
import seedu.address.model.interview.Interview;

public class SortInterviewFutureCommand extends SortInterviewCommand {

    public static final String MESSAGE_SUCCESS =
            "Showing applicants with upcoming interviews in the future (sorted chronologically).";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        //model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredPersonList((person) -> {
            Interview interview = person.getInterview().get();
            return !interview.isEmptyInterview() && !interview.hasInterviewPassed();
        });

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
